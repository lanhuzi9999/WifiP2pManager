package com.tarena.musicplayer;

import com.tarena.adapter.MusicAdapter;
import com.tarena.utils.GolbalConsts;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ListView lvMusics;
	private TextView tvName, tvSinger;
	private SeekBar sbProgress;
	private Button btnPlayOrPause;
	private MusicApplication app;
	private MusicAdapter adapter;

	private void setupView() {
		// 初始化listview
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		// 获取数据，并创建adapter
		adapter = new MusicAdapter(this, app.getPlayList());
		// 设置适配器
		lvMusics.setAdapter(adapter);

		// 获取其他控件的引用
		tvName = (TextView) findViewById(R.id.tvMusicName);
		tvSinger = (TextView) findViewById(R.id.tvSinger);

		sbProgress = (SeekBar) findViewById(R.id.sbProgress);

		btnPlayOrPause = (Button) findViewById(R.id.btnPlayOrPause);
	}

	public void doClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btnPrevious:// 上一首
			intent.setAction(GolbalConsts.ACTION_PREVIOUS);
			btnPlayOrPause.setText("Pause");
			break;
		case R.id.btnPlayOrPause:// 播放或暂停
			String text = btnPlayOrPause.getText().toString();
			if ("Play".equalsIgnoreCase(text)) {
				intent.setAction(GolbalConsts.ACTION_PLAY);
				btnPlayOrPause.setText("Pause");
			} else {
				intent.setAction(GolbalConsts.ACTION_PAUSE);
				btnPlayOrPause.setText("Play");
			}
			break;
		case R.id.btnNext:// 下一首
			intent.setAction(GolbalConsts.ACTION_NEXT);
			btnPlayOrPause.setText("Pause");
			break;
		}
		sendBroadcast(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		app = (MusicApplication) getApplication();
		setupView();
		// 启动播放服务
		Intent service = new Intent(this, MusicPlayService.class);
		startService(service);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch (item.getItemId()) {
		case R.id.menu_exit:// 退出
			intent.setAction(GolbalConsts.ACTION_STOP_SERVICE);
			finish();
			break;
		case R.id.sub_menu_loop:// 循环
			intent.setAction(GolbalConsts.ACTION_PLAYMODE_CHANGED);
			intent.putExtra(GolbalConsts.EXTRA_PLAY_MODE,
					GolbalConsts.PLAY_MODE_LOOP);
			break;
		case R.id.sub_menu_random:// 随机
			intent.setAction(GolbalConsts.ACTION_PLAYMODE_CHANGED);
			intent.putExtra(GolbalConsts.EXTRA_PLAY_MODE,
					GolbalConsts.PLAY_MODE_RANDOM);
			break;
		}

		sendBroadcast(intent);
		return super.onOptionsItemSelected(item);
	}

}
