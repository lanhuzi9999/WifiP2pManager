package com.tarena.adapter;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

import com.tarena.bll.ImageBiz;
import com.tarena.day1005.R;
import com.tarena.entity.ImageInfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private ArrayList<ImageInfo> infos;
	private LayoutInflater inflater;
	private ImageBiz imgBiz;

	public void setInfos(ArrayList<ImageInfo> infos) {
		if (infos != null)
			this.infos = infos;
		else
			this.infos = new ArrayList<ImageInfo>();
	}

	public ImageAdapter(Context context, ArrayList<ImageInfo> infos) {
		this.setInfos(infos);
		this.inflater = LayoutInflater.from(context);
		this.imgBiz = new ImageBiz(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return infos.size();
	}

	@Override
	public ImageInfo getItem(int position) {
		// TODO Auto-generated method stub
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.ivThumb = (ImageView) convertView
					.findViewById(R.id.ivThumbnail);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTtitle);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ImageInfo info = getItem(position);

		holder.tvTitle.setText(info.getTitle());

		Bitmap bm = info.getThumbnail().get();
		//如果缓存的缩略图被释放，则重新加载
		if (bm == null) {
			bm = imgBiz.getThumbnail(info.getId());
			info.setThumbnail(new SoftReference<Bitmap>(bm));
		}

		holder.ivThumb.setImageBitmap(bm);

		return convertView;
	}

	class ViewHolder {
		private ImageView ivThumb;
		private TextView tvTitle;
	}

}
