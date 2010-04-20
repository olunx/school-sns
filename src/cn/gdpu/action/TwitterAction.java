package cn.gdpu.action;

import com.opensymphony.xwork2.ActionContext;

import cn.gdpu.service.PeopleService;
import cn.gdpu.util.Log;
import cn.gdpu.vo.People;

@SuppressWarnings("serial")
public class TwitterAction extends BaseAction {

	private int id;
	private PeopleService<People, Integer> peopleService;

	@Override
	public String execute() throws Exception {
		
		String username = ActionContext.getContext().getName();
		
		Log.init(getClass()).info("TwitterAction username: " + username);
		
		People people = peopleService.getPeopleByUsername(username);
		
		this.id = people.getId();
		
		Log.init(getClass()).info("TwitterAction id: " + id);
		
		return super.SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PeopleService<People, Integer> getPeopleService() {
		return peopleService;
	}

	public void setPeopleService(PeopleService<People, Integer> peopleService) {
		this.peopleService = peopleService;
	}

}
