package com.example.day_13_05_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	public class MyBinder extends Binder {
		// public void play(){
		// MyService.this.play();
		// }

		public MyService getService() {
			return MyService.this;
		}

	}

	public void play() {
		Log.i("info", "MyService.play()");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("info", "MyService.onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("info", "MyService.onCreate");
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("info", "MyService.onBind");
		return new MyBinder();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("info", "MyService.onUnbind");
		return true;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("info", "MyService.onDestroy");
	}

}
