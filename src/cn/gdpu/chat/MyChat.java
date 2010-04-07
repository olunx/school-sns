package cn.gdpu.chat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

public class MyChat {
	Map<String, ScriptSession> sm = new HashMap<String, ScriptSession>();
	LinkedList<User> userList = UserList.getInstance().getUserList();

	@SuppressWarnings( { "deprecation" })
	public String updateUsersList(String username, HttpServletRequest request) {
		User user = null;
		if (!username.equals("")) {
			user = new User(username);
			request.getSession().setAttribute("user", user);
		}
		sm.put(request.getSession().getId(), WebContextFactory.get().getScriptSession());
		// System.out.println(sm);
		//System.out.println(user.getName());
		List<String> list = new ArrayList<String>();
		for (User u : userList) {
			list.add(u.getName());
		}
		for (Object o : sm.keySet()) {
			ScriptSession ss = sm.get(o);
			ss.addScript(new ScriptBuffer().appendCall("updateOnlineUser", userList));
			Util util = new Util(ss);
			util.removeAllOptions("sendto");
			util.addOptions("sendto", new String[] { "所有人" });
			util.addOptions("sendto", userList, "userid", "name");
		}
		return null;
	};

	public void sayHi() {
		Browser.withAllSessions(new Runnable() {
			public void run() {
				ScriptSessions.addFunctionCall("show", "Hi!!");
			}
		});
	}

	public void sayTo(String sendTo, String msg, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String username = user.getName();
		String userid = user.getUserid();
		String date = new SimpleDateFormat("hh:mm:ss").format(new Date());
		final String sendStr = username + "说:" + msg + " " + date;
		String sendToUsername = sendTo;
		for (User u : userList) {
			if (u.getUserid().equals(sendTo)) {
				sendToUsername = u.getName();
				break;
			}
		}
		for (String o : sm.keySet()) {
			ScriptSession ss = sm.get(o);
			if (sendTo.equals("所有人")) {
				ss.addScript(new ScriptBuffer().appendCall("show", sendStr));
			} else {
				if (sendTo.equals(o))
					ss.addScript(new ScriptBuffer().appendCall("show", username + "对你说:" + msg + " " + date));
				if (userid.equals(o))
					ss.addScript(new ScriptBuffer().appendCall("show", "你对" + sendToUsername + "说:" + msg + " " + date));
			}
		}
		// Browser.withAllSessions(new Runnable() {
		// public void run() {
		// ScriptSessions.addFunctionCall("show", sendStr);
		// }
		// });
	}

}
