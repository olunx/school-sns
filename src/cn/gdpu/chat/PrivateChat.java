package cn.gdpu.chat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;
import cn.gdpu.util.Log;
import cn.gdpu.vo.*;

public class PrivateChat {
	Map<String, User> userList = UserList.getInstance().getUserList(); // 用户表
	LinkedList<Message> messageList = new LinkedList<Message>();

	//登陆
	public String login(HttpServletRequest request){
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
		updateUsersList();//更新用户列表
		return request.getSession().getId();
	}
	
	//退出
	public void logout(HttpServletRequest request){
		request.getSession().removeAttribute("chatuser");
		userList.remove(request.getSession().getId());
		Log.init(this.getClass()).info("userList：" + userList);
		updateUsersList();//更新用户列表
	}

	//更新在线用户
	public void updateUsersList() {
		//循环更新所有用户的在线列表
		for (Object o : userList.keySet()) {
			User user = userList.get(o);
			ScriptSession ss = user.getSs();
			ss.addScript(new ScriptBuffer().appendCall("setUserid", user.getUserid()));
			ss.addScript(new ScriptBuffer().appendCall("receiveOnlineUser", userList));
//			Util util = new Util(ss);
//			util.removeAllOptions("sendto");
//			util.addOptions("sendto", new String[] { "所有人" });
//			util.addOptions("sendto", UserList.getInstance().getUserNameList(), "userid", "name");
		}
	};
		
	// 旧信息
	public LinkedList<Message> getMessageList() {
		return messageList;
	}

	//从userid取得user
	public User getUser(String userid) {
		return userList.get(userid);
	}

	// 说话
	public void sayTo(String toId, String msg, HttpServletRequest request) {
		Log.init(this.getClass()).info("msg：" + msg);
		User formUser = (User) request.getSession().getAttribute("chatuser");
		Log.init(this.getClass()).info("user：" + formUser);
		
		String fromUsername = formUser.getName();
		String fromId = formUser.getUserid();
		String date = new SimpleDateFormat("hh:mm:ss").format(new Date());
		String toUsername = toId;
		User toUser = getUser(toId);
		if (toUser != null)
			toUsername = toUser.getName();

		// 信息类
		final Message message = new Message();
		message.setText(msg);
		message.setFrom(fromUsername);
		message.setFromid(fromId);
		message.setTo(toUsername);
		message.setToid(toId);
		message.setTime(date);

		// 保存信息列表
		messageList.add(message);
		if (messageList.size() > 10)
			messageList.removeFirst();

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

		// Browser.withAllSessions(new Runnable() {
		// public void run() {
		// ScriptSessions.addFunctionCall("receiveMessage", message);
		// }
		// });
	}

}
