package com.tarena.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tarena.entity.Music;
import com.tarena.musicplayer.R;
import com.tarena.utils.BitmapUtils;
import com.tarena.utils.FormatUtils;

public class MusicAdapter extends BaseAdapter {
	private ArrayList<Music> musics;
	private LayoutInflater inflater;

	public void setMusics(ArrayList<Music> musics) {
		if (musics != null)
			this.musics = musics;
		else
			this.musics = new ArrayList<Music>();
	}

	public MusicAdapter(final Context context, ArrayList<Music> musics) {

		this.inflater = LayoutInflater.from(context);
		this.setMusics(musics);

	}

	/**
	 * 使用新的数据集 替换adapter中的数据 并更新listview
	 * 
	 * @param musics
	 */
	public void changeData(ArrayList<Music> musics) {
		// 设置新的数据集
		this.setMusics(musics);
		// 刷新listview
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
		// 加载或复用item界面
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
		// 获取指定位置的数据
		Music m = getItem(position);
		// 将数据写入到item界面
		holder.tvName.setText(m.getName());
		holder.tvAlbum.setText(m.getAlbum());
		holder.tvArtist.setText(m.getArtist());
		holder.tvDuration.setText(FormatUtils.format(m.getDuration()));

		// 获取专辑图片
		Bitmap bm = BitmapUtils.loadBitmap(m.getAlbumPicPath(), 48, 48);
		if (bm != null) {
			holder.ivAlbum.setImageBitmap(bm);
		} else {
			holder.ivAlbum.setImageResource(R.drawable.ic_launcher);
		}
		// 返回item界面
		return convertView;
	}

	private class ViewHolder {
		private ImageView ivAlbum;
		private TextView tvName;
		private TextView tvDuration;
		private TextView tvArtist;
		private TextView tvAlbum;
	}

}
