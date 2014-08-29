package com.tarena.day0101;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView ivPic;
	private final static String PATH = Environment
			.getExternalStorageDirectory() + "/bitmap_test/";
	
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
        String path = PATH+"instance.jpg";
		Bitmap bm = loadBitmap(path, 1);
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
    /*
     * 按指定收缩比例 从指定路径加载位图到内存
     */
	private Bitmap loadBitmap(String path, int scale){
		Options options = new Options();
		options.inSampleSize = scale;
		return BitmapFactory.decodeFile(path, options);
	}
	
	private Bitmap loadBitmap(String path, int width, int height) 
	{
		Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		//计算收缩比例
		int xScale = options.outWidth/width;
		int yScale = options.outHeight/height;
		options.inSampleSize=xScale > yScale ?xScale:yScale;
		options.inJustDecodeBounds = false;
		// 加载图片
		return BitmapFactory.decodeFile(path, options);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
