package com.tarena.appwidget;

import com.tarena.day0901.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

public class MyAppWidgetProvider extends AppWidgetProvider {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("info", intent.getAction());
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.layout_appwidget);
		views.setTextViewText(R.id.tv1, "HE");
		views.setTextViewText(R.id.tv2, "HX");
		views.setOnClickPendingIntent(
				R.id.btnTest,
				PendingIntent.getActivity(context, 0, new Intent(
						Intent.ACTION_VIEW, Uri.parse("http://www.163.com")), 0));
		appWidgetManager.updateAppWidget(appWidgetIds, views);
		Log.i("info", "onUpdate");
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Log.i("info", "onDeleted");
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		Log.i("info", "onEnabled");
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
		Log.i("info", "onDisabled");
	}

}
