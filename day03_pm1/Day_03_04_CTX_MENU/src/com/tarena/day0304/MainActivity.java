package com.tarena.day0304;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText etTest;

	private void setupView() {
		etTest = (EditText) findViewById(R.id.etTest);
	}

	private void addListener() {
		etTest.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				menu.setHeaderIcon(android.R.drawable.ic_dialog_info);
				menu.setHeaderTitle("����");

				menu.add(2, 10, 1, "ȫѡ");
				menu.add(2, 11, 2, "����");
				menu.add(2, 12, 3, "����");
				menu.add(2, 13, 4, "ճ��");
			}
		});
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 10:// ȫѡ
			Toast.makeText(this, "��ѡ���� ȫѡ ����", Toast.LENGTH_LONG).show();
			break;
		case 11:// ����
			Toast.makeText(this, "��ѡ���� ���� ����", Toast.LENGTH_LONG).show();
			break;
		case 12:// ����
			Toast.makeText(this, "��ѡ���� ���� ����", Toast.LENGTH_LONG).show();
			break;
		case 13:// ճ��
			Toast.makeText(this, "��ѡ���� ճ�� ����", Toast.LENGTH_LONG).show();
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupView();
		addListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
