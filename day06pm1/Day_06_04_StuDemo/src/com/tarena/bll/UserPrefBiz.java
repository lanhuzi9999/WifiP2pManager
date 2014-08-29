package com.tarena.bll;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserPrefBiz {
	private static final String KEY = "userName";
	private SharedPreferences pref;

	public UserPrefBiz(Context context) {
		// pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
		pref = PreferenceManager.getDefaultSharedPreferences(context);
	}

	/**
	 * 从偏好设置文件中读取用户名
	 * 
	 * @return
	 */
	public String getUserName() {
		return pref.getString(KEY, null);
	}

	/**
	 * 保存用户名到偏好设置文件
	 * 
	 * @param name
	 */
	public void saveUserName(String name) {
		pref.edit().putString(KEY, name).commit();
	}

	/**
	 * 从偏好设置文件中移除用户名
	 */
	public void removeUserName() {
		pref.edit().remove(KEY).commit();
	}
}
