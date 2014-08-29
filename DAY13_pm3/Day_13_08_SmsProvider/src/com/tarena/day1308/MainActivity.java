package com.tarena.day1308;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private ContentResolver cr;

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnShowConversations:
			showConversations();
			break;

		case R.id.btnShowSms:
			showSms(2);
			break;
		}
	}

	private void showConversations() {
		// ׼��uri
		Uri uri = Uri.parse("content://sms/conversations/");
		// ׼����ѯ��
		String[] projection = { "groups.group_thread_id as thread_id",
				"groups.msg_count as msg_count",
				"groups.group_date as last_date", "sms.body as body",
				"sms.address as address" };
		// ִ�в�ѯ
		Cursor c = cr.query(uri, projection, null, null, null);
		// �����ѯ���
		showCursorInfo(c);
	}

	private void showSms(int threadId) {
		// ׼��Uri
		Uri uri = Uri.parse("content://sms/");
		// ׼����ѯ��
		String[] projection = { "_id", "address", "date", "body", "type" };
		// ִ�в�ѯ
		Cursor c = cr.query(uri, projection, "thread_id=" + threadId, null,
				null);
		// ��ʾ��ѯ���
		showCursorInfo(c);
	}

	private void showSms() {
		// ׼��Uri
		Uri uri = Uri.parse("content://sms/");
		// ׼����ѯ��
		String[] projection = { "_id", "address", "date", "body", "type" };
		// ִ�в�ѯ
		Cursor c = cr.query(uri, projection, null, null, null);
		// ��ʾ��ѯ���
		showCursorInfo(c);
	}

	private void showCursorInfo(Cursor c) {
		if (c != null) {
			String[] cols = c.getColumnNames();
			while (c.moveToNext()) {
				for (String col : cols) {
					Log.i("info",
							col + ":" + c.getString(c.getColumnIndex(col)));
				}
				Log.i("info", "----------------");
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cr = getContentResolver();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
