package com.tarena.dal;

import com.tarena.entity.User;
import com.tarena.student.StuProviderInfo.UserInfo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
	private ContentResolver cr;

	public UserDao(Context context) {
		cr = context.getContentResolver();
	}

	public boolean exists(User user) {
		boolean isExists = false;
		// ʹ��ContentResolver����ִ�в���
		Cursor c = cr.query(UserInfo.CONTENT_URI, null, UserInfo.USERNAME
				+ "=? and " + UserInfo.USERPASS + "=?",
				new String[] { user.getName(), user.getPassword() }, null);
		// �����ѯ�����Ϊ��
		if (c != null && c.moveToFirst()) {
			isExists = true;// �û�����
			c.close();
		}
		return isExists;
	}
}
