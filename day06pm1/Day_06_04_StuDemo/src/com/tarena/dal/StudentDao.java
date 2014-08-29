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
		// ͨ��cr ִ�в�ѯ
		Cursor c = cr.query(StudentInfo.CONTENT_URI, null, null, null, null);
		// ����cursor
		if (c != null) {
			// ��ʼ������
			students = new ArrayList<Student>();
			while (c.moveToNext()) {
				Student stu = new Student();
				stu.setId(c.getInt(c.getColumnIndex("_id")));
				stu.setAge(c.getInt(c.getColumnIndex("age")));
				stu.setName(c.getString(c.getColumnIndex("name")));
				stu.setSex(c.getString(c.getColumnIndex("sex")));
				// ��ӵ�����
				students.add(stu);
			}
			c.close();
		}
		return students;
	}

	public long addStudent(Student stu) {
		long rowId = -1;
		// ͨ�� cr ִ����Ӳ���
		ContentValues values = new ContentValues();
		values.put(StudentInfo.NAME, stu.getName());
		values.put(StudentInfo.SEX, stu.getSex());
		values.put(StudentInfo.AGE, stu.getAge());
		// ִ�в������
		Uri uri = cr.insert(StudentInfo.CONTENT_URI, values);
		// �������ɹ�����ȡ�²����е�id
		if (uri != null) {
			rowId = ContentUris.parseId(uri);
		}
		return rowId;
	}

	public int updateStudent(Student stu) {
		int count = 0;
		// ͨ�� cr ִ�в���
		ContentValues values = new ContentValues();
		values.put(StudentInfo.NAME, stu.getName());
		values.put(StudentInfo.SEX, stu.getSex());
		values.put(StudentInfo.AGE, stu.getAge());
		// ʹ��stu��id ����һ���������ݷ��ʵ�uri
		Uri uri = ContentUris.withAppendedId(StudentInfo.CONTENT_URI,
				stu.getId());
		// ִ���޸�
		count = cr.update(uri, values, null, null);

		return count;
	}

	public int deleteStudent(int id) {
		int count = 0;
		// ִ�в���
		Uri uri = ContentUris.withAppendedId(StudentInfo.CONTENT_URI, id);

		count = cr.delete(uri, null, null);
		return count;
	}
}
