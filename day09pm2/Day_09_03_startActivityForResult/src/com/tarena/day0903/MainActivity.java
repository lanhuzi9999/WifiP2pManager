package com.tarena.day0903;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 启动SecondActivity
		Intent intent = new Intent(this, SecondActivity.class);
		intent.putExtra("key", new Random().nextInt(3) + 1);
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case RESULT_OK:// 操作成功
			String retValue = data.getStringExtra("retValue");
			Toast.makeText(this, retValue, Toast.LENGTH_LONG).show();
			break;

		case RESULT_CANCELED:// 操作失败
			Toast.makeText(this, "操作失败", Toast.LENGTH_LONG).show();
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
