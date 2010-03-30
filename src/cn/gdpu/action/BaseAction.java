package cn.gdpu.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport implements RequestAware, SessionAware {

	public final String INDEX = "index";
	public final String SUCCESS = "success";
	public final String ADD_PAGE = "addPage";
	public final String MODIFY_PAGE = "modifyPage";
	public final String VIEW_PAGE = "viewPage";
	public final String LIST = "list";
	private Map<String, Object> request;
	private Map<String, Object> session;

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
		return LIST;
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
		return LIST;
	}

	/**
	 * 删除一条数据，返回首页。
	 * 
	 * @return
	 */
	public String delete() {
		return LIST;
	}

	/**
	 * 删除多条数据，返回首页。
	 * 
	 * @return
	 */
	public String deleteMany() {
		return LIST;
	}

	/**
	 * 跳转到查看页面
	 * 
	 * @return
	 */
	public String view() {
		return VIEW_PAGE;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

}
