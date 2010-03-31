package cn.gdpu.service.impl;

import cn.gdpu.dao.impl.*;
import cn.gdpu.service.*;
import cn.gdpu.vo.*;

public class StudentServiceImpl extends BaseServiceImpl<Student, Integer, StudentDaoImpl> implements StudentService<Student, Integer> {

	@Override
	public Student getStudentByNo(String sno) {
		return getBaseDao().queryByNo(sno);
	}

	@Override
	public Student getStudentByUserName(String username) {
		return getBaseDao().queryByUsername(username);
	}

}
