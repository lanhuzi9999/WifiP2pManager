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

		// ����SecondActivity
		Intent intent = new Intent(this, SecondActivity.class);
		intent.putExtra("key", new Random().nextInt(3) + 1);
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case RESULT_OK:// �����ɹ�
			String retValue = data.getStringExtra("retValue");
			Toast.makeText(this, retValue, Toast.LENGTH_LONG).show();
			break;

		case RESULT_CANCELED:// ����ʧ��
			Toast.makeText(this, "����ʧ��", Toast.LENGTH_LONG).show();
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
