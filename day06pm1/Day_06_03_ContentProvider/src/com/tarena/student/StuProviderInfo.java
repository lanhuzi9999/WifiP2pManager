package com.tarena.student;

import android.net.Uri;

public class StuProviderInfo {
	// ��Ȩ�ַ���
	public static final String AUTHORITY = "com.tarena.providers.student";
	// �����û���������ݷ���·����ʽ
	public static final String MULTI_USER_PATH = "user";
	// ����ѧ����������ݷ���·����ʽ
	public static final String MULTI_STU_PATH = "student";
	// �����û��������ݷ���·����ʽ
	public static final String SINGLE_USER_PATH = "user/#";
	// ����ѧ���������ݷ���·����ʽ
	public static final String SINGLE_STU_PATH = "student/#";

	public static class StudentInfo {
		// ���ڷ���ѧ����Ķ������ݵ�uri
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/" + MULTI_STU_PATH);
		//ѧ�����еĸ�������
		public static final String ID = "_id";
		public static final String NAME = "name";
		public static final String SEX = "sex";
		public static final String AGE = "age";
	}

	public static class UserInfo {
		//���ڷ����û���Ķ������ݵ�uri
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/" + MULTI_USER_PATH);
		//�û����еĸ�������
		public static final String ID = "_id";
		public static final String USERNAME = "name";
		public static final String USERPASS = "password";

	}
}
