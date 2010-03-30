package cn.gdpu.interceptor;

import java.util.Map;

import cn.gdpu.util.Log;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AuthInterceptor extends AbstractInterceptor {

	Map<String, Object> session;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if(session == null) {
			session = invocation.getInvocationContext().getSession();
		}
		
		Log.init(getClass()).info("session: " + session.get("username"));
		
		if(session.get("username") != null) {
			if (session.get("username").equals("3c")) {
				String invoke = invocation.invoke();
				Log.init(getClass()).info("验证通过，跳转: " + invoke);
				return invoke;
			}
		}
		

		return Action.LOGIN;
	}

}
