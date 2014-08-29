package com.tarena.day1002;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView ivPic;
	private Handler handler;

	private void setupView() {
		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	public void doClick(View v) {
		new Thread() {
			public void run() {
				// 记载图片
				final Bitmap bm = BitmapFactory.decodeResource(getResources(),
						R.drawable.p01);
				// 发送消息到转线程 更新界面
				Runnable callback = new Runnable() {
					@Override
					public void run() {
						Log.i("info", "callback.run()");
						if (bm != null) {
							ivPic.setImageBitmap(bm);
						} else {
							ivPic.setImageResource(R.drawable.ic_launcher);
						}
					}
				};
				handler.post(callback);
			};
		}.start();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupView();
		handler = new Handler();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
