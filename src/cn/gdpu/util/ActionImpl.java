package cn.gdpu.util;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;

public abstract class ActionImpl extends ActionSupport implements RequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String add() {

		return "index";
	}

	public String get() {
		return "updateLink";
	}

	public String update() {
		return "index";
	}

	public String delete() {
		return "index";
	}

	public String deleteMany() {
		return "index";
	}

	public String list() {
		return "input";
	}

	public String addLink() {
		return "addLink";
	}

}
