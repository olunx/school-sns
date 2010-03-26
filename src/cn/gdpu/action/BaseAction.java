package cn.gdpu.action;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport implements RequestAware {

	public final String INDEX = "index";
	public final String SUCCESS = "success";
	public final String ADD_PAGE = "addPage";
	public final String MODIFY_PAGE = "modifyPage";

	/**
	 * 列出所有记录
	 * 
	 * @return
	 */
	public String list() {
		return INDEX;
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goAdd() {
		return ADD_PAGE;
	}

	/**
	 * 添加数据，返回首页。
	 * 
	 * @return
	 */
	public String add() {
		return INDEX;
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @return
	 */
	public String goModify() {
		return MODIFY_PAGE;
	}

	/**
	 * 提交修改，返回首页。
	 * 
	 * @return
	 */
	public String modify() {
		return INDEX;
	}

	/**
	 * 删除一条数据，返回首页。
	 * 
	 * @return
	 */
	public String delete() {
		return INDEX;
	}

	/**
	 * 删除多条数据，返回首页。
	 * 
	 * @return
	 */
	public String deleteMany() {
		return INDEX;
	}

}
