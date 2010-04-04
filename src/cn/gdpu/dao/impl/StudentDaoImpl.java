package cn.gdpu.dao.impl;

import java.util.List;

import cn.gdpu.vo.*;


public class StudentDaoImpl extends BaseDaoImpl<Student, Integer> implements cn.gdpu.dao.StudentDao<Student, Integer> {

	public Student queryByNo(String sno) {
		Student stu = null;
		List list = this.getHibernateTemplate().find("from Student s where s.sno = '" + sno + "'");
		System.out.println(list.get(0));
		if (list != null && list.size() > 0) {
			stu = (Student) list.get(0);
		}
		return stu;
	}

	@Override
	public Student queryByUsername(String username) {
		Student stu = null;
		List list = this.getHibernateTemplate().find("from Student s where s.username = '" + username + "'");
		System.out.println(list.get(0));
		if (list != null && list.size() > 0) {
			stu = (Student) list.get(0);
		}
		return stu;
	}

}
