package com.tarena.day0404;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public abstract class MyIntentService extends Service {
	private HandlerThread thread;
	private Handler handler;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("info", "Service����");
		// ��������������Ϣ���еĹ����߳�
		thread = new HandlerThread("workThread");
		thread.start();
		// ����handler �������ù����߳�
		Looper looper = thread.getLooper();
		handler = new Handler(looper) {
			/**
			 * �����ڹ����̵߳���Ϣ������
			 */
			public void handleMessage(android.os.Message msg) {
				// ��ȡintent��������������
				onHandleIntent((Intent) msg.obj);
				// ���������,������������
				stopSelf(msg.arg1);
			};
		};
	}

	/**
	 * �����ҵ���������ڹ����߳��б�����
	 * 
	 * @param intent
	 */
	public abstract void onHandleIntent(Intent intent);

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// ������Ϣ�������߳�
		Message msg = Message.obtain();
		msg.obj = intent;
		msg.arg1 = startId;
		handler.sendMessage(msg);

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		thread.quit();
		Log.i("info", "Service����");
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
