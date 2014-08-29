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
				Log.i("info", "��Ϣ����");

				// ����Ϣ�л�ȡͼƬ�����������
				ImageLoadTask task = (ImageLoadTask) msg.obj;
				Log.i("info", "task.path=" + task.path);
				Log.i("info", "task.bitmap=" + task.bitmap);
				// ��ʾͼƬ
				ImageView iv = (ImageView) lvMusics.findViewWithTag(task.path);
				Log.i("info", "imageview=" + iv);
				if (iv != null && task.bitmap != null) {
					iv.setImageBitmap(task.bitmap);
					Log.i("info", "��ʾͼƬ");
				}
			};
		};
		this.workThread = new Thread() {
			public void run() {
				Log.i("info", "�����߳̿�ʼ����");
				while (isLoop) {
					// ������ƻ���Ϊ��ʱ����ѯ���񼯺ϣ�ִ��ͼƬ��������
					while (!tasks.isEmpty()) {
						try {

							// �Ƴ�����ȡ��һ������
							ImageLoadTask task = tasks.remove(0);
							Log.i("info", "��ʼ����ͼƬ: path=" + task.path);
							// ִ��ͼƬ��������
							HttpEntity entity = HttpUtils.getEntity(
									GlobalConsts.BASE_URL + task.path, null,
									HttpUtils.METHOD_GET);
							byte[] data = HttpUtils.getBytes(entity);
							task.bitmap = BitmapUtils.loadBitmap(data, 64, 64);

							// ������Ϣ�����߳�
							Message msg = Message.obtain(handler, 0, task);
							msg.sendToTarget();
							Log.i("info", "������ɣ�������Ϣ�����߳�:path=" + task.path);

							// ���浽���漯��
							caches.put(task.path, new SoftReference<Bitmap>(
									task.bitmap));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// �̵߳ȴ�
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				Log.i("info", "�����߳̽�������");
			};

		};
		this.workThread.start();
	}

	/**
	 * ʹ���µ����ݼ� �滻adapter�е����� ������listview
	 * 
	 * @param musics
	 */
	public void changeData(ArrayList<Music> musics) {
		// �����µ����ݼ�
		this.setMusics(musics);
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
		// ��ȡָ��λ�õ�����
		Music m = getItem(position);
		// ������д�뵽item����
		holder.tvName.setText(m.getName());
		holder.tvAlbum.setText(m.getAlbum());
		holder.tvArtist.setText(m.getArtist());
		holder.tvDuration.setText(m.getDuration());

		// ��ʾĬ��ͼƬ
		holder.ivAlbum.setImageResource(R.drawable.ic_launcher);

		String path = m.getAlbumPicPath();
		holder.ivAlbum.setTag(path);

		// ��������д��ڸ�·����ͼƬ
		if (caches.containsKey(path)) {
			// �ӻ����л�ȡͼƬ
			Bitmap bm = caches.get(path).get();
			if (bm != null) {
				holder.ivAlbum.setImageBitmap(bm);
				return convertView;
			}
		}
		// ����ͼƬ��������
		ImageLoadTask task = new ImageLoadTask();
		task.path = path;
		// ������񼯺��в����ڸ���������ӵ����񼯺�
		if (!tasks.contains(task)) {
			Log.i("info", "����������񼯺�:path=" + task.path);
			tasks.add(task);
			// �����߳�
			synchronized (workThread) {
				try {
					workThread.notify();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		// ����item����
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
	 * ͼƬ����������
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
