package com.tarena.day0405;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat.Builder;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private NotificationManager manager;

	public void doClick(View v) {
		// 创建通知对象
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);

		Builder builder = new Builder(this)
				.setSmallIcon(android.R.drawable.ic_notification_overlay)
				.setContentTitle("重要通知")
				.setContentText("您的手机将在10s内爆炸，请看着办吧...")
				.setLargeIcon(
						BitmapFactory.decodeResource(getResources(),
								R.drawable.ic_launcher)).setContentInfo("1")
				.setTicker("提示").setAutoCancel(false)
				.setContentIntent(contentIntent)
				.setDefaults(Notification.DEFAULT_LIGHTS);

		// Notification noti = builder.build();
		// 发送通知
		manager.notify(0, builder.build());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		manager.cancel(0);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取通知服务
		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
