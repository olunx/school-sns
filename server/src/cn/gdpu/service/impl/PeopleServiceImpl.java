package cn.gdpu.service.impl;

import cn.gdpu.dao.impl.PeopleDaoImpl;
import cn.gdpu.service.PeopleService;
import cn.gdpu.vo.People;

public class PeopleServiceImpl extends BaseServiceImpl<People, Integer, PeopleDaoImpl> implements PeopleService<People, Integer> {

	@Override
	public People getPeopleByUsername(String username) {
		return this.getBaseDao().queryByUsername(username);
	}

	@Override
	public People getPeopleByUsernameAndPwd(String username, String password) {
		return this.getBaseDao().queryByUsernameAndPwd(username, password);
	}

}
