package com.tarena.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {
	public DBOpenHelper(Context context, String dbName) {
		super(context, dbName, null, 2);
	}

	/*
	 * ֻ�ڴ������ݿ��ļ�ʱִ�� ������ִ�����ݳ�ʼ������ (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("info", "���ݿ��ļ���������ʼ�����ݿ�");
		db.execSQL("create table stutbl("
				+ "_id integer primary key autoincrement,"
				+ "name text not null," + "sex text not null," + "age integer)");

		ContentValues values = new ContentValues();
		values.put("name", "�ŷ�");
		values.put("sex", "��");
		values.put("age", "16");
		db.insert("stutbl", null, values);
	}

	/*
	 * �����췽���еİ汾�����Դ��������ݿ��ļ��İ汾�Ų�ͬʱ ִ�д˷����������������ݿ� (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
