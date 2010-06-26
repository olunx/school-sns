package cn.gdpu.dao;

import java.io.Serializable;

import cn.gdpu.vo.Student;

public interface StudentDao<T,ID extends Serializable> extends BaseDao<T, ID> {
	Student queryBySno(String sno);
	Student queryByUsername(String username);
}
