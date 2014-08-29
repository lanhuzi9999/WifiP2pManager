package com.tarena.bll;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;

import com.tarena.utils.GlobalConsts;
import com.tarena.utils.HttpUtils;
import com.tarena.utils.StreamUtils;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class AsyncMusicLoader {
	private static final int MSG_WHAT_FILE_EXISTS = 1;
	private static final int MSG_WHAT_STARTEDED = 4;
	private static final int MSG_WHAT_FINISHED = 2;
	private static final int MSG_WHAT_FAILED = 3;

	private boolean isLoop;
	private ArrayList<String> tasks;
	private Handler handler;
	private Thread workThread;

	/**
	 * 发送消息到主线程
	 * 
	 * @param what
	 * @param obj
	 */
	private void sendMessage(int what, Object obj) {
		Message msg = Message.obtain(handler, what, obj);
		msg.sendToTarget();
	}

	public AsyncMusicLoader(final Context context) {
		isLoop = true;
		tasks = new ArrayList<String>();
		handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				Toast.makeText(context, msg.obj.toString(), Toast.LENGTH_LONG)
						.show();
			};
		};
		workThread = new Thread() {
			public void run() {
				while (isLoop) {
					// 轮询 执行下载任务
					while (!tasks.isEmpty()) {
						// 获取第一条任务并移除
						String path = tasks.remove(0);
						try {
							// 执行下载
							File file = new File(
									Environment
											.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),
									path);
							// 文件已存在
							if (file.exists()) {
								// 发送消息到主线程
								sendMessage(
										MSG_WHAT_FILE_EXISTS,
										"文件已存在，请勿重复下载:"
												+ file.getAbsolutePath());
								// 本次下载结束，开始下一个下载
								continue;
							}
							// 下载
							sendMessage(MSG_WHAT_STARTEDED, "开始下载."
									+ GlobalConsts.BASE_URL + path);

							HttpEntity entity = HttpUtils.getEntity(
									GlobalConsts.BASE_URL + path, null,
									HttpUtils.METHOD_GET);
							InputStream in = HttpUtils.getStream(entity);
							StreamUtils.save(in, file);
							// 发送消息到主线程
							sendMessage(MSG_WHAT_FINISHED, "下载结束."
									+ GlobalConsts.BASE_URL + path);
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							sendMessage(MSG_WHAT_FAILED, "音乐下载失败."
									+ GlobalConsts.BASE_URL + path);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							sendMessage(MSG_WHAT_FAILED, "音乐下载失败."
									+ GlobalConsts.BASE_URL + path);
						}
					}
					// 线程等待
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			};
		};
		workThread.start();
	}

	/**
	 * 音乐下载的方法
	 * 
	 * @param path
	 */
	public void download(String path) {
		if (!tasks.contains(path)) {
			tasks.add(path);
			synchronized (workThread) {
				workThread.notify();
			}
		}
	}

}
