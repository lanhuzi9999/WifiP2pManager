package com.tarena.musicplayer;

import java.util.ArrayList;

import com.tarena.bll.MusicBiz;
import com.tarena.entity.Music;

import android.app.Application;

public class MusicApplication extends Application {
	private int currentIndex;
	private ArrayList<Music> playList;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// ��ѯ����ý����������Ƶ��Ϣ
		// ����Ϊ�����б�
		setPlayList(new MusicBiz(this).getMusics());
		// ��ʼ��currentIndex
		setCurrentIndex(0);
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public int getPlayListSize() {
		return playList.size();
	}

	public void setCurrentIndex(int currentIndex) {
		if (currentIndex >= 0 && currentIndex < playList.size())
			this.currentIndex = currentIndex;
		else
			this.currentIndex = -1;
	}

	public ArrayList<Music> getPlayList() {
		return playList;
	}

	public void setPlayList(ArrayList<Music> playList) {
		if (playList != null)
			this.playList = playList;
		else
			this.playList = new ArrayList<Music>();
	}

	public Music getCurrentMusic() {
		if (currentIndex >= 0 && currentIndex < playList.size()) {
			return playList.get(currentIndex);
		}
		return null;
	}

}
