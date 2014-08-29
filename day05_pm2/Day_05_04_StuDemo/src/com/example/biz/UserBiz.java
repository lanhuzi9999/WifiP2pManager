package com.example.biz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dal.UserDao;
import com.example.entity.User;

public class UserBiz {
	private UserDao dao;

	public UserBiz(Context context) {
		dao = new UserDao(context);
	}

	/**
	 * 判断指定的用户是否存在
	 * 
	 * @param user
	 * @return
	 */
	public boolean exists(User user) {
		return dao.exists(user);
	}
}
