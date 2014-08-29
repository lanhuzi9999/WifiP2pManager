package com.tarena.musicplayer;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.tarena.entity.Music;
import com.tarena.utils.GolbalConsts;

public class MusicWidgetProvider extends AppWidgetProvider {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (GolbalConsts.ACTION_CURRENT_MUSIC_CHANGED
				.equals(intent.getAction())) {
			// 获取音乐信息
			MusicApplication app = (MusicApplication) context
					.getApplicationContext();
			Music m = app.getCurrentMusic();
			// 显示音乐信息,更新桌面部件
			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			ComponentName provider = new ComponentName(context,
					MusicWidgetProvider.class);
			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.layout_appwidget);
			views.setTextViewText(R.id.tvName_Widget, m.getName());
			views.setTextViewText(R.id.tvSinger_Widget, m.getArtist());
			manager.updateAppWidget(provider, views);
			return;
		} else if (GolbalConsts.ACTION_STOP_SERVICE.equals(intent.getAction())) {
			// 更新桌面部件
			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			ComponentName provider = new ComponentName(context,
					MusicWidgetProvider.class);
			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.layout_appwidget);
			views.setTextViewText(R.id.tvName_Widget, "音乐名");
			views.setTextViewText(R.id.tvSinger_Widget, "歌手");
			manager.updateAppWidget(provider, views);
			return;
		} else if ("com.tarena.action.START_SERVICE".equals(intent.getAction())) {
			// 启动Service
			Intent service = new Intent(context, MusicPlayService.class);
			context.startService(service);
			// 发送音乐播放广播
			Intent broadcast = new Intent(GolbalConsts.ACTION_PLAY);
			context.sendStickyBroadcast(broadcast);
		}
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.layout_appwidget);
		views.setOnClickPendingIntent(R.id.btnPlay_Widget, PendingIntent
				.getBroadcast(context, 0, new Intent(
						"com.tarena.action.START_SERVICE"), 0));
		views.setOnClickPendingIntent(R.id.btnPause_Widget, PendingIntent
				.getBroadcast(context, 1,
						new Intent(GolbalConsts.ACTION_PAUSE), 0));
		views.setOnClickPendingIntent(R.id.btnPrevious_Widget, PendingIntent
				.getBroadcast(context, 2, new Intent(
						GolbalConsts.ACTION_PREVIOUS), 0));
		views.setOnClickPendingIntent(R.id.btnNext_Widget, PendingIntent
				.getBroadcast(context, 3, new Intent(GolbalConsts.ACTION_NEXT),
						0));
		views.setOnClickPendingIntent(R.id.btnList_Widget, PendingIntent
				.getActivity(context, 4,
						new Intent(context, MainActivity.class), 0));

		appWidgetManager.updateAppWidget(appWidgetIds, views);
	}
}
