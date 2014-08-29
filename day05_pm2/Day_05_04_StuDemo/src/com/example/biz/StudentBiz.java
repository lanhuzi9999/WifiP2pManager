package com.example.biz;

import java.util.ArrayList;

import android.content.Context;

import com.example.dal.StudentDao;
import com.example.entity.Student;

public class StudentBiz {
	private StudentDao dao;

	public StudentBiz(Context context) {
		dao = new StudentDao(context);
	}

	/**
	 * 查询所有学员信息
	 */
	public ArrayList<Student> getStudents() {
		return dao.getStudents();
	}

	/**
	 * 添加学员信息
	 * 
	 * @param stu
	 * @return
	 */
	public long addStudent(Student stu) {
		return dao.addStudent(stu);
	}

	/**
	 * 根据id 修改学员信息
	 * 
	 * @param stu
	 * @return
	 */
	public int update(Student stu) {
		return dao.updateStudent(stu);
	}

	/**
	 * 根据id 删除学员信息
	 * 
	 * @param id
	 * @return
	 */
	public int deleteStudent(int id) {
		return dao.deleteStudent(id);
	}
}
