package cn.gdpu.chat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

import cn.gdpu.util.Log;
import cn.gdpu.vo.*;

public class MyChat {
	Map<String, User> userList = UserList.getInstance().getUserList(); // 用户表
	LinkedList<Message> messageList = new LinkedList<Message>();

	@SuppressWarnings( { "deprecation" })
	public String updateUsersList(HttpServletRequest request) {
		// 取得学生
		People people = (People) request.getSession().getAttribute("user");
		Log.init(this.getClass()).info("登陆名：" + people.getName());
		HttpSession nowHs = request.getSession();
		ScriptSession nowSs = WebContextFactory.get().getScriptSession();
		if (people != null) {
			User user = null;
			Log.init(this.getClass()).info("新增用户");
			user = new User(nowHs.getId(), people.getName(), nowSs, nowHs);
			if (request.getSession().getAttribute("chatuser") == null) {
				request.getSession().setAttribute("chatuser", user);// 这里会自动加入userList
			} else {
				// 更新列表
				userList.put(user.getUserid(), user);
			}
		}
		Log.init(this.getClass()).info("userList：" + userList);

		for (Object o : userList.keySet()) {
			User user = userList.get(o);
			ScriptSession ss = user.getSs();
			ss.addScript(new ScriptBuffer().appendCall("receiveOnlineUser", userList));
			Util util = new Util(ss);
			util.removeAllOptions("sendto");
			util.addOptions("sendto", new String[] { "所有人" });
			util.addOptions("sendto", UserList.getInstance().getUserNameList(), "userid", "name");
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
	
	public LinkedList<Message> getMessageList(){
		return messageList;
	}
	
	//说话
	public void sayTo(String toId, String msg, HttpServletRequest request) {
		Log.init(this.getClass()).info("msg：" + msg);
		User user = (User) request.getSession().getAttribute("chatuser");
		Log.init(this.getClass()).info("user：" + user);
		String fromUsername = user.getName();
		String fromId = user.getUserid();
		String date = new SimpleDateFormat("hh:mm:ss").format(new Date());
		String toUsername = toId;
		Log.init(this.getClass()).info("userList：" + userList);
		for (Object o : userList.keySet()) {
			User u = userList.get(o);
			if (u.getUserid().equals(toId)) {
				toUsername = u.getName();
				break;
			}
		}
		
		// 信息类
		final Message message = new Message();
		message.setText(msg);
		message.setFrom(fromUsername);
		message.setFromid(fromId);
		message.setTo(toUsername);
		message.setToid(toId);
		message.setTime(date);
		
		//保存信息列表
		messageList.add(message);
		if (messageList.size()>10) messageList.removeFirst();
		
		LinkedList<Message> sendMessageList = new LinkedList<Message>();
		sendMessageList.add(message);
		
		for (Object o : userList.keySet()) {
			ScriptSession ss = userList.get(o).getSs();
			ss.addScript(new ScriptBuffer().appendCall("receiveMessage", sendMessageList));
		}
		
		// Log.init(this.getClass()).info("遍历sm："+sm);
		// for (String o : sm.keySet()) {
		// ScriptSession ss = sm.get(o);
		// if (toId.equals("所有人")) {
		// ss.addScript(new ScriptBuffer().appendCall("show", sendStr));
		// } else {
		// if (toId.equals(o))
		// ss.addScript(new ScriptBuffer().appendCall("show", fromUsername +
		// "对你说:" + msg + " " + date));
		// if (fromId.equals(o))
		// ss.addScript(new ScriptBuffer().appendCall("show", "你对" + toUsername
		// + "说:" + msg + " " + date));
		// }
		// }

//		Browser.withAllSessions(new Runnable() {
//			public void run() {
//				ScriptSessions.addFunctionCall("receiveMessage", message);
//			}
//		});
	}

}
