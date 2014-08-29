package com.tarena.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	public DBOpenHelper(Context context) {
		super(context, "stu.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//����user��
		db.execSQL("create table usertbl("
				+ "_id integer primary key autoincrement,"
				+ "name text not null," + "password text not null" + ")");
		//��user���в����ʼ���� 2 ��
		ContentValues values = new ContentValues();
		values.put("name", "admin");
		values.put("password", "888888");
		db.insert("usertbl", null, values);

		values.clear();
		values.put("name", "wangwu");
		values.put("password", "111111");
		db.insert("usertbl", null, values);
		//����ѧԱ��Ϣ��
		db.execSQL("create table stutbl("
				+ "_id integer primary key autoincrement,"
				+ "name text not null," + "sex text not null,"
				+ "age integer not null)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
