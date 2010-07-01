package cn.gdpu.action;

import java.util.ArrayList;
import java.util.List;

import cn.gdpu.service.PeopleService;
import cn.gdpu.vo.People;

@SuppressWarnings("serial")
public class FriendAction extends BaseAction {
	private PeopleService<People, Integer> peopleService;
	private People people;
	@Override
	public String view() {
		People user = (People) this.getSession().get("user");
		people = peopleService.getEntity(People.class, user.getId());
		List<People> schoolmates = new ArrayList<People>();
		String hql;
		if (people.getSchool() != null) {
				// 推荐同一学校
				hql = "from People p where p.school.id ='" + people.getSchool().getId() + "' and p.id <> '" + people.getId()
				+ "' order by rand()";
				schoolmates = peopleService.queryForLimit(hql, 0, 10);
		}
		if(schoolmates.size() == 0){
			schoolmates = null;
		}
		getRequest().put("schoolmates", schoolmates);
		return VIEW_PAGE;
	}
	
	//getter and setter
	public PeopleService<People, Integer> getPeopleService() {
		return peopleService;
	}
	public void setPeopleService(PeopleService<People, Integer> peopleService) {
		this.peopleService = peopleService;
	}
	public People getPeople() {
		return people;
	}
	public void setPeople(People people) {
		this.people = people;
	}

}
