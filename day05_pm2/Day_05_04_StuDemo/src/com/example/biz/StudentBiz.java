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
	 * ��ѯ����ѧԱ��Ϣ
	 */
	public ArrayList<Student> getStudents() {
		return dao.getStudents();
	}

	/**
	 * ���ѧԱ��Ϣ
	 * 
	 * @param stu
	 * @return
	 */
	public long addStudent(Student stu) {
		return dao.addStudent(stu);
	}

	/**
	 * ����id �޸�ѧԱ��Ϣ
	 * 
	 * @param stu
	 * @return
	 */
	public int update(Student stu) {
		return dao.updateStudent(stu);
	}

	/**
	 * ����id ɾ��ѧԱ��Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public int deleteStudent(int id) {
		return dao.deleteStudent(id);
	}
}
