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
	 * ��ƫ�������ļ��ж�ȡ�û���
	 * 
	 * @return
	 */
	public String getUserName() {
		return pref.getString(KEY, null);
	}

	/**
	 * �����û�����ƫ�������ļ�
	 * 
	 * @param name
	 */
	public void saveUserName(String name) {
		pref.edit().putString(KEY, name).commit();
	}

	/**
	 * ��ƫ�������ļ����Ƴ��û���
	 */
	public void removeUserName() {
		pref.edit().remove(KEY).commit();
	}
}
