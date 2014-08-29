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
	 * �ж�ָ�����û��Ƿ����
	 * 
	 * @param user
	 * @return
	 */
	public boolean exists(User user) {
		boolean isExists = false;
		//��ȡ���ݿ���ʶ���
		SQLiteDatabase db = helper.getReadableDatabase();
		//��ѯָ�����û�����������û���Ϣ
		Cursor c = db.rawQuery(
				"select * from usertbl where name=? and password=?",
				new String[] { user.getName(), user.getPassword() });
		//�����ѯ���Ľ������,֤���û�����
		if (c != null && c.moveToFirst()) {
			isExists = true;
		}
		//�ͷ���Դ
		db.close();
		//�����û��Ƿ���ڵ���Ϣ
		return isExists;
	}
}
