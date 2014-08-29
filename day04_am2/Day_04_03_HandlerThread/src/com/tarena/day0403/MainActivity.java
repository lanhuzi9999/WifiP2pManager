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
		// ����������һ������Ϣ���еĹ����߳�
		thread = new HandlerThread("thread_work");
		thread.start();
		// ����handler �������ù����߳�
		Looper looper = thread.getLooper();
		handler = new Handler(looper) {
			public void handleMessage(android.os.Message msg) {
				Log.i("info", "��Ϣ�������������߳�:" + Thread.currentThread().getName());
			};
		};
		// �����̷߳�����Ϣ�������߳�
		Log.i("info", "���̷߳�����Ϣ�������߳�");
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
