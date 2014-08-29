package com.tarena.musicplayer;

import com.tarena.adapter.MusicAdapter;
import com.tarena.entity.Music;
import com.tarena.utils.GolbalConsts;

import android.os.Bundle;
import android.provider.MediaStore.Audio.Media;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
	private InnerReceiver receiver;

	private void setupView() {
		// ��ʼ��listview
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		// ��ȡ���ݣ�������adapter
		adapter = new MusicAdapter(this, app.getPlayList());
		// ����������
		lvMusics.setAdapter(adapter);

		// ��ȡ�����ؼ�������
		tvName = (TextView) findViewById(R.id.tvMusicName);
		tvSinger = (TextView) findViewById(R.id.tvSinger);

		sbProgress = (SeekBar) findViewById(R.id.sbProgress);

		btnPlayOrPause = (Button) findViewById(R.id.btnPlayOrPause);
	}

	public void doClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btnPrevious:// ��һ��
			intent.setAction(GolbalConsts.ACTION_PREVIOUS);
			btnPlayOrPause.setText("Pause");
			break;
		case R.id.btnPlayOrPause:// ���Ż���ͣ
			String text = btnPlayOrPause.getText().toString();
			if ("Play".equalsIgnoreCase(text)) {
				intent.setAction(GolbalConsts.ACTION_PLAY);
				btnPlayOrPause.setText("Pause");
			} else {
				intent.setAction(GolbalConsts.ACTION_PAUSE);
				btnPlayOrPause.setText("Play");
			}
			break;
		case R.id.btnNext:// ��һ��
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

		getContentResolver().delete(Media.EXTERNAL_CONTENT_URI, null, null);

		app = (MusicApplication) getApplication();
		setupView();
		// �������ŷ���
		Intent service = new Intent(this, MusicPlayService.class);
		startService(service);

		// ע��㲥������
		receiver = new InnerReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(GolbalConsts.ACTION_CURRENT_MUSIC_CHANGED);
		registerReceiver(receiver, filter);
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
		case R.id.menu_exit:// �˳�
			intent.setAction(GolbalConsts.ACTION_STOP_SERVICE);
			finish();
			break;
		case R.id.sub_menu_loop:// ѭ��
			intent.setAction(GolbalConsts.ACTION_PLAYMODE_CHANGED);
			intent.putExtra(GolbalConsts.EXTRA_PLAY_MODE,
					GolbalConsts.PLAY_MODE_LOOP);
			break;
		case R.id.sub_menu_random:// ���
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
				// ��ǰ���ֱ��,��ȡ��ǰ������Ϣ
				Music m = app.getCurrentMusic();
				// ���½���
				tvName.setText(m.getName());
				tvSinger.setText(m.getArtist());
			}
		}
	}

}
