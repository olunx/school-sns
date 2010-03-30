package cn.gdpu.action;

import java.util.Map;

@SuppressWarnings("serial")
public class LoginAction extends BaseAction {

	private String username;
	private String password;
	
	public String auth() {
		this.getSession().put("username", this.getUsername());
		this.getSession().put("password", this.getPassword());
		
		return super.SUCCESS;
	}

	public String go() {
		return "goLogin";
	}
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

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

}
