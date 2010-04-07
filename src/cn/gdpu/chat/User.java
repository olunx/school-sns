package cn.gdpu.chat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;


public class User implements HttpSessionBindingListener,Serializable {
	private String userid;
	private String name;
	private ScriptSession ss;
	public User(String name) {
		this.name = name;
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


}
