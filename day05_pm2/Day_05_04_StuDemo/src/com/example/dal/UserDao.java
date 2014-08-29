package com.example.dal;

import com.example.entity.User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
	private DBOpenHelper helper;

	public UserDao(Context context) {
		helper = new DBOpenHelper(context);
	}

	/**
	 * 判断指定的用户是否存在
	 * 
	 * @param user
	 * @return
	 */
	public boolean exists(User user) {
		boolean isExists = false;
		//获取数据库访问对象
		SQLiteDatabase db = helper.getReadableDatabase();
		//查询指定的用户名和密码的用户信息
		Cursor c = db.rawQuery(
				"select * from usertbl where name=? and password=?",
				new String[] { user.getName(), user.getPassword() });
		//如果查询到的结果存在,证明用户存在
		if (c != null && c.moveToFirst()) {
			isExists = true;
		}
		//释放资源
		db.close();
		//返回用户是否存在的信息
		return isExists;
	}
}
