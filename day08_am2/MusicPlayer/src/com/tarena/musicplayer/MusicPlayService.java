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
	private boolean needUpdate;// ��ʶֵ�����ڱ�ʾ�����Ƿ���Ҫ���ȸ���
	private boolean isLoop;
	private Thread workThread;

	private void seekTo(int position) {
		player.seekTo(position);
		if (isPause) {
			player.start();
			isPause = false;
		}
	}

	private void play() {
		if (isPause) {
			// ����ͣ״̬�ָ�����
			player.start();
		} else {
			// ��ȡ��ǰ���ŵ�������Ϣ
			Music m = app.getCurrentMusic();
			if (m != null) {
				try {
					// ����������
					player.reset();
					player.setDataSource(m.getMusicPath());
					player.prepare();
					player.start();

					// ���͵�ǰ���ֱ���㲥
					Intent intent = new Intent(
							GolbalConsts.ACTION_CURRENT_MUSIC_CHANGED);
					sendBroadcast(intent);
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
		synchronized (workThread) {
			try {
				workThread.notify();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void pause() {
		if (player.isPlaying()) {
			player.pause();
			isPause = true;
		}
	}

	public void previous() {
		// ��ȡ��ǰ���ڲ��ŵ����ֵ�����
		int currentIndex = app.getCurrentIndex();
		// ������һ�����ֵ�����
		switch (playMode) {
		case GolbalConsts.PLAY_MODE_LOOP:// ѭ������
			if (--currentIndex < 0) {
				currentIndex = app.getPlayListSize() - 1;
			}
			break;
		case GolbalConsts.PLAY_MODE_RANDOM:// �������
			do {
				currentIndex = rand.nextInt(app.getPlayListSize());
			} while (currentIndex == app.getCurrentIndex());
			break;
		}
		// ����Ϊ��ǰ���ŵ�����
		app.setCurrentIndex(currentIndex);
		// ����
		isPause = false;
		play();
	}

	public void next() {
		// ��ȡ��ǰ���ڲ��ŵ����ֵ�����
		int currentIndex = app.getCurrentIndex();
		// ������һ�����ֵ�����
		switch (playMode) {
		case GolbalConsts.PLAY_MODE_LOOP:// ѭ������
			if (++currentIndex == app.getPlayListSize() - 1) {
				currentIndex = 0;
			}
			break;
		case GolbalConsts.PLAY_MODE_RANDOM:// �������
			do {
				currentIndex = rand.nextInt(app.getPlayListSize());
			} while (currentIndex == app.getCurrentIndex());
			break;
		}
		// ����Ϊ��ǰ���ŵ�����
		app.setCurrentIndex(currentIndex);
		// ����
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

		isLoop = true;
		needUpdate = true;
		workThread = new Thread() {
			public void run() {
				while (isLoop) {
					// ��������Ҫ���ȸ��£�
					// �����ִ��ڲ���״̬ʱ��
					// ÿ����²��Ž���
					while (needUpdate && player.isPlaying()) {
						// ���ͽ��ȹ㲥
						Intent intent = new Intent(
								GolbalConsts.ACTION_UPDATE_PROGRESS);
						intent.putExtra(GolbalConsts.EXTRA_CURRENT_POSITION,
								player.getCurrentPosition());
						sendBroadcast(intent);
						// ����1s
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					// ������Ҫ���� ʱ �̵߳ȴ�
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			};
		};
		workThread.start();

		// ע��㲥������
		receiver = new InnserReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(GolbalConsts.ACTION_PLAY);
		filter.addAction(GolbalConsts.ACTION_PREVIOUS);
		filter.addAction(GolbalConsts.ACTION_SEEK_TO);
		filter.addAction(GolbalConsts.ACTION_PAUSE);
		filter.addAction(GolbalConsts.ACTION_PLAYMODE_CHANGED);
		filter.addAction(GolbalConsts.ACTION_NEXT);
		filter.addAction(GolbalConsts.ACTION_STOP_SERVICE);
		filter.addAction(GolbalConsts.ACTION_ACTIVITY_STARTED);
		filter.addAction(GolbalConsts.ACTION_ACTIVITY_STOPED);
		registerReceiver(receiver, filter);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		needUpdate = false;
		isLoop = false;
		synchronized (workThread) {
			try {
				workThread.notify();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
				// ����
				play();
			} else if (GolbalConsts.ACTION_PAUSE.equals(action)) {
				// ��ͣ
				pause();
			} else if (GolbalConsts.ACTION_PREVIOUS.equals(action)) {
				// ��һ��
				previous();
			} else if (GolbalConsts.ACTION_NEXT.equals(action)) {
				// ��һ��
				next();
			} else if (GolbalConsts.ACTION_PLAYMODE_CHANGED.equals(action)) {
				// ����ģʽ���
				playMode = intent.getIntExtra(GolbalConsts.EXTRA_PLAY_MODE,
						GolbalConsts.PLAY_MODE_LOOP);
			} else if (GolbalConsts.ACTION_STOP_SERVICE.equals(action)) {
				// ֹͣ����
				stopSelf();
			} else if (GolbalConsts.ACTION_ACTIVITY_STARTED.equals(action)) {
				// activity����
				needUpdate = true;
			} else if (GolbalConsts.ACTION_ACTIVITY_STARTED.equals(action)) {
				// activityֹͣ
				needUpdate = false;
			} else if (GolbalConsts.ACTION_SEEK_TO.equals(action)) {
				// ��ת,��ȡ��תλ��
				int progress = intent.getIntExtra(
						GolbalConsts.EXTRA_CURRENT_POSITION, 0);
				int duration = (int) app.getCurrentMusic().getDuration();
				progress = progress * duration / 100;

				// ��ת��ָ��λ�ò���
				seekTo(progress);
			}
		}
	}

}
