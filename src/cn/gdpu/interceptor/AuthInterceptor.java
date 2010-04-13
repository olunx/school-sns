package cn.gdpu.interceptor;

import java.util.Map;

import cn.gdpu.util.Log;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AuthInterceptor extends AbstractInterceptor {

	private Map<String, Object> session;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		session = invocation.getInvocationContext().getSession();

		Log.init(getClass()).info("session isAccess: " + session.get("isAccess"));

//		Action action = (Action) invocation.getAction();
//		Log.init(getClass()).info("action: " + action);
//		if(action instanceof HomeAction) {
//			Log.init(getClass()).info("HomeAction: " + action);
//			HomeAction homeAction = (HomeAction)action;
//		}
		
//		String access = (String) session.get("isAccess");
//		if (access != null && (access == "ture" || access.equals("true"))) {
//			Log.init(getClass()).info("验证成功。");
//			return invocation.invoke();
//		}
//
//		Log.init(getClass()).info("验证失败。");
//		return Action.LOGIN;
		
		return invocation.invoke();
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
