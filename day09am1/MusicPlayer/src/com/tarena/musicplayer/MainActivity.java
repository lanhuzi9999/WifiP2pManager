package com.tarena.musicplayer;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.tarena.adapter.MusicAdapter;
import com.tarena.entity.Music;
import com.tarena.utils.GolbalConsts;

public class MainActivity extends Activity {
	private ListView lvMusics;
	private TextView tvName, tvSinger;
	private SeekBar sbProgress;
	private Button btnPlayOrPause;
	private MusicApplication app;
	private MusicAdapter adapter;
	private InnerReceiver receiver;

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

	private void addListener() {
		sbProgress.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// 获取当前进度条的进度
				int progress = sbProgress.getProgress();
				// 发送广播
				Intent intent = new Intent(GolbalConsts.ACTION_SEEK_TO);
				intent.putExtra(GolbalConsts.EXTRA_CURRENT_POSITION, progress);
				sendBroadcast(intent);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});
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
		addListener();
		// 启动播放服务
		Intent service = new Intent(this, MusicPlayService.class);
		startService(service);

		// 注册广播接收器
		receiver = new InnerReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(GolbalConsts.ACTION_CURRENT_MUSIC_CHANGED);
		filter.addAction(GolbalConsts.ACTION_UPDATE_PROGRESS);
		filter.addAction(GolbalConsts.ACTION_RESPONSE_PLAY_STATE);
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// 发送广播
		Intent intent = new Intent(GolbalConsts.ACTION_ACTIVITY_STARTED);
		sendBroadcast(intent);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// 发送广播
		Intent intent = new Intent(GolbalConsts.ACTION_ACTIVITY_STOPED);
		sendBroadcast(intent);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
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

	private class InnerReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (GolbalConsts.ACTION_CURRENT_MUSIC_CHANGED.equals(action)) {
				// 当前音乐变更,获取当前音乐信息
				Music m = app.getCurrentMusic();
				// 更新界面
				tvName.setText(m.getName());
				tvSinger.setText(m.getArtist());
			} else if (GolbalConsts.ACTION_UPDATE_PROGRESS.equals(action)) {
				// 播放进度更新,获取播放进度
				int progress = intent.getIntExtra(
						GolbalConsts.EXTRA_CURRENT_POSITION, 0);
				int duration = (int) app.getCurrentMusic().getDuration();
				progress = progress * 100 / duration;

				// 更新界面
				sbProgress.setProgress(progress);
			} else if (GolbalConsts.ACTION_RESPONSE_PLAY_STATE.equals(action)) {
				// 获取播放状态
				int state = intent.getIntExtra(GolbalConsts.EXTRA_PLAY_STATE,
						GolbalConsts.STATE_OTHERS);
				// 根据状态 设置界面
				Music m = app.getCurrentMusic();
				switch (state) {
				case GolbalConsts.STATE_PLAYING:// 播放
					tvName.setText(m.getName());
					tvSinger.setText(m.getArtist());
					btnPlayOrPause.setText("Pause");
					break;
				case GolbalConsts.STATE_PAUSE:// 暂停
					tvName.setText(m.getName());
					tvSinger.setText(m.getArtist());
					int progress = intent.getIntExtra(
							GolbalConsts.EXTRA_CURRENT_POSITION, 0);
					progress = progress * 100 / (int) m.getDuration();
					sbProgress.setProgress(progress);
					break;
				}
			}
		}
	}

}
