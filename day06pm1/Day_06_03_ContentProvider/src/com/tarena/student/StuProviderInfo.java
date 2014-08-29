package com.tarena.student;

import android.net.Uri;

public class StuProviderInfo {
	// 授权字符串
	public static final String AUTHORITY = "com.tarena.providers.student";
	// 对于用户表多条数据访问路径格式
	public static final String MULTI_USER_PATH = "user";
	// 对于学生表多条数据访问路径格式
	public static final String MULTI_STU_PATH = "student";
	// 对于用户表单条数据访问路径格式
	public static final String SINGLE_USER_PATH = "user/#";
	// 对于学生表单条数据访问路径格式
	public static final String SINGLE_STU_PATH = "student/#";

	public static class StudentInfo {
		// 用于访问学生表的多条数据的uri
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/" + MULTI_STU_PATH);
		//学生表中的各个列名
		public static final String ID = "_id";
		public static final String NAME = "name";
		public static final String SEX = "sex";
		public static final String AGE = "age";
	}

	public static class UserInfo {
		//用于访问用户表的多条数据的uri
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/" + MULTI_USER_PATH);
		//用户表中的各个列名
		public static final String ID = "_id";
		public static final String USERNAME = "name";
		public static final String USERPASS = "password";

	}
}
