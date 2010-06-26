package cn.gdpu.chat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContextFactory;
import cn.gdpu.util.Log;
import cn.gdpu.vo.*;

public class PrivateChat {
	Map<String, PrivateUser> userList = PrivateUserList.getInstance().getUserList(); // 用户表
	Map<String, PrivateUser> chatingUserList = new HashMap<String, PrivateUser>(); // 正在聊天用户表
	LinkedList<Message> messageList = new LinkedList<Message>();

	// 登陆
	public String login(HttpServletRequest request) {
		// 取得学生
		People people = (People) request.getSession().getAttribute("user");
		Log.init(this.getClass()).info("登陆名：" + people.getName());
		HttpSession nowHs = request.getSession();
		ScriptSession nowSs = WebContextFactory.get().getScriptSession();
		if (people != null) {
			PrivateUser user = null;
			Log.init(this.getClass()).info("新增匿名聊天用户");
			user = new PrivateUser(nowHs.getId(), people.getName(), nowSs, nowHs);
			if (request.getSession().getAttribute("prichatuser") == null) {
				request.getSession().setAttribute("prichatuser", user);// 这里会自动加入userList
			}
			userList.put(user.getUserid(), user);
			// 配对
			randomUser(user);
		}

		Log.init(this.getClass()).info("userList：" + userList);

		return request.getSession().getId();
	}

	/**
	 * 用户随机配对
	 * @param user
	 */
	private synchronized void randomUser(PrivateUser user) {
		Log.init(getClass()).info("配对开始");
		if (user.getChatuser() == null) {
			Log.init(getClass()).info("还没有配对，寻找好友");
			List<PrivateUser> nowUserList = new ArrayList<PrivateUser>();
			nowUserList.addAll(userList.values());
			nowUserList.remove(user);
			if (nowUserList.size() > 0) {// 除了自己还有其他人
				Random rnd = new Random();
				int rndint = rnd.nextInt(nowUserList.size());
				PrivateUser otherUser = nowUserList.get(rndint);
				
				otherUser.setChatuser(user);
				user.setChatuser(otherUser);

				// 把用户从userList移到chatingUserList
				userList.remove(user.getUserid());
				userList.remove(otherUser.getUserid());
				chatingUserList.put(user.getUserid(), user);
				chatingUserList.put(otherUser.getUserid(), otherUser);
				Log.init(getClass()).info("配对成功:" + user + " 与 " + otherUser);

				sendTo(user,1, "配对成功！" + otherUser);
				sendTo(otherUser,1, "配对成功！" + user);

			}

		}
		Log.init(getClass()).info("chatingUserList:" + chatingUserList);
		Log.init(getClass()).info("userList:" + userList);
	}

	// 退出
	public void logout(HttpServletRequest request) {
		PrivateUser user = (PrivateUser) request.getSession().getAttribute("prichatuser");
		request.getSession().removeAttribute("prichatuser");
		userList.remove(user.getUserid());
		if (user!=null && user.getChatuser()!=null){
			PrivateUser otherUser = user.getChatuser();
			otherUser.getSs().addScript(new ScriptBuffer().appendCall("onelogout"));
			otherUser.getHs().removeAttribute("prichatuser");
			chatingUserList.remove(user.getUserid());
			chatingUserList.remove(otherUser.getUserid());
		}
		Log.init(this.getClass()).info("userList：" + userList);
		updateUsersList();// 更新用户列表
	}

	// 更新在线用户
	public void updateUsersList() {
		// 循环更新所有用户的在线列表
		Browser.withAllSessions(new Runnable() {
			@Override
			public void run() {
				int usernum = userList.size()+chatingUserList.size();
				ScriptSessions.addFunctionCall("receiveOnlineUser", usernum);
			}
		});
	};

	// 旧信息
	public LinkedList<Message> getMessageList() {
		return messageList;
	}

	// 从userid取得user
	public PrivateUser getUser(String userid) {
		return userList.get(userid);
	}

	/**
	 * 发送系统消息到用户
	 * @param toUser
	 * @param cmd
	 *            参考值 0:普通消息 1:配对成功 2:配对失败 
	 * @param msg
	 */
	private void sendTo(PrivateUser toUser, int cmd, String msg) {
		ScriptSession ss = toUser.getSs();
		ss.addScript(new ScriptBuffer().appendCall("receiveSystemMessage", cmd, msg));
	}
	
	//和配对的好友说话
	public void say(String msg, HttpServletRequest request) {
		PrivateUser formUser = (PrivateUser) request.getSession().getAttribute("prichatuser");
		PrivateUser toUser = formUser.getChatuser();
		ScriptSession ss = toUser.getSs();
		
		// 信息类
		final Message message = new Message();
		String date = new SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance(TimeZone.getDefault()).getTime());
		message.setText(msg);
		message.setFrom("陌生人");
		message.setFromid("");
		message.setTo("");
		message.setToid("");
		message.setTime(date);
		
		//发送到对方
		LinkedList<Message> sendMessageList = new LinkedList<Message>();
		sendMessageList.add(message);
		ss.addScript(new ScriptBuffer().appendCall("receiveMessage", sendMessageList));
	}

	// 说话
	public void sayTo(String toId, String msg, HttpServletRequest request) {
		Log.init(this.getClass()).info("msg：" + msg);
		PrivateUser formUser = (PrivateUser) request.getSession().getAttribute("prichatuser");
		Log.init(this.getClass()).info("user：" + formUser);

		String fromUsername = formUser.getName();
		String fromId = formUser.getUserid();
		String date = new SimpleDateFormat("hh:mm:ss").format(new Date());
		String toUsername = toId;
		PrivateUser toUser = getUser(toId);
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
