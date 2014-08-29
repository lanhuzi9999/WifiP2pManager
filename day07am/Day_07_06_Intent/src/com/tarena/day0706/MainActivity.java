package com.tarena.day0706;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void doClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btnBrowser:
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("http://www.163.com"));
			break;
		case R.id.btnCall:
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:13811022566"));
			break;
		case R.id.btnDial:
			intent.setAction(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel:13811022566"));
			break;
		case R.id.btnSendMessage:
			intent.setAction(Intent.ACTION_SENDTO);
			intent.setData(Uri.parse("smsto:13811025566"));
			intent.putExtra("sms_body", "ÄãºÃ!");
			break;
		}
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
