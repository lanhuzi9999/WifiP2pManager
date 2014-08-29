package com.tarena.musicclient;

import com.tarena.bll.AsyncMusicLoader;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MusicDownloadService extends Service {
	private AsyncMusicLoader loader;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		loader = new AsyncMusicLoader(this);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// 获取intent中传递的path
		String path = intent.getStringExtra("path");
		// 下载
		loader.download(path);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
