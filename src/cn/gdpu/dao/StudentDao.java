package cn.gdpu.dao;

import java.io.Serializable;

import cn.gdpu.vo.Student;

public interface StudentDao<T,ID extends Serializable> extends BaseDao<T, ID> {
	public abstract Student queryByNo(String sno);
	public abstract Student queryByUsername(String username);
}
