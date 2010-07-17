package cn.gdpu.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.gdpu.service.PeopleService;
import cn.gdpu.util.MutualFriendComparator;
import cn.gdpu.vo.MutualFriend;
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
		List<People> institutemates = new ArrayList<People>();
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
		
		if (people.getInstitute() != null) {
			// 推荐同一学校并同一学院的学生
			hql = "from People p where p.institute.id ='" + people.getInstitute().getId() + "' and p.id <> '" + people.getId()
			+ "' order by rand()";
			institutemates = peopleService.queryForLimit(hql, 0, 10);
		}
		if(institutemates.size() == 0){
			institutemates = null;
		}
		getRequest().put("institutemates", institutemates);
		
		return VIEW_PAGE;
	}
	
	@SuppressWarnings("unchecked")
	public String mutual(){
		List<MutualFriend> mfs = (List<MutualFriend>) getSession().get("mutualfriend");
		MutualFriendComparator<MutualFriend> mfc = new MutualFriendComparator<MutualFriend>();
		if (mfs!=null)
		Collections.sort(mfs, mfc);
		getSession().put("mutualfriend", mfs);
		return "mutual";
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
