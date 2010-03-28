package cn.gdpu.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AuthInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		System.out.println("session: " + session.get("username"));
		
		if(session.get("username") != null) {
			if (session.get("username").equals("3c")) {
//				System.out.println("invoke: " + invocation.invoke());
				String invoke = invocation.invoke();
				System.out.println("验证通过，跳转: " + invoke);
				return invoke;
			}
		}
		

		return Action.LOGIN;
	}

}
