package com.tarena.day0101;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView ivPic;

	/**
	 * �����ʼ������
	 */
	private void setupView() {
		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	/**
	 * ��ť�ĵ����¼�������
	 * 
	 * @param v
	 */
	public void doClick(View v) throws IOException {
		// ����λͼ
		// Bitmap bm = loadBitmap("/mnt/sdcard/Pictures/p01.jpg");
		// FileInputStream is = new
		// FileInputStream("/mnt/sdcard/Pictures/p01.jpg");
		// Bitmap bm = loadBitmap(is);

		// FileInputStream is = new
		// FileInputStream("/mnt/sdcard/Pictures/p01.jpg");
		// ByteArrayOutputStream out = new ByteArrayOutputStream();
		// int len = -1;
		// byte[] buffer = new byte[1024];
		// while ((len = is.read(buffer)) != -1) {
		// out.write(buffer, 0, len);
		// out.flush();
		// }
		// out.close();
		// is.close();
		//
		// Bitmap bm = loadBitmap(out.toByteArray());

		// Bitmap bm = loadBitmap(android.R.drawable.ic_menu_mylocation);

		// Bitmap bm = loadBitmap("/mnt/sdcard/Pictures/p01.jpg", 2);
		Bitmap bm = loadBitmap("/mnt/sdcard/Pictures/p01.jpg", 200, 200);
		// ��ʾͼƬ
		if (bm != null) {
			ivPic.setImageBitmap(bm);
		} else {
			ivPic.setImageResource(R.drawable.ic_launcher);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupView();
	}

	/**
	 * ��ָ��·������λͼ���ڴ�
	 * 
	 * @param path
	 * @return
	 */
	private Bitmap loadBitmap(String path) {
		Bitmap bm = BitmapFactory.decodeFile(path);
		return bm;
	}

	/**
	 * ���������м���λͼ����
	 * 
	 * @param is
	 * @return
	 */
	private Bitmap loadBitmap(InputStream is) {
		return BitmapFactory.decodeStream(is);
	}

	/**
	 * ���ֽ��������λͼ����
	 * 
	 * @param data
	 * @return
	 */
	private Bitmap loadBitmap(byte[] data) {
		return BitmapFactory.decodeByteArray(data, 0, data.length);
	}

	/**
	 * �Ӷ�������Դ�м���λͼ
	 * 
	 * @param resId
	 * @return
	 */
	private Bitmap loadBitmap(int resId) {
		return BitmapFactory.decodeResource(getResources(), resId);
	}

	/**
	 * ��ָ���������� ��ָ��·������λͼ���ڴ�
	 * 
	 * @param path
	 * @param scale
	 * @return
	 */
	private Bitmap loadBitmap(String path, int scale) {
		Options opts = new Options();
		opts.inSampleSize = scale;// ������������
		return BitmapFactory.decodeFile(path, opts);
	}

	private Bitmap loadBitmap(String path, int width, int height) {
		Options opts = new Options();
		opts.inJustDecodeBounds = true;// ���ý�����ͼƬ�߽���Ϣ
		BitmapFactory.decodeFile(path, opts);// �����ر߽���Ϣ����ʵ�ʼ���ͼƬ
		// ������������
		int xScale = opts.outWidth / width;
		int yScale = opts.outHeight / height;
		opts.inSampleSize = xScale > yScale ? xScale : yScale;
		opts.inJustDecodeBounds = false;
		// ����ͼƬ
		return BitmapFactory.decodeFile(path, opts);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
