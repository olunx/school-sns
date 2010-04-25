package cn.gdpu.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import cn.gdpu.util.Log;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AuthInterceptor extends AbstractInterceptor {

	private Map<String, Object> session;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
		session = actionContext.getSession();

		Log.init(getClass()).info("session isAccess: " + session.get("isAccess"));

		// Action action = (Action) invocation.getAction();
		// Log.init(getClass()).info("action: " + action);
		// if(action instanceof HomeAction) {
		// Log.init(getClass()).info("HomeAction: " + action);
		// HomeAction homeAction = (HomeAction)action;
		// }

		String access = (String) session.get("isAccess");
		if (access != null && (access == "ture" || access.equals("true"))) {
			Log.init(getClass()).info("验证成功。");
			return invocation.invoke();
		}

		// 检查cookies
		Cookie[] cookies = request.getCookies();
		String username = "";
		String password = "";
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("username"))
					username = c.getValue();
				if (c.getName().equals("password"))
					password = c.getValue();
			}
		}

		if (!username.equals("") && !password.equals("")) {
			Log.init(getClass()).info("Cookies存在，重新验证。username:" + username + " password:" + password);
			String url = "";
			String namespace = invocation.getProxy().getNamespace();
			String actionName = invocation.getProxy().getActionName();
			if (!namespace.equals("") && !namespace.equals("/")) {
				url = url + namespace;
			}
			if (!actionName.equals(""))
				url = url + "/" + actionName;
				Log.init(getClass()).info("url:"+url);
				session.put("referurl", url);
			// return invocation.invoke();//跳过验证
			return "cookieauth";// 重新登陆
		}

		Log.init(getClass()).info("验证失败。");
		return Action.LOGIN;

		// return invocation.invoke();
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
