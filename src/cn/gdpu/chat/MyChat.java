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


import cn.gdpu.util.Log;
import cn.gdpu.vo.Student;

public class MyChat {
	Map<String, ScriptSession> sm = new HashMap<String, ScriptSession>(); //httpSession与ScriptSession对应表
	LinkedList<User> userList = UserList.getInstance().getUserList(); //用户表

	@SuppressWarnings( { "deprecation" })
	public String updateUsersList(HttpServletRequest request) {
		User user = null;

		//取得学生
		Student stu = (Student) request.getSession().getAttribute("student");
		Log.init(this.getClass()).info("登陆名："+stu.getUsername());
		if (stu != null) {
			if (request.getSession().getAttribute("chatuser") == null){
				Log.init(this.getClass()).info("新增用户");
				user = new User(stu.getUsername());
				request.getSession().setAttribute("chatuser", user);
				}
		}
		sm.put(request.getSession().getId(), WebContextFactory.get().getScriptSession());
		Log.init(this.getClass()).info("sm："+sm);
		
		List<String> list = new ArrayList<String>();
		for (User u : userList) {
			list.add(u.getName());
		}
		for (Object o : sm.keySet()) {
			ScriptSession ss = sm.get(o);
			ss.addScript(new ScriptBuffer().appendCall("receiveOnlineUser", userList));
			Util util = new Util(ss);
			util.removeAllOptions("sendto");
			util.addOptions("sendto", new String[] { "所有人" });
			util.addOptions("sendto", userList, "userid", "name");
		}
		return request.getSession().getId();
	};

	public void sayHi() {
		Browser.withAllSessions(new Runnable() {
			public void run() {
				ScriptSessions.addFunctionCall("show", "Hi!!");
			}
		});
	}

	public void sayTo(String toId, String msg, HttpServletRequest request) {
		Log.init(this.getClass()).info("msg："+msg);
		User user = (User) request.getSession().getAttribute("chatuser");
		Log.init(this.getClass()).info("user："+user);
		String fromUsername = user.getName();
		String fromId = user.getUserid();
		String date = new SimpleDateFormat("hh:mm:ss").format(new Date());
		final String sendStr = fromUsername + "说:" + msg + " " + date;
		String toUsername = toId;
		Log.init(this.getClass()).info("userList："+userList);
		for (User u : userList) {
			if (u.getUserid().equals(toId)) {
				toUsername = u.getName();
				break;
			}
		}
		
		//信息类
		final Message message = new Message();
		message.setText(msg);
		message.setFrom(fromUsername);
		message.setFromid(fromId);
		message.setTo(toUsername);
		message.setToid(toId);
		message.setTime(date);
		
//		Log.init(this.getClass()).info("遍历sm："+sm);
//		for (String o : sm.keySet()) {
//			ScriptSession ss = sm.get(o);
//			if (toId.equals("所有人")) {
//				ss.addScript(new ScriptBuffer().appendCall("show", sendStr));
//			} else {
//				if (toId.equals(o))
//					ss.addScript(new ScriptBuffer().appendCall("show", fromUsername + "对你说:" + msg + " " + date));
//				if (fromId.equals(o))
//					ss.addScript(new ScriptBuffer().appendCall("show", "你对" + toUsername + "说:" + msg + " " + date));
//			}
//		}
		
		 Browser.withAllSessions(new Runnable() {
		 public void run() {
		 ScriptSessions.addFunctionCall("receiveMessage", message);
		 }
		 });
	}

}
