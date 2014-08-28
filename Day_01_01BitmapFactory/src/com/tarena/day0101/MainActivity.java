package com.tarena.day0101;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView ivPic;

	/**
	 * 界面初始化方法
	 */
	private void setupView() {
		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	/**
	 * 按钮的单击事件处理方法
	 * 
	 * @param v
	 */
	public void doClick(View v) throws IOException {
		// 加载位图
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

		Bitmap bm = loadBitmap(android.R.drawable.ic_menu_mylocation);
		// 显示图片
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
	 * 从指定路径加载位图到内存
	 * 
	 * @param path
	 * @return
	 */
	private Bitmap loadBitmap(String path) {
		Bitmap bm = BitmapFactory.decodeFile(path);
		return bm;
	}

	/**
	 * 从输入流中加载位图对象
	 * 
	 * @param is
	 * @return
	 */
	private Bitmap loadBitmap(InputStream is) {
		return BitmapFactory.decodeStream(is);
	}

	/**
	 * 从字节数组加载位图对象
	 * 
	 * @param data
	 * @return
	 */
	private Bitmap loadBitmap(byte[] data) {
		return BitmapFactory.decodeByteArray(data, 0, data.length);
	}

	/**
	 * 从二进制资源中加载位图
	 * 
	 * @param resId
	 * @return
	 */
	private Bitmap loadBitmap(int resId) {
		return BitmapFactory.decodeResource(getResources(), resId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
