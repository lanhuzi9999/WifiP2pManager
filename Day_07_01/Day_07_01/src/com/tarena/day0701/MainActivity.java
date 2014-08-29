package com.tarena.day0701;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.tarena.adapter.MusicAdapter;
import com.tarena.bll.MusicBiz;
import com.tarena.entity.Music;

public class MainActivity extends Activity {
	private ListView lvMusics;
	private MusicBiz musicBiz;
	private MusicAdapter adapter;
	private AlertDialog dialog;
	private InnerReceiver receiver;

	private void setupView() {
		// 获取listview的引用
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		// 获取数据
		ArrayList<Music> musics = musicBiz.getMusics();
		// 创建adapter
		adapter = new MusicAdapter(this, musics);
		// 设置适配器
		lvMusics.setAdapter(adapter);

		// 初始化dialog
		dialog = new Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("提示").setMessage("正在扫描sd卡，请稍候...").create();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		musicBiz = new MusicBiz(this);
		setupView();

		// 注册广播接收器
		receiver = new InnerReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
		filter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
		filter.addDataScheme("file");
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 发送sd卡装在成功广播
		Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
		intent.setData(Uri.parse("file://"
				+ Environment.getExternalStorageDirectory()));
		sendBroadcast(intent);

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 2, 1, "刷新");
		return true;
	}

	private class InnerReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (Intent.ACTION_MEDIA_SCANNER_STARTED.equals(action)) {
				// 开始扫描,显示对话框
				dialog.show();
			} else if (Intent.ACTION_MEDIA_SCANNER_FINISHED.equals(action)) {
				// 扫描结束,关闭对话框，更新listview
				dialog.dismiss();
				adapter.changeData(musicBiz.getMusics());
			}
		}
	}

}
