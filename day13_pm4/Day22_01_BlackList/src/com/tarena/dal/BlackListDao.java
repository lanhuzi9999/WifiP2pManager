package com.tarena.dal;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BlackListDao {
	private DBOpenHelper helper;

	public BlackListDao(Context context) {
		helper = new DBOpenHelper(context);
	}

	/**
	 * 查询黑名单中的所有电话号码
	 * 
	 * @return
	 */
	public ArrayList<String> getBlackList() {
		ArrayList<String> list = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select number from blacklistTbl", null);
		if (c != null) {
			list = new ArrayList<String>();
			while (c.moveToNext()) {
				list.add(c.getString(0));
			}
			c.close();
		}
		db.close();
		return list;
	}

	/**
	 * 向黑名单表中插入数据
	 * 
	 * @param numb
	 * @return
	 */
	public long addNumber(String numb) {
		long rowId = -1;
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("number", numb);
		rowId = db.insert("blacklistTbl", null, values);
		db.close();
		return rowId;
	}

	/**
	 * 将号码从黑名单中移除
	 * 
	 * @param numb
	 * @return
	 */
	public int removeNumber(String numb) {
		int count = 0;
		SQLiteDatabase db = helper.getWritableDatabase();
		count = db.delete("blacklistTbl", "number=?", new String[] { numb });
		db.close();
		return count;
	}

	/**
	 * 查询指定号码是否存在于黑名单中
	 * 
	 * @param numb
	 * @return
	 */
	public boolean exists(String numb) {
		boolean isExists = false;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from blacklistTbl where number=?",
				new String[] { numb });
		if (c != null && c.moveToFirst()) {
			isExists = true;
		}
		db.close();
		return isExists;
	}
}
