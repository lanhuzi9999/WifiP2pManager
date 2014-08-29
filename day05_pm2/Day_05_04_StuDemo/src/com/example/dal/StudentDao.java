package com.example.dal;

import java.util.ArrayList;

import com.example.entity.Student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StudentDao {
	private DBOpenHelper helper;

	public StudentDao(Context context) {
		helper = new DBOpenHelper(context);
	}

	/**
	 * ��ѯ����ѧԱ��Ϣ
	 */
	public ArrayList<Student> getStudents() {
		ArrayList<Student> stus = null;
		//��ȡ���ݿ���ʶ���
		SQLiteDatabase db = helper.getReadableDatabase();
		//��ѯ����ѧԱ��Ϣ��
		Cursor c = db.rawQuery("select * from stutbl", null);
		if (c != null) {
			//��ʼ������
			stus = new ArrayList<Student>();
			//�����У�ÿ�ж���һ��ѧԱ����Ϣ
			while (c.moveToNext()) {
				//����һ��Student����
				Student stu = new Student();
				//��ȡ������Ϣ  ����Student����ĸ�������
				stu.setId(c.getInt(c.getColumnIndex("_id")));
				stu.setName(c.getString(c.getColumnIndex("name")));
				stu.setSex(c.getString(c.getColumnIndex("sex")));
				stu.setAge(c.getInt(c.getColumnIndex("age")));
				//��ӵ�����
				stus.add(stu);
			}
			c.close();//�ͷ�cursor
		}
		db.close();//�ͷ�db
		return stus;
	}

	/**
	 * ���ѧԱ��Ϣ
	 * 
	 * @param stu
	 * @return
	 */
	public long addStudent(Student stu) {
		long rowId = -1;
		//��ȡ���ݿ���ʶ���
		SQLiteDatabase db = helper.getWritableDatabase();
		//����contentValues���󣬲���Ҫ�����������ӵ��ü���
		ContentValues values = new ContentValues();
		values.put("name", stu.getName());
		values.put("sex", stu.getSex());
		values.put("age", stu.getAge());
		//ִ�в������
		rowId = db.insert("stutbl", null, values);
		//�ͷ���Դ
		db.close();
		return rowId;
	}

	/**
	 * ����id �޸�ѧԱ��Ϣ
	 * 
	 * @param stu
	 * @return
	 */
	public int updateStudent(Student stu) {
		int count = 0;
		//��ȡ���ݿ���ʶ���
		SQLiteDatabase db = helper.getWritableDatabase();
		//����contentValues���󣬲���Ҫ�޸ĵ�������ӵ��ü���
		ContentValues values = new ContentValues();
		values.put("name", stu.getName());
		values.put("sex", stu.getSex());
		values.put("age", stu.getAge());
		//ִ���޸Ĳ���
		count = db.update("stutbl", values, "_id=" + stu.getId(), null);
		//�ͷ���Դ
		db.close();
		return count;
	}

	/**
	 * ����id ɾ��ѧԱ��Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public int deleteStudent(int id) {
		int count = 0;
		//��ȡ���ݷ��ʶ���
		SQLiteDatabase db = helper.getWritableDatabase();
		//ִ��ɾ������
		count = db.delete("stutbl", "_id=" + id, null);
		//�ͷ���Դ
		db.close();
		return count;
	}
}
