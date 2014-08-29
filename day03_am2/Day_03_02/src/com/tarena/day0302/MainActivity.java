package com.tarena.day0302;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.tarena.adapter.DataAdapter;
import com.tarena.bll.DataSource;

public class MainActivity extends Activity {
	private ListView lvData;
	private DataSource ds;
	private DataAdapter adapter;
	private int page = 1;

	private void setupView() {
		lvData = (ListView) findViewById(R.id.lvData);
		ArrayList<String> data = ds.getData(page++);
		adapter = new DataAdapter(this, data);
		lvData.setAdapter(adapter);
	}

	private void addListeners() {
		lvData.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if(scrollState==OnScrollListener.SCROLL_STATE_FLING){
					//停止更新listview
					adapter.setFling(true);
				}else{
					//开始更新listview
					adapter.setFling(false);
					adapter.notifyDataSetChanged();
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if(firstVisibleItem+visibleItemCount-1==totalItemCount-1){
					//获取下一页数据  更新listview
					ArrayList<String> data = ds.getData(page++);
					adapter.append(data);
				}
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ds = new DataSource();
		setupView();// 界面初始化
		addListeners();// 为控件添加监听器
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
