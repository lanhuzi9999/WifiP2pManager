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
		// ��ȡlistview������
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		// ��ȡ����
		ArrayList<Music> musics = musicBiz.getMusics();
		// ����adapter
		adapter = new MusicAdapter(this, musics);
		// ����������
		lvMusics.setAdapter(adapter);

		// ��ʼ��dialog
		dialog = new Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("��ʾ").setMessage("����ɨ��sd�������Ժ�...").create();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		musicBiz = new MusicBiz(this);
		setupView();

		// ע��㲥������
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
		// ����sd��װ�ڳɹ��㲥
		Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
		intent.setData(Uri.parse("file://"
				+ Environment.getExternalStorageDirectory()));
		sendBroadcast(intent);

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 2, 1, "ˢ��");
		return true;
	}

	private class InnerReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (Intent.ACTION_MEDIA_SCANNER_STARTED.equals(action)) {
				// ��ʼɨ��,��ʾ�Ի���
				dialog.show();
			} else if (Intent.ACTION_MEDIA_SCANNER_FINISHED.equals(action)) {
				// ɨ�����,�رնԻ��򣬸���listview
				dialog.dismiss();
				adapter.changeData(musicBiz.getMusics());
			}
		}
	}

}
