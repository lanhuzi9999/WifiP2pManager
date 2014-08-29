package com.tarena.bll;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;

import com.tarena.utils.BitmapUtils;
import com.tarena.utils.GlobalConsts;
import com.tarena.utils.HttpUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class AsyncImageLoader {
	private Context context;
	private boolean isLoop;
	private ArrayList<ImageLoadTask> tasks;
	private Thread workThread;
	private Handler handler;
	private HashMap<String, SoftReference<Bitmap>> caches;

	public AsyncImageLoader(final Context context, final Callback callback) {
		this.context = context;
		this.isLoop = true;
		this.tasks = new ArrayList<AsyncImageLoader.ImageLoadTask>();
		this.caches = new HashMap<String, SoftReference<Bitmap>>();
		this.handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				// ��ȡ������ɵ��������
				ImageLoadTask task = (ImageLoadTask) msg.obj;
				// �ص�
				callback.imageLoaded(task.path, task.bitmap);
			};
		};
		this.workThread = new Thread() {
			@Override
			public void run() {
				Log.i("info", "�����߳̿�ʼ����");
				while (isLoop) {
					// ��ѯ
					while (isLoop && !tasks.isEmpty()) {
						try {
							// ��ȡ���Ƴ���һ������
							ImageLoadTask task = tasks.remove(0);
							// ����
							HttpEntity entity = HttpUtils.getEntity(
									GlobalConsts.BASE_URL + task.path, null,
									HttpUtils.METHOD_GET);
							byte[] data = HttpUtils.getBytes(entity);
							task.bitmap = BitmapUtils.loadBitmap(data, 64, 64);

							// ����Ϣ
							Message msg = Message.obtain(handler, 0, task);
							msg.sendToTarget();

							// ����
							caches.put(task.path, new SoftReference<Bitmap>(
									task.bitmap));

							File file = new File(context.getExternalCacheDir(),
									task.path);
							BitmapUtils.save(task.bitmap, file);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (!isLoop)
						break;

					// �̵߳ȴ�
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				Log.i("info", "�����߳̽���");
			}

		};
		this.workThread.start();
	}

	public Bitmap loadImage(String path) {
		// ������漯���д��ڸ�·����ͼƬ�����ػ���ͼƬ
		if (caches.containsKey(path)) {
			Bitmap bm = caches.get(path).get();
			if (bm != null) {
				return bm;
			}
		}
		// ����ļ������д��ڸ�·����ͼƬ�����ػ����ͼƬ
		File file = new File(context.getCacheDir(), path);
		if (file.exists()) {
			Bitmap bm = BitmapUtils.loadBitmap(file.getAbsolutePath());
			if (bm != null) {
				return bm;
			}
		}
		// �����񼯺����������
		ImageLoadTask task = new ImageLoadTask();
		task.path = path;
		if (!tasks.contains(task)) {
			tasks.add(task);
			synchronized (workThread) {
				try {
					workThread.notify();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// ͬ������null
		return null;
	}

	/**
	 * ���������߳�
	 */
	public void quit() {
		isLoop = false;
		synchronized (workThread) {
			workThread.notify();
		}
	}

	/**
	 * ͼƬ����������
	 * 
	 * @author Administrator
	 * 
	 */
	private class ImageLoadTask {
		private String path;
		private Bitmap bitmap;

		@Override
		public boolean equals(Object o) {
			ImageLoadTask task = (ImageLoadTask) o;
			return path.equals(task.path);
		}
	}

	public interface Callback {
		void imageLoaded(String path, Bitmap bitmap);
	}
}
