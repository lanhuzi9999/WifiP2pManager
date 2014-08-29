package com.example.day_04_02_handlerthread;

import android.os.Looper;
import android.util.Log;

public class MyHandlerThread extends Thread {
	private Looper looper;

	public Looper getLooper() {
		if (!this.isAlive()) {
			return null;
		}
		if (looper == null) {
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return looper;
	}

	@Override
	public void run() {
		Log.i("info", "工作线程启动");
		// 创建消息队列
		Looper.prepare();
		looper = Looper.myLooper();
		synchronized (this) {
			try {
				this.notify();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Looper.loop();

		Log.i("info", "工作线程结束");
	}

	public void quit() {
		looper.quit();
	}
}
