package com.example.day_04_02_handlerthread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	private Handler handler;
	private MyHandlerThread thread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//创建并启动一个带有消息队列的工作线程
		thread = new MyHandlerThread();
		thread.start();
		//创建一个handler 关联到该工作线程
		Looper looper = thread.getLooper();
		handler = new Handler(looper) {
			/**
			 * 在工作线程中处理消息的方法
			 */
			public void handleMessage(android.os.Message msg) {
				Log.i("info", "消息处理方法，运行于线程:"
						+ Thread.currentThread().getName());
				Log.i("info", "消息处理方法中收到的文本内容:" + msg.obj);
			};
		};

		// 在主线程向工作线程发送消息
		String text = "消息文本，创建于 线程:" + Thread.currentThread().getName();
		Message msg = Message.obtain();
		msg.obj = text;
		handler.sendMessage(msg);

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
