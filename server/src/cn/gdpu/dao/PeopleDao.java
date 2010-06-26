package cn.gdpu.dao;

import java.io.Serializable;

import cn.gdpu.vo.People;

public interface PeopleDao<T,ID extends Serializable> extends BaseDao<T, ID> {
	public abstract People queryByUsername(String username);
	public abstract People queryByUsernameAndPwd(String username,String password);
}
