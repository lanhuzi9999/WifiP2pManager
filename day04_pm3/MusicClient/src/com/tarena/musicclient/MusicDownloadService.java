package com.tarena.musicclient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;

import com.tarena.utils.GlobalConsts;
import com.tarena.utils.HttpUtils;
import com.tarena.utils.StreamUtils;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.Toast;

public class MusicDownloadService extends IntentService {
	private static final int MSG_WHAT_FILE_EXISTS = 1;
	private static final int MSG_WHAT_STARTEDED = 4;
	private static final int MSG_WHAT_FINISHED = 2;
	private static final int MSG_WHAT_FAILED = 3;
	private Handler handler;
	private String fileName;
	private int length;
	private NotificationManager manager;
	private Builder builder;

	public MusicDownloadService() {
		super("workThread");
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		builder = new Builder(this)
				.setSmallIcon(android.R.drawable.ic_menu_upload)
				.setContentTitle("下载")
				.setAutoCancel(false)
				.setContentIntent(
						PendingIntent.getActivity(this, 0, new Intent(this,
								MainActivity.class), 0));

		handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				case MSG_WHAT_FILE_EXISTS:
					Toast.makeText(MusicDownloadService.this, "文件已存在，请勿重复下载",
							Toast.LENGTH_LONG).show();
					break;

				case MSG_WHAT_STARTEDED:// 开始下载
					builder.setContentText("正在下载：" + fileName).setProgress(
							length, 0, false);
					manager.notify(0, builder.build());
					break;
				case GlobalConsts.MSG_WHAT_UPDATE_PROGRESS:// 进度更新
					builder.setProgress(length, msg.arg1 * 1024, false);
					manager.notify(0, builder.build());
					break;
				case MSG_WHAT_FINISHED:// 下载完成
					builder.setContentText("下载完成").setAutoCancel(true)
							.setProgress(0, 0, false);
					manager.notify(0, builder.build());
					break;
				case MSG_WHAT_FAILED:// 下载失败
					builder.setContentText("下载失败").setAutoCancel(true)
							.setProgress(0, 0, false);
					manager.notify(0, builder.build());
					break;
				}
			};
		};
	}

	/**
	 * 发送消息的方法
	 * 
	 * @param what
	 * @param obj
	 */
	private void sendMessage(int what, Object obj) {
		Message msg = Message.obtain(handler, what, obj);
		msg.sendToTarget();
	}

	/**
	 * 在工作线程中运行的，用于处理具体业务
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		// 获取音乐路径
		String path = intent.getStringExtra("path");
		// 判断文件是否存在
		File file = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),
				path);
		if (file.exists()) {
			sendMessage(MSG_WHAT_FILE_EXISTS, "文件已存在，请勿重复下载");
			return;
		}
		try {
			// 执行下载
			HttpEntity entity = HttpUtils.getEntity(GlobalConsts.BASE_URL
					+ path, null, HttpUtils.METHOD_GET);
			// 获取要下载的音乐名 和 文件长度
			fileName = file.getName();
			length = (int) HttpUtils.getLength(entity);
			// 发送通知 开始下载
			handler.sendEmptyMessage(MSG_WHAT_STARTEDED);

			InputStream in = HttpUtils.getStream(entity);
			StreamUtils.save(in, file, handler);

			handler.sendEmptyMessage(MSG_WHAT_FINISHED);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			handler.sendEmptyMessage(MSG_WHAT_FAILED);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			handler.sendEmptyMessage(MSG_WHAT_FAILED);
		}

	}

}
