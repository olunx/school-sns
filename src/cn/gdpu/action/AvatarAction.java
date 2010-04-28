package cn.gdpu.action;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.gdpu.service.PeopleService;
import cn.gdpu.util.Log;
import cn.gdpu.vo.Image;
import cn.gdpu.vo.People;

public class AvatarAction extends BaseAction {
	private PeopleService<People, Integer> peopleService;
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		ServletContext context = ServletActionContext.getServletContext();
		int id = -1;
		try {
			String mname = ActionContext.getContext().getName();
			Log.init(getClass()).info("method Name" + mname);
			id = Integer.parseInt(mname);
		} catch (NumberFormatException e) {
			id = -1;
		}
		Log.init(getClass()).info(id);
		String filename = context.getRealPath("/content/images/avatar.jpg");
		if (id > 0) {
			People peo = peopleService.getEntity(People.class, id);
			if (peo != null) {
				Image avatar = peo.getAvatar();
				if (avatar != null && !avatar.getMinFileUrl().equals("")) {
					filename = context.getRealPath(avatar.getMinFileUrl());
				}
				Log.init(getClass()).info("avatar filename:" + filename);
			}
		}
		this.getRequest().put("filename", filename);
		return super.INDEX;
	}

	public void setPeopleService(PeopleService<People, Integer> peopleService) {
		this.peopleService = peopleService;
	}

	public PeopleService<People, Integer> getPeopleService() {
		return peopleService;
	}

}
