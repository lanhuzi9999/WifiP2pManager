package com.tarena.dal;

import java.util.ArrayList;

import com.tarena.entity.Student;
import com.tarena.student.StuProviderInfo.StudentInfo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class StudentDao {
	private ContentResolver cr;

	public StudentDao(Context context) {
		cr = context.getContentResolver();
	}

	public ArrayList<Student> getStudents() {
		ArrayList<Student> students = null;
		// 通过cr 执行查询
		Cursor c = cr.query(StudentInfo.CONTENT_URI, null, null, null, null);
		// 解析cursor
		if (c != null) {
			// 初始化集合
			students = new ArrayList<Student>();
			while (c.moveToNext()) {
				Student stu = new Student();
				stu.setId(c.getInt(c.getColumnIndex("_id")));
				stu.setAge(c.getInt(c.getColumnIndex("age")));
				stu.setName(c.getString(c.getColumnIndex("name")));
				stu.setSex(c.getString(c.getColumnIndex("sex")));
				// 添加到集合
				students.add(stu);
			}
			c.close();
		}
		return students;
	}

	public long addStudent(Student stu) {
		long rowId = -1;
		// 通过 cr 执行添加操作
		ContentValues values = new ContentValues();
		values.put(StudentInfo.NAME, stu.getName());
		values.put(StudentInfo.SEX, stu.getSex());
		values.put(StudentInfo.AGE, stu.getAge());
		// 执行插入操作
		Uri uri = cr.insert(StudentInfo.CONTENT_URI, values);
		// 如果插入成功，获取新插入行的id
		if (uri != null) {
			rowId = ContentUris.parseId(uri);
		}
		return rowId;
	}

	public int updateStudent(Student stu) {
		int count = 0;
		// 通过 cr 执行操作
		ContentValues values = new ContentValues();
		values.put(StudentInfo.NAME, stu.getName());
		values.put(StudentInfo.SEX, stu.getSex());
		values.put(StudentInfo.AGE, stu.getAge());
		// 使用stu的id 构建一个单条数据访问的uri
		Uri uri = ContentUris.withAppendedId(StudentInfo.CONTENT_URI,
				stu.getId());
		// 执行修改
		count = cr.update(uri, values, null, null);

		return count;
	}

	public int deleteStudent(int id) {
		int count = 0;
		// 执行操作
		Uri uri = ContentUris.withAppendedId(StudentInfo.CONTENT_URI, id);

		count = cr.delete(uri, null, null);
		return count;
	}
}
