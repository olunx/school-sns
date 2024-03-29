package cn.gdpu.chat;

import java.io.Serializable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.directwebremoting.ScriptSession;


public class User implements HttpSessionBindingListener,Serializable {
	private static final long serialVersionUID = 1L;
	private String userid;
	private String name;
	private ScriptSession ss;
	private HttpSession hs;

	public User(String userid, String name, ScriptSession ss, HttpSession hs) {
		this.userid = userid;
		this.name = name;
		this.ss = ss;
		this.hs = hs;
	}

	private UserList userList = UserList.getInstance();

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
		User user = (User)obj;
		return user.userid.equals(userid);
	}

	@Override
	public int hashCode() {
		return userid.hashCode();
	}


}
