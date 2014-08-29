package com.tarena.musicclient;

import java.util.ArrayList;

import com.tarena.adapter.MusicAdapter;
import com.tarena.bll.AsyncMusicParseTask;
import com.tarena.entity.Music;
import com.tarena.utils.GlobalConsts;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView lvMusics;
	private MusicAdapter adapter;

	/**
	 * 初始化界面
	 */
	private void setupView() {
		// 获取listview的引用
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		// 创建adapter
		adapter = new MusicAdapter(this, null, lvMusics);
		// 设置adapter
		lvMusics.setAdapter(adapter);

		// 异步加载和解析xml
		new AsyncMusicParseTask(this).execute(GlobalConsts.BASE_URL
				+ "sounds.xml");
	}

	/**
	 * 更新listView
	 * 
	 * @param musics
	 */
	public void updateListView(ArrayList<Music> musics) {
		// 更新listview
		adapter.changeData(musics);
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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		adapter.quit();
	}

}
