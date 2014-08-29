package com.tarena.day0902;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class MyService extends Service {
	private AppWidgetManager manager;
	private ComponentName provider;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		manager = AppWidgetManager.getInstance(this);
		provider = new ComponentName(this, MyAppWidgetProvider.class);

		new Thread() {
			public void run() {
				for (int i = 599; i >= 0; i--) {
					int min = i / 60;
					int sec = i % 60;
					// 更新桌面部件
					RemoteViews views = new RemoteViews(getPackageName(),
							R.layout.layout_appwidget);
					views.setTextViewText(R.id.tv1, format(min));
					views.setTextViewText(R.id.tv2, format(sec));
					
					manager.updateAppWidget(provider, views);
					// 休眠1s
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			};
		}.start();
	}

	private String format(int numb) {
		return numb < 10 ? "0" + numb : "" + numb;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
