package com.tarena.day0501;

import java.io.File;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// File file = getDatabasePath("user.db");
		// Log.i("info", "exists=" + file.exists());
		// getDir("db", MODE_PRIVATE);
		// getFilesDir();
		// getCacheDir();
		// getExternalCacheDir();
		//
		// Environment.getDataDirectory();
		// ��ȡ�����ݿ��ļ��Ƿ���ڵ���Ϣ
		boolean isExists = getDatabasePath("user.db").exists();
		// �򿪻򴴽����ݿ�,���������ݿ���ʶ���
		SQLiteDatabase db = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
		if (!isExists) {
			// �������ݿ�
			db.execSQL("create table if not exists usertbl("
					+ "_id integer primary key autoincrement,"
					+ "name text not null," + "password text not null" + ")");
			// ��������
			db.execSQL("insert into usertbl(name,password)"
					+ "values ('admin','888888')");
			db.execSQL("insert into usertbl(name,password)"
					+ "values ('zhangsan','000000')");
			db.execSQL("insert into usertbl(name,password)"
					+ "values ('lisi','111111')");

			// �޸�
			db.execSQL("update usertbl set password='123456' where _id=1");

			// ɾ��
			db.execSQL("delete from usertbl where _id=3");

		}
		// ��ѯ
		Cursor c = db.rawQuery("select * from usertbl where _id>?",
				new String[] { "1" });

		// ������ѯ���
		if (c != null) {
			// ������
			while (c.moveToNext()) {
				// ������
				String[] cols = c.getColumnNames();
				for (String columnName : cols) {
					Log.i("info",
							columnName + ":"
									+ c.getString(c.getColumnIndex(columnName)));
				}
				Log.i("info", "----------------------------");
				// int id = c.getInt(c.getColumnIndex("_id"));
				// String name = c.getString(c.getColumnIndex("name"));
				// String password = c.getString(c.getColumnIndex("password"));
				//
				// Log.i("info", id + "," + name + "," + password);
			}
			// c.getCount();
			// c.getColumnCount();
			// c.getColumnNames()
			// c.getColumnName(columnIndex)
			// c.getColumnIndex(columnName)

			c.close();
		}
		db.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
