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
	 * ��ȡ������������״̬
	 * 
	 * @return
	 */
	public boolean isUsed() {
		return pref.getBoolean("isUsed", false);
	}

	/**
	 * ���ú���������״̬
	 * 
	 * @param used
	 * @return
	 */
	public boolean setBlackListState(boolean used) {
		return pref.edit().putBoolean("isUsed", used).commit();
	}

	/**
	 * ��ѯ�������е����е绰����
	 * 
	 * @return
	 */
	public ArrayList<String> getBlackList() {
		return dao.getBlackList();
	}

	/**
	 * ����������в�������
	 * 
	 * @param numb
	 * @return
	 */
	public long addNumber(String numb) {
		return dao.addNumber(numb);
	}

	/**
	 * ������Ӻ��������Ƴ�
	 * 
	 * @param numb
	 * @return
	 */
	public int removeNumber(String numb) {
		return dao.removeNumber(numb);
	}

	/**
	 * ��ѯָ�������Ƿ�����ں�������
	 * 
	 * @param numb
	 * @return
	 */
	public boolean exists(String numb) {
		return dao.exists(numb);
	}
}
