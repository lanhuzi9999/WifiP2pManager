package com.tarena.day0403;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	private HandlerThread thread;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 创建并启动一个有消息队列的工作线程
		thread = new HandlerThread("thread_work");
		thread.start();
		// 创建handler 关联到该工作线程
		Looper looper = thread.getLooper();
		handler = new Handler(looper) {
			public void handleMessage(android.os.Message msg) {
				Log.i("info", "消息处理方法运行于线程:" + Thread.currentThread().getName());
			};
		};
		// 在主线程发送消息到工作线程
		Log.i("info", "主线程发送消息到工作线程");
		handler.sendEmptyMessage(0);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		thread.quit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
