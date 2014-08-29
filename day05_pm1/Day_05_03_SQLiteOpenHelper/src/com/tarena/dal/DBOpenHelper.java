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
	 * 只在创建数据库文件时执行 可用于执行数据初始化操作 (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("info", "数据库文件创建，初始化数据库");
		db.execSQL("create table stutbl("
				+ "_id integer primary key autoincrement,"
				+ "name text not null," + "sex text not null," + "age integer)");

		ContentValues values = new ContentValues();
		values.put("name", "张飞");
		values.put("sex", "男");
		values.put("age", "16");
		db.insert("stutbl", null, values);
	}

	/*
	 * 当构造方法中的版本号与以创建的数据库文件的版本号不同时 执行此方法，用于升级数据库 (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
