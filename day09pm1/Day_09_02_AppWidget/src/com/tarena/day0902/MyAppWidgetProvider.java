package com.tarena.day0902;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class MyAppWidgetProvider extends AppWidgetProvider {
	@Override
	public void onEnabled(Context context) {
		// ��������
		Intent service = new Intent(context, MyService.class);
		context.startService(service);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// ��ʼ�����沿��
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.layout_appwidget);
		views.setOnClickPendingIntent(R.id.btnTest, PendingIntent.getActivity(
				context,
				0,
				new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://www.baidu.com")), 0));

		appWidgetManager.updateAppWidget(appWidgetIds, views);
	}
}
