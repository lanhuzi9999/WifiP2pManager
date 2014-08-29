package com.tarena.adapter;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;

import com.tarena.entity.Music;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MusicAdapter extends BaseAdapter {
	private ArrayList<Music> musics;
	private LayoutInflater inflater;
	private boolean isLoop;
	private ArrayList<ImageLoadTask> tasks;
	private Handler handler;
	private Thread workThread;
	private HashMap<String, SoftReference<Bitmap>> caches;

	public MusicAdapter(Context context, ArrayList<Music> muiscs,
			final ListView lvMusics) {

	}

	public void changeData(ArrayList<Music> musics) {

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	class ImageLoadTask {
		private String path;
		private Bitmap bitmap;

		@Override
		public boolean equals(Object o) {
			// TODO Auto-generated method stub
			return super.equals(o);
		}
	}

}
