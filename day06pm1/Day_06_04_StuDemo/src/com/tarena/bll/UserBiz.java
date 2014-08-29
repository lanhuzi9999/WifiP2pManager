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
	 * �ж�ָ�����û��Ƿ����
	 * 
	 * @param user
	 * @return
	 */
	public boolean exists(User user) {
		return dao.exists(user);
	}
}
