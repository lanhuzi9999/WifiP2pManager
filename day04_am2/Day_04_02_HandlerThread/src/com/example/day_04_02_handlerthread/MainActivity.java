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
		//����������һ��������Ϣ���еĹ����߳�
		thread = new MyHandlerThread();
		thread.start();
		//����һ��handler �������ù����߳�
		Looper looper = thread.getLooper();
		handler = new Handler(looper) {
			/**
			 * �ڹ����߳��д�����Ϣ�ķ���
			 */
			public void handleMessage(android.os.Message msg) {
				Log.i("info", "��Ϣ���������������߳�:"
						+ Thread.currentThread().getName());
				Log.i("info", "��Ϣ���������յ����ı�����:" + msg.obj);
			};
		};

		// �����߳������̷߳�����Ϣ
		String text = "��Ϣ�ı��������� �߳�:" + Thread.currentThread().getName();
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
