package com.example.day_13_05_service;

import com.example.day_13_05_service.MyService.MyBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class SecodActivity extends Activity {
	// private MyBinder binder;
	private MyService service;
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			// MainActivity.this.binder = (MyBinder) binder;
			service = ((MyBinder) binder).getService();
			Log.i("info", "SecondActivity.ServiceConnection.onServiceConnected");
		}
	};

	public void doClick(View v) {
		Intent intent = new Intent(this, MyService.class);
		switch (v.getId()) {
		case R.id.btnBindService:// °ó¶¨Service
			bindService(intent, conn, BIND_AUTO_CREATE);
			break;
		case R.id.btnUnbindService:
			unbindService(conn);
			break;
		case R.id.btnStartActivity:
			intent = new Intent(this, SecodActivity.class);
			startActivity(intent);
			break;
		case R.id.btnPlay:
			if (service != null) {
				service.play();
			}
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("SecondActivity");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
