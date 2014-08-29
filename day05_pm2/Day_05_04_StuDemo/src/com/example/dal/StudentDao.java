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
	 * 查询所有学员信息
	 */
	public ArrayList<Student> getStudents() {
		ArrayList<Student> stus = null;
		//获取数据库访问对象
		SQLiteDatabase db = helper.getReadableDatabase();
		//查询整个学员信息表
		Cursor c = db.rawQuery("select * from stutbl", null);
		if (c != null) {
			//初始化集合
			stus = new ArrayList<Student>();
			//遍历行，每行都是一条学员的信息
			while (c.moveToNext()) {
				//创建一个Student对象
				Student stu = new Student();
				//读取该行信息  设置Student对象的各个属性
				stu.setId(c.getInt(c.getColumnIndex("_id")));
				stu.setName(c.getString(c.getColumnIndex("name")));
				stu.setSex(c.getString(c.getColumnIndex("sex")));
				stu.setAge(c.getInt(c.getColumnIndex("age")));
				//添加到集合
				stus.add(stu);
			}
			c.close();//释放cursor
		}
		db.close();//释放db
		return stus;
	}

	/**
	 * 添加学员信息
	 * 
	 * @param stu
	 * @return
	 */
	public long addStudent(Student stu) {
		long rowId = -1;
		//获取数据库访问对象
		SQLiteDatabase db = helper.getWritableDatabase();
		//创建contentValues对象，并将要插入的数据添加到该集合
		ContentValues values = new ContentValues();
		values.put("name", stu.getName());
		values.put("sex", stu.getSex());
		values.put("age", stu.getAge());
		//执行插入操作
		rowId = db.insert("stutbl", null, values);
		//释放资源
		db.close();
		return rowId;
	}

	/**
	 * 根据id 修改学员信息
	 * 
	 * @param stu
	 * @return
	 */
	public int updateStudent(Student stu) {
		int count = 0;
		//获取数据库访问对象
		SQLiteDatabase db = helper.getWritableDatabase();
		//创建contentValues对象，并将要修改的数据添加到该集合
		ContentValues values = new ContentValues();
		values.put("name", stu.getName());
		values.put("sex", stu.getSex());
		values.put("age", stu.getAge());
		//执行修改操作
		count = db.update("stutbl", values, "_id=" + stu.getId(), null);
		//释放资源
		db.close();
		return count;
	}

	/**
	 * 根据id 删除学员信息
	 * 
	 * @param id
	 * @return
	 */
	public int deleteStudent(int id) {
		int count = 0;
		//获取数据访问对象
		SQLiteDatabase db = helper.getWritableDatabase();
		//执行删除操作
		count = db.delete("stutbl", "_id=" + id, null);
		//释放资源
		db.close();
		return count;
	}
}
