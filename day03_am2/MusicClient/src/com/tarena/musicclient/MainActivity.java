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

	/**
	 * ����listView
	 * 
	 * @param musics
	 */
	public void updateListView(ArrayList<Music> musics) {
		// ����listview
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
