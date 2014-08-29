package com.tarena.musicplayer;

import java.io.IOException;
import java.util.Random;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;

import com.tarena.entity.Music;
import com.tarena.utils.GolbalConsts;

public class MusicPlayService extends Service {
	private MediaPlayer player;
	private boolean isPause;
	private MusicApplication app;
	private Random rand;
	private int playMode;
	private InnserReceiver receiver;

	private void play() {
		if (isPause) {
			// 从暂停状态恢复播放
			player.start();
		} else {
			// 获取当前播放的音乐信息
			Music m = app.getCurrentMusic();
			if (m != null) {
				try {
					// 播放新音乐
					player.reset();
					player.setDataSource(m.getMusicPath());
					player.prepare();
					player.start();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		isPause = false;
	}

	public void pause() {
		if (player.isPlaying()) {
			player.pause();
			isPause = true;
		}
	}

	public void previous() {
		// 获取当前正在播放的音乐的索引
		int currentIndex = app.getCurrentIndex();
		// 计算上一首音乐的索引
		switch (playMode) {
		case GolbalConsts.PLAY_MODE_LOOP:// 循环播放
			if (--currentIndex < 0) {
				currentIndex = app.getPlayListSize() - 1;
			}
			break;
		case GolbalConsts.PLAY_MODE_RANDOM:// 随机播放
			do {
				currentIndex = rand.nextInt(app.getPlayListSize());
			} while (currentIndex == app.getCurrentIndex());
			break;
		}
		// 设置为当前播放的音乐
		app.setCurrentIndex(currentIndex);
		// 播放
		isPause = false;
		play();
	}

	public void next() {
		// 获取当前正在播放的音乐的索引
		int currentIndex = app.getCurrentIndex();
		// 计算下一首音乐的索引
		switch (playMode) {
		case GolbalConsts.PLAY_MODE_LOOP:// 循环播放
			if (++currentIndex == app.getPlayListSize() - 1) {
				currentIndex = 0;
			}
			break;
		case GolbalConsts.PLAY_MODE_RANDOM:// 随机播放
			do {
				currentIndex = rand.nextInt(app.getPlayListSize());
			} while (currentIndex == app.getCurrentIndex());
			break;
		}
		// 设置为当前播放的音乐
		app.setCurrentIndex(currentIndex);
		// 播放
		isPause = false;
		play();
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		app = (MusicApplication) getApplication();
		isPause = false;
		rand = new Random();
		playMode = GolbalConsts.PLAY_MODE_LOOP;
		player = new MediaPlayer();
		player.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				next();
			}
		});

		// 注册广播接收器
		receiver = new InnserReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(GolbalConsts.ACTION_PLAY);
		filter.addAction(GolbalConsts.ACTION_PREVIOUS);
		filter.addAction(GolbalConsts.ACTION_PAUSE);
		filter.addAction(GolbalConsts.ACTION_PLAYMODE_CHANGED);
		filter.addAction(GolbalConsts.ACTION_NEXT);
		filter.addAction(GolbalConsts.ACTION_STOP_SERVICE);
		registerReceiver(receiver, filter);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
		player.release();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	private class InnserReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (GolbalConsts.ACTION_PLAY.equals(action)) {
				// 播放
				play();
			} else if (GolbalConsts.ACTION_PAUSE.equals(action)) {
				// 暂停
				pause();
			} else if (GolbalConsts.ACTION_PREVIOUS.equals(action)) {
				// 上一首
				previous();
			} else if (GolbalConsts.ACTION_NEXT.equals(action)) {
				// 下一首
				next();
			} else if (GolbalConsts.ACTION_PLAYMODE_CHANGED.equals(action)) {
				// 播放模式变更
				playMode = intent.getIntExtra(GolbalConsts.EXTRA_PLAY_MODE,
						GolbalConsts.PLAY_MODE_LOOP);
			} else if (GolbalConsts.ACTION_STOP_SERVICE.equals(action)) {
				// 停止服务
				stopSelf();
			}
		}
	}

}
