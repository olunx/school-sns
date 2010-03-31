package cn.gdpu.action;

import cn.gdpu.service.PeopleService;
import cn.gdpu.util.Log;
import cn.gdpu.vo.People;

@SuppressWarnings("serial")
public class LoginAction extends BaseAction {

	private String username;
	private String password;
	private PeopleService<People, Integer> peopleService;

	public String auth() {

		Log.init(getClass()).info("username: " + username);
		Log.init(getClass()).info("password: " + password);

		if (username != null && password != null) {
			if (peopleService != null) {
				People people = peopleService.getPeopleByUsernameAndPwd(username, password);
				Log.init(getClass()).info("登录用户对象: " + people);
				if (people != null) {
					this.getSession().put("username", this.getUsername());
					this.getSession().put("password", this.getPassword());
					Log.init(getClass()).info("登录成功");
					return super.SUCCESS;
				}
			}
		}

		return super.INDEX;
	}

	public String go() {
		return "goLogin";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PeopleService<People, Integer> getPeopleService() {
		return peopleService;
	}

	public void setPeopleService(PeopleService<People, Integer> peopleService) {
		this.peopleService = peopleService;
	}

}
