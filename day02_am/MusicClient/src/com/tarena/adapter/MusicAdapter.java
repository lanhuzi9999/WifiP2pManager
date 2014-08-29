package com.tarena.adapter;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;

import com.tarena.entity.Music;
import com.tarena.musicclient.R;
import com.tarena.utils.BitmapUtils;
import com.tarena.utils.GlobalConsts;
import com.tarena.utils.HttpUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MusicAdapter extends BaseAdapter {
	private ArrayList<Music> musics;
	private LayoutInflater inflater;
	private ArrayList<ImageLoadTask> tasks;
	private Thread workThread;
	private Handler handler;
	private boolean isLoop;
	private HashMap<String, SoftReference<Bitmap>> caches;

	public void setMusics(ArrayList<Music> musics) {
		if (musics != null)
			this.musics = musics;
		else
			this.musics = new ArrayList<Music>();
	}

	public MusicAdapter(Context context, ArrayList<Music> musics,
			final ListView lvMusics) {
		this.inflater = LayoutInflater.from(context);
		this.setMusics(musics);

		this.caches = new HashMap<String, SoftReference<Bitmap>>();
		this.isLoop = true;
		this.tasks = new ArrayList<MusicAdapter.ImageLoadTask>();
		this.handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				Log.i("info", "消息处理");

				// 从消息中获取图片加载任务对象
				ImageLoadTask task = (ImageLoadTask) msg.obj;
				Log.i("info", "task.path=" + task.path);
				Log.i("info", "task.bitmap=" + task.bitmap);
				// 显示图片
				ImageView iv = (ImageView) lvMusics.findViewWithTag(task.path);
				Log.i("info", "imageview=" + iv);
				if (iv != null && task.bitmap != null) {
					iv.setImageBitmap(task.bitmap);
					Log.i("info", "显示图片");
				}
			};
		};
		this.workThread = new Thread() {
			public void run() {
				Log.i("info", "工作线程开始运行");
				while (isLoop) {
					// 当任务计划不为空时，轮询任务集合，执行图片加载任务
					while (!tasks.isEmpty()) {
						try {

							// 移除并获取第一条任务
							ImageLoadTask task = tasks.remove(0);
							Log.i("info", "开始加载图片: path=" + task.path);
							// 执行图片加载任务
							HttpEntity entity = HttpUtils.getEntity(
									GlobalConsts.BASE_URL + task.path, null,
									HttpUtils.METHOD_GET);
							byte[] data = HttpUtils.getBytes(entity);
							task.bitmap = BitmapUtils.loadBitmap(data, 64, 64);

							// 发送消息到主线程
							Message msg = Message.obtain(handler, 0, task);
							msg.sendToTarget();
							Log.i("info", "下载完成，发送消息到主线程:path=" + task.path);

							// 保存到缓存集合
							caches.put(task.path, new SoftReference<Bitmap>(
									task.bitmap));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// 线程等待
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				Log.i("info", "工作线程结束运行");
			};

		};
		this.workThread.start();
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
		holder.tvDuration.setText(m.getDuration());

		// 显示默认图片
		holder.ivAlbum.setImageResource(R.drawable.ic_launcher);

		String path = m.getAlbumPicPath();
		holder.ivAlbum.setTag(path);

		// 如果缓存中存在该路径的图片
		if (caches.containsKey(path)) {
			// 从缓存中获取图片
			Bitmap bm = caches.get(path).get();
			if (bm != null) {
				holder.ivAlbum.setImageBitmap(bm);
				return convertView;
			}
		}
		// 创建图片加载任务
		ImageLoadTask task = new ImageLoadTask();
		task.path = path;
		// 如果任务集合中不存在该任务，则添加到任务集合
		if (!tasks.contains(task)) {
			Log.i("info", "添加任务到任务集合:path=" + task.path);
			tasks.add(task);
			// 唤醒线程
			synchronized (workThread) {
				try {
					workThread.notify();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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

	/**
	 * 图片加载任务类
	 * 
	 * @author Administrator
	 * 
	 */
	private class ImageLoadTask {
		private String path;
		private Bitmap bitmap;

		@Override
		public boolean equals(Object o) {
			ImageLoadTask task = (ImageLoadTask) o;
			return path.equals(task.path);
		}
	}
}
