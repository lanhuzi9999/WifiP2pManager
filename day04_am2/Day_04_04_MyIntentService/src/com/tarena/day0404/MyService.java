package com.tarena.day0404;

import android.content.Intent;
import android.util.Log;

public class MyService extends MyIntentService {

	/**
	 * 此方法在工作线程中运行，用于执行具体业务
	 */
	@Override
	public void onHandleIntent(Intent intent) {
		// 获取文件名
		String name = intent.getStringExtra("filename");
		Log.i("info", "开始下载:" + name);
		// 执行下载
		for (int i = 1; i <= 5; i++) {
			Log.i("info", name + "下载中，i=" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
