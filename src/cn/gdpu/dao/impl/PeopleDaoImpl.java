package cn.gdpu.dao.impl;

import java.util.List;

import cn.gdpu.dao.PeopleDao;
import cn.gdpu.util.Log;
import cn.gdpu.vo.People;

public class PeopleDaoImpl extends BaseDaoImpl<People, Integer> implements PeopleDao<People, Integer> {

	@SuppressWarnings("unchecked")
	@Override
	public People queryByUsername(String username) {
		People people = null;
		List<People> list = this.getHibernateTemplate().find("from People p where p.username = '" + username + "'");
//		Log.init(getClass()).info(list.get(0));
		if (list != null && list.size() > 0) {
			people =  list.get(0);
		}
		return people;
	}

	@SuppressWarnings("unchecked")
	@Override
	public People queryByUsernameAndPwd(String username, String password) {
		People people = null;
		List<People> list = this.getHibernateTemplate().find("from People p where p.username = '" + username + "' and p.password = '" + password + "'");
//		Log.init(getClass()).info(list.get(0));
		if (list != null && list.size() > 0) {
			Log.init(getClass()).info("进入循环");
			people =  list.get(0);
		}
		Log.init(getClass()).info("返回数据");
		Log.init(getClass()).info(people);
		Log.init(getClass()).info("返回完成");
		return people;
	}

}
