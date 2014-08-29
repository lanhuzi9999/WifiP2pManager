package com.tarena.day0704;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// Æô¶¯SecondActivity
		Intent intent = new Intent(this, SecondActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
