package com.tarena.day0305;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class MainActivity extends Activity {
	private ListView lvData;
	private ArrayAdapter<String> adapter;

	private void setupView() {
		lvData = (ListView) findViewById(R.id.lvData);
		ArrayList<String> data = getData();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);
		lvData.setAdapter(adapter);
	}

	private void addListener() {
		lvData.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				menu.setHeaderIcon(android.R.drawable.ic_dialog_info)
						.setHeaderTitle("操作");

				menu.add(1, 1, 1, "添加");
				menu.add(1, 2, 2, "修改");
				menu.add(1, 3, 3, "删除");
			}
		});
	}

	private ArrayList<String> getData() {
		ArrayList<String> data = new ArrayList<String>();
		for (int i = 1; i <= 20; i++) {
			data.add("item" + i);
		}
		return data;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupView();
		addListener();
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// 获取被长按项的值
		ContextMenuInfo menuInfo = item.getMenuInfo();
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		String child = adapter.getItem(info.position);

		// 执行具体操作
		switch (item.getItemId()) {
		case 1:// 添加
			adapter.add("追加数据");
			break;
		case 2:// 修改
			adapter.insert("修改项", info.position);
			break;
		case 3:// 删除
			adapter.remove(child);
			break;
		}

		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
