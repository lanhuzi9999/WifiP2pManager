package com.tarena.musicclient;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.tarena.adapter.MusicAdapter;
import com.tarena.bll.AsyncMusicParseTask;
import com.tarena.entity.Music;
import com.tarena.utils.GlobalConsts;

public class MainActivity extends Activity {
	private ListView lvMusics;
	private MusicAdapter adapter;

	/**
	 * ��ʼ������
	 */
	private void setupView() {
		// ��ȡlistview������
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		// ����adapter
		adapter = new MusicAdapter(this, null, lvMusics);
		// ����adapter
		lvMusics.setAdapter(adapter);

		// �첽���غͽ���xml
		new AsyncMusicParseTask(this).execute(GlobalConsts.BASE_URL
				+ "sounds.xml");
	}

	private void addListener() {
		lvMusics.setOnScrollListener(new OnScrollListener() {
			private int lastPosition;

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (scrollState == OnScrollListener.SCROLL_STATE_FLING) {
					adapter.setFling(true);
				} else {
					if (lastPosition == adapter.getCount() - 1) {
						new AsyncMusicParseTask(MainActivity.this)
								.execute(GlobalConsts.BASE_URL + "sounds.xml");
					}
					adapter.setFling(false);
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				lastPosition = firstVisibleItem + visibleItemCount - 1;
			}
		});
	}

	/**
	 * ����listView
	 * 
	 * @param musics
	 */
	public void updateListView(ArrayList<Music> musics) {
		// ����listview
		adapter.appendData(musics);
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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		adapter.quit();
	}

}
