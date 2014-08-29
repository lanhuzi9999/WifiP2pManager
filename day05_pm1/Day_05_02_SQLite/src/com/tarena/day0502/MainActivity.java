package com.tarena.day0502;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// �򿪻򴴽����ݿ�
		SQLiteDatabase db = openOrCreateDatabase("student.db", MODE_PRIVATE,
				null);

		// ������ṹ
		db.execSQL("create table if not exists stutbl("
				+ "_id integer primary key autoincrement,"
				+ "name text not null," + "sex text not null,"
				+ "age integer not null" + ")");
		// ����
		int id = 1;
		String name = "����";
		String sex = "��";
		int age = 19;

		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("sex", sex);
		values.put("age", age);
		/*
		 * insert into stutbl (age) values(null)
		 */
		long rowId = db.insert("stutbl", null, values);
		if (rowId != -1) {
			// �����ɹ�
		}

		values.clear();
		values.put("name", "������");
		values.put("sex", "��");
		values.put("age", 58);
		db.insert("stutbl", null, values);
		// �޸�
		values.clear();
		values.put("sex", "Ů");
		values.put("age", 18);
		/*
		 * udpate stutbl set where
		 */
		int count = db.update("stutbl", values, "_id=?", new String[] { "1" });
		if (count != 0) {
			// �޸ĳɹ�
		}
		// ɾ��
		/*
		 * delete from ���� where ����
		 */
		count = db.delete("stutbl", "name like ?", new String[] { "��%" });
		if (count != 0) {
			// ɾ���ɹ�
		}

		// ��ѯ
		/*
		 * select ���� from ���� where ���� group by ������ having ���� order by ������
		 */
		// Cursor c = db.query("stutbl", new String[] { "name", "sex", "age" },
		// "_id>?", new String[] { "0" }, null, null, null);
		Cursor c = db.query("stutbl", null,
				"_id>?", new String[] { "0" }, null, null, null);
		if (c != null) {
			while (c.moveToNext()) {
				String[] cols = c.getColumnNames();
				for (String columnName : cols) {
					Log.i("info",
							columnName + ":"
									+ c.getString(c.getColumnIndex(columnName)));
				}
			}
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
