package com.tarena.musicclient;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import com.tarena.adapter.MusicAdapter;
import com.tarena.bll.AsyncMusicParseTask;
import com.tarena.entity.Music;
import com.tarena.utils.GlobalConsts;

public class MainActivity extends Activity {
	private static final int CTX_MENU_ID_DETAILS = 1;
	private static final int CTX_MENU_ID_DOWNLOAD = 2;
	private static final int OPTS_MENU_ID_EXIT = 10;
	private ListView lvMusics;
	private MusicAdapter adapter;
	private AlertDialog dialog;

	/**
	 * ��ʼ������
	 */
	private void setupView() {
		// ��ȡlistview������
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		// ����adapter
		adapter = new MusicAdapter(this, null, lvMusics);
		// ����adapter
		lvMusics.setAdapter(adapter);

		// �첽���غͽ���xml
		new AsyncMusicParseTask(this).execute(GlobalConsts.BASE_URL
				+ "sounds.xml");
		// ����dialog
		dialog = new Builder(this).setIcon(android.R.drawable.ic_dialog_info)
				.setTitle("����").setPositiveButton("ȷ��", null).create();
	}

	private void addListener() {
		lvMusics.setOnScrollListener(new OnScrollListener() {
			private int lastPosition;

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (scrollState == OnScrollListener.SCROLL_STATE_FLING) {
					adapter.setFling(true);
				} else {
					if (lastPosition == adapter.getCount() - 1) {
						new AsyncMusicParseTask(MainActivity.this)
								.execute(GlobalConsts.BASE_URL + "sounds.xml");
					}
					adapter.setFling(false);
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				lastPosition = firstVisibleItem + visibleItemCount - 1;
			}
		});

		lvMusics.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				menu.setHeaderIcon(android.R.drawable.ic_dialog_info)
						.setHeaderTitle("����");

				menu.add(2, CTX_MENU_ID_DETAILS, 1, "����");
				menu.add(2, CTX_MENU_ID_DOWNLOAD, 2, "����");
			}
		});
	}

	/**
	 * ����listView
	 * 
	 * @param musics
	 */
	public void updateListView(ArrayList<Music> musics) {
		// ����listview
		adapter.appendData(musics);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupView();
		addListener();
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// ��ȡ��������������
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		Music m = adapter.getItem(info.position);
		// ִ�о������
		switch (item.getItemId()) {
		case CTX_MENU_ID_DETAILS:// ����
			dialog.setMessage(m.toString());
			dialog.show();
			break;
		case CTX_MENU_ID_DOWNLOAD:// ����
			// ��ȡ����·��
			String path = m.getMusicPath();
			// �첽����
			Intent intent = new Intent(this, MusicDownloadService.class);
			intent.putExtra("path", path);
			startService(intent);
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case OPTS_MENU_ID_EXIT:// �˳�
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(1, OPTS_MENU_ID_EXIT, 1, "�˳�");
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		adapter.quit();
	}

}
