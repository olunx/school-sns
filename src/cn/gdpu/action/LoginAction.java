package cn.gdpu.action;

import java.util.Map;

@SuppressWarnings("serial")
public class LoginAction extends BaseAction {

	public String auth() {
		return super.SUCCESS;
	}

	public String go() {
		return "goLogin";
	}
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

}
