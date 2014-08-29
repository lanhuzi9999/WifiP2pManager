package com.tarena.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tarena.bll.AsyncImageLoader;
import com.tarena.bll.AsyncImageLoader.Callback;
import com.tarena.entity.Music;
import com.tarena.musicclient.R;

public class MusicAdapter extends BaseAdapter {
	private AsyncImageLoader loader;
	private ArrayList<Music> musics;
	private LayoutInflater inflater;

	public void quit() {
		loader.quit();
	}

	public void setMusics(ArrayList<Music> musics) {
		if (musics != null)
			this.musics = musics;
		else
			this.musics = new ArrayList<Music>();
	}

	public MusicAdapter(final Context context, ArrayList<Music> musics,
			final ListView lvMusics) {

		this.inflater = LayoutInflater.from(context);
		this.setMusics(musics);
		this.loader = new AsyncImageLoader(context, new Callback() {

			@Override
			public void imageLoaded(String path, Bitmap bitmap) {
				// ����ͼƬ·�� ���Һ��ʵ�iamgeview
				ImageView iv = (ImageView) lvMusics.findViewWithTag(path);
				// �����������ʾͼƬ
				if (iv != null && bitmap != null) {
					iv.setImageBitmap(bitmap);
				}
			}
		});
	}

	/**
	 * ʹ���µ����ݼ� �滻adapter�е����� ������listview
	 * 
	 * @param musics
	 */
	public void appendData(ArrayList<Music> musics) {
		// �����µ����ݼ�
		this.musics.addAll(musics);
		// ˢ��listview
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return musics.size();
	}

	@Override
	public Music getItem(int position) {
		// TODO Auto-generated method stub
		return musics.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ���ػ���item����
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_music, null);
			holder = new ViewHolder();
			holder.ivAlbum = (ImageView) convertView
					.findViewById(R.id.ivAlbumPic);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvAlbum = (TextView) convertView.findViewById(R.id.tvAlbum);
			holder.tvArtist = (TextView) convertView
					.findViewById(R.id.tvArtist);
			holder.tvDuration = (TextView) convertView
					.findViewById(R.id.tvDuration);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (isFling == false) {
			// ��ȡָ��λ�õ�����
			Music m = getItem(position);
			// ������д�뵽item����
			holder.tvName.setText(m.getName());
			holder.tvAlbum.setText(m.getAlbum());
			holder.tvArtist.setText(m.getArtist());
			holder.tvDuration.setText(m.getDuration());

			// ��ȡר��ͼƬ·��
			String path = m.getAlbumPicPath();
			holder.ivAlbum.setTag(path);
			// ��ȡͼƬ
			Bitmap bm = loader.loadImage(path);
			if (bm != null) {
				holder.ivAlbum.setImageBitmap(bm);
			} else {
				holder.ivAlbum.setImageResource(R.drawable.ic_launcher);
			}
		}

		// ����item����
		return convertView;
	}

	private boolean isFling;

	public void setFling(boolean isFling) {
		this.isFling = isFling;
		this.notifyDataSetChanged();
	}

	private class ViewHolder {
		private ImageView ivAlbum;
		private TextView tvName;
		private TextView tvDuration;
		private TextView tvArtist;
		private TextView tvAlbum;
	}

}
