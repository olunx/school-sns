package cn.gdpu.chat;

import java.io.Serializable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.directwebremoting.ScriptSession;


public class PrivateUser implements HttpSessionBindingListener,Serializable {
	private static final long serialVersionUID = 1L;
	private String userid;
	private String name;
	private PrivateUser chatuser;
	private ScriptSession ss;
	private HttpSession hs;

	public PrivateUser(String userid, String name, ScriptSession ss, HttpSession hs) {
		this.userid = userid;
		this.name = name;
		this.ss = ss;
		this.hs = hs;
	}

	private PrivateUserList userList = PrivateUserList.getInstance();

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println(name+"登陆了");
		this.setUserid(event.getSession().getId());
		userList.addUser(this);
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
	    System.out.println(this.name + "退出。");
		userList.removeUser(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public ScriptSession getSs() {
		return ss;
	}

	public void setSs(ScriptSession ss) {
		this.ss = ss;
	}

	public HttpSession getHs() {
		return hs;
	}

	public void setHs(HttpSession hs) {
		this.hs = hs;
	}

	@Override
	public boolean equals(Object obj) {
		PrivateUser user = (PrivateUser)obj;
		return user.userid.equals(userid);
	}

	@Override
	public int hashCode() {
		return userid.hashCode();
	}

	public PrivateUser getChatuser() {
		return chatuser;
	}

	public void setChatuser(PrivateUser chatuser) {
		this.chatuser = chatuser;
	}


}
