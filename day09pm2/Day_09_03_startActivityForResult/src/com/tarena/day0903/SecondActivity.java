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
		// 如果接受的是无效值，则返回 RESULT_CANCELED
		if (value <= 0 || value > 3) {
			setResult(RESULT_CANCELED);
			finish();
		}
	}

	public void doClick(View v) {
		String resultData = null;
		switch (value) {
		case 1:
			resultData = "你好";
			break;
		case 2:
			resultData = "你很好";
			break;
		case 3:
			resultData = "你非常好";
			break;
		}

		Intent data = new Intent();
		data.putExtra("retValue", resultData);
		setResult(RESULT_OK, data);
		finish();
	}
}
