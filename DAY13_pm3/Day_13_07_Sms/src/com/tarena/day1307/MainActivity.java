package com.tarena.day1307;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputFilter;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText etNumber, etContent;
	private SmsManager manager;
	private InnerReceiver receiver;

	private void setupView() {
		etContent = (EditText) findViewById(R.id.etContent);
		etNumber = (EditText) findViewById(R.id.etNumber);
	}

	public void doClick(View v) {
		// 获取输入
		String number = etNumber.getText().toString();
		String content = etContent.getText().toString();
		// 发送短信
		// ArrayList<String> text = manager.divideMessage(content);
		// manager.sendMultipartTextMessage(number, null, text, null, null);
		Intent intent = new Intent("com.tarena.action.SMS_SENDED");
		intent.putExtra("number", number);
		intent.putExtra("body", content);
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		manager.sendTextMessage(number, null, content, pi, null);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupView();

		manager = SmsManager.getDefault();

		receiver = new InnerReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.tarena.action.SMS_SENDED");
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class InnerReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if ("com.tarena.action.SMS_SENDED".equals(action)) {
				// 短信发送完成
				Toast.makeText(MainActivity.this, "发送完成", Toast.LENGTH_LONG)
						.show();
				// 插入到数据库
				ContentResolver cr = getContentResolver();
				Uri uri = Uri.parse("content://sms/sent");
				ContentValues values = new ContentValues();
				values.put("address", intent.getStringExtra("number"));
				values.put("body", intent.getStringExtra("body"));
				values.put("date", System.currentTimeMillis());
				// values.put("type", 2);
				cr.insert(uri, values);
			}
		}
	}

}
