package com.tarena.day0306;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	public void doClick(View v) {
		Intent service = new Intent(this, MyService.class);
		switch (v.getId()) {
		case R.id.button1:// Æô¶¯
			// Æô¶¯Service
			startService(service);
			break;
		case R.id.button2:// Í£Ö¹
			stopService(service);
			break;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
