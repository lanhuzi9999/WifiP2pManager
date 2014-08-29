package com.tarena.day0903;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {
	private int value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		value = getIntent().getIntExtra("key", 1);
		// ������ܵ�����Чֵ���򷵻� RESULT_CANCELED
		if (value <= 0 || value > 3) {
			setResult(RESULT_CANCELED);
			finish();
		}
	}

	public void doClick(View v) {
		String resultData = null;
		switch (value) {
		case 1:
			resultData = "���";
			break;
		case 2:
			resultData = "��ܺ�";
			break;
		case 3:
			resultData = "��ǳ���";
			break;
		}

		Intent data = new Intent();
		data.putExtra("retValue", resultData);
		setResult(RESULT_OK, data);
		finish();
	}
}
