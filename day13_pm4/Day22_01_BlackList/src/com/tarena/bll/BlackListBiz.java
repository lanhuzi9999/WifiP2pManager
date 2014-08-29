package com.tarena.bll;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.tarena.dal.BlackListDao;

public class BlackListBiz {
	private BlackListDao dao;
	private SharedPreferences pref;

	public BlackListBiz(Context context) {
		dao = new BlackListDao(context);
		pref = PreferenceManager.getDefaultSharedPreferences(context);
	}

	/**
	 * 获取黑名单的启用状态
	 * 
	 * @return
	 */
	public boolean isUsed() {
		return pref.getBoolean("isUsed", false);
	}

	/**
	 * 设置黑名单启用状态
	 * 
	 * @param used
	 * @return
	 */
	public boolean setBlackListState(boolean used) {
		return pref.edit().putBoolean("isUsed", used).commit();
	}

	/**
	 * 查询黑名单中的所有电话号码
	 * 
	 * @return
	 */
	public ArrayList<String> getBlackList() {
		return dao.getBlackList();
	}

	/**
	 * 向黑名单表中插入数据
	 * 
	 * @param numb
	 * @return
	 */
	public long addNumber(String numb) {
		return dao.addNumber(numb);
	}

	/**
	 * 将号码从黑名单中移除
	 * 
	 * @param numb
	 * @return
	 */
	public int removeNumber(String numb) {
		return dao.removeNumber(numb);
	}

	/**
	 * 查询指定号码是否存在于黑名单中
	 * 
	 * @param numb
	 * @return
	 */
	public boolean exists(String numb) {
		return dao.exists(numb);
	}
}
