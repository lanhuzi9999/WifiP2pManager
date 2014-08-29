package com.tarena.day1307;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText etNumber, etContent;
	private SmsManager manager;

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

		manager.sendTextMessage(number, null, content, null, null);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupView();

		manager = SmsManager.getDefault();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
