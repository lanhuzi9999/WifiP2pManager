package com.example.day_10_03_simpleadapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private ArrayList<HashMap<String, Object>> getData() {
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (int i = 1; i <= 5; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("id", i);
			item.put("name", "����" + i);
			item.put("age", 40 - i * 2);
			data.add(item);
		}
		return data;
	}

	private ListView lvData;
	private SimpleAdapter adapter;

	private void setupView() {
		// ��ȡ�ؼ�������
		lvData = (ListView) findViewById(R.id.lvData);
		// ��ȡ����
		ArrayList<HashMap<String, Object>> data = getData();
		// ����������
		String[] from = { "id", "name", "age" };
		int[] to = { R.id.tvId, R.id.tvName, R.id.tvAge };
		adapter = new SimpleAdapter(this, data, R.layout.item, from, to);
		// ����������
		lvData.setAdapter(adapter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
