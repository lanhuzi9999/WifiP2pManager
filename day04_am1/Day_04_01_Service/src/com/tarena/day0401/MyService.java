package com.tarena.day0401;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("info", "MyService.onCreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, final int startId) {
		Log.i("info", "MyService.onStartCommand");

		// 创建一个线程 模拟耗时任务的执行
		new Thread() {
			public void run() {
				for (int i = 1; i <= 5; i++) {
					Log.i("info", "in thread " + getName() + ",i=" + i);
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				stopSelf(startId);
			};
		}.start();

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("info", "MyService.onDestroy");
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
