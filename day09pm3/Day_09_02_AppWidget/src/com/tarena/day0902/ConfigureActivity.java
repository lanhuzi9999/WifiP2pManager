package com.tarena.day0902;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RemoteViews;

public class ConfigureActivity extends Activity {
	private int appWidgetId;
	private AppWidgetManager manager;
	private EditText etDuration, etWebSite;

	private void setupView() {
		etDuration = (EditText) findViewById(R.id.etDuration);
		etWebSite = (EditText) findViewById(R.id.etWebSite);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configure);
		appWidgetId = getIntent().getIntExtra(
				AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);
		if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
			setResult(RESULT_CANCELED);
			finish();
		}

		manager = AppWidgetManager.getInstance(this);
		setupView();
	}

	public void doClick(View v) {
		// 获取用户输入
		int number = Integer.parseInt(etDuration.getText().toString());
		String webSite = etWebSite.getText().toString();
		// 初始化桌面小部件实例
		RemoteViews views = new RemoteViews(getPackageName(),
				R.layout.layout_appwidget);
		views.setTextViewText(R.id.tv1, format(number / 60));
		views.setTextViewText(R.id.tv2, format(number % 60));
		PendingIntent intent = PendingIntent.getActivity(this, 0, new Intent(
				Intent.ACTION_VIEW, Uri.parse(webSite)), 0);
		views.setOnClickPendingIntent(R.id.btnTest, intent);

		manager.updateAppWidget(appWidgetId, views);
		// 设置返回值结束Activity
		Intent data = new Intent();
		data.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		setResult(RESULT_OK, data);
		finish();
	}

	private String format(int numb) {
		return numb < 10 ? "0" + numb : "" + numb;
	}
}
