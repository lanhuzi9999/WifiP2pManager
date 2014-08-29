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
		Log.i("info", "Service启动");
		// 创建并启动有消息队列的工作线程
		thread = new HandlerThread("workThread");
		thread.start();
		// 创建handler 关联到该工作线程
		Looper looper = thread.getLooper();
		handler = new Handler(looper) {
			/**
			 * 运行在工作线程的消息处理方法
			 */
			public void handleMessage(android.os.Message msg) {
				// 获取intent，并进行任务处理
				onHandleIntent((Intent) msg.obj);
				// 任务处理完成,结束本次启动
				stopSelf(msg.arg1);
			};
		};
	}

	/**
	 * 具体的业务处理方法，在工作线程中被调用
	 * 
	 * @param intent
	 */
	public abstract void onHandleIntent(Intent intent);

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// 发送消息到工作线程
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
		Log.i("info", "Service销毁");
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
