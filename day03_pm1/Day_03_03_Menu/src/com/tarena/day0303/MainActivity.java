package com.tarena.day0303;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:// ����
			Toast.makeText(this, "���ڱ�����...", Toast.LENGTH_LONG).show();
			break;

		case 2:// �˳�
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem item = menu.add(1, 1, 1, "����");
		menu.add(1, 2, 2, "�˳�");
		return true;
	}

}
