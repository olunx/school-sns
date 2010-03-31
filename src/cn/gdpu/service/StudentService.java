package cn.gdpu.service;

import java.io.Serializable;

import cn.gdpu.vo.Student;

public interface StudentService<T, ID extends Serializable> extends BaseService<T, ID> {
	public abstract Student getStudentByNo(String sno);
	public abstract Student getStudentByUserName(String username);
}
