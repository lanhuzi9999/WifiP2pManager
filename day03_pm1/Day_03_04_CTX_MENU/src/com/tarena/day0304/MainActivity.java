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
				menu.setHeaderTitle("操作");

				menu.add(2, 10, 1, "全选");
				menu.add(2, 11, 2, "复制");
				menu.add(2, 12, 3, "剪切");
				menu.add(2, 13, 4, "粘贴");
			}
		});
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 10:// 全选
			Toast.makeText(this, "您选择了 全选 操作", Toast.LENGTH_LONG).show();
			break;
		case 11:// 复制
			Toast.makeText(this, "您选择了 复制 操作", Toast.LENGTH_LONG).show();
			break;
		case 12:// 剪切
			Toast.makeText(this, "您选择了 剪切 操作", Toast.LENGTH_LONG).show();
			break;
		case 13:// 粘贴
			Toast.makeText(this, "您选择了 粘贴 操作", Toast.LENGTH_LONG).show();
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
