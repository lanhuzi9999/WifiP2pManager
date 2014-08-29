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
		// 打开或创建数据库
		SQLiteDatabase db = openOrCreateDatabase("student.db", MODE_PRIVATE,
				null);

		// 创建表结构
		db.execSQL("create table if not exists stutbl("
				+ "_id integer primary key autoincrement,"
				+ "name text not null," + "sex text not null,"
				+ "age integer not null" + ")");
		// 插入
		int id = 1;
		String name = "张三";
		String sex = "男";
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
			// 操作成功
		}

		values.clear();
		values.put("name", "王老五");
		values.put("sex", "男");
		values.put("age", 58);
		db.insert("stutbl", null, values);
		// 修改
		values.clear();
		values.put("sex", "女");
		values.put("age", 18);
		/*
		 * udpate stutbl set where
		 */
		int count = db.update("stutbl", values, "_id=?", new String[] { "1" });
		if (count != 0) {
			// 修改成功
		}
		// 删除
		/*
		 * delete from 表名 where 条件
		 */
		count = db.delete("stutbl", "name like ?", new String[] { "王%" });
		if (count != 0) {
			// 删除成功
		}

		// 查询
		/*
		 * select 列名 from 表名 where 条件 group by 分组列 having 条件 order by 排序列
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
