package com.tarena.day0404;

import android.content.Intent;
import android.util.Log;

public class MyService extends MyIntentService {

	/**
	 * �˷����ڹ����߳������У�����ִ�о���ҵ��
	 */
	@Override
	public void onHandleIntent(Intent intent) {
		// ��ȡ�ļ���
		String name = intent.getStringExtra("filename");
		Log.i("info", "��ʼ����:" + name);
		// ִ������
		for (int i = 1; i <= 5; i++) {
			Log.i("info", name + "�����У�i=" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
