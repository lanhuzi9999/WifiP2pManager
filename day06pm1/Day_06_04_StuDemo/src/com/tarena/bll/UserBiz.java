package com.tarena.bll;

import android.content.Context;

import com.tarena.dal.UserDao;
import com.tarena.entity.User;

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
