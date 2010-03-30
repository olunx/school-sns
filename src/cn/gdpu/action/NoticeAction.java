package cn.gdpu.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import cn.gdpu.service.*;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.*;

@SuppressWarnings("serial")
public class NoticeAction extends BaseAction {
	private Log logger = LogFactory.getLog(getClass());
	private NoticeService<Notice, Integer> noticeService;
	private String title;
	private String content;
	private PageBean pageBean;
	private int page;
	private int id;
	private int[] nids;
	private int cmd;
	
	@Override
	public String add() {
		Notice notice = new Notice();
		notice.setTitle(title.trim());
		notice.setContent(content);
		notice.setAuthor((Student) this.getSession().get("student"));
		notice.setTime(new Date());
		noticeService.addEntity(notice);
		return super.add();
	}

	@Override
	public String delete() {
		noticeService.deleteEntity(Notice.class, id);
		logger.info("-------page"+page);
		return super.delete();
	}

	@Override
	public String deleteMany() {
		if (nids != null && nids.length > 0) {
			switch (cmd) {
			case 1:
				for (int i = 0; i < nids.length; i++) {
					noticeService.deleteEntity(Notice.class, nids[i]);
				}
				break;
			}
		}
		return "list";
	}

	@Override
	public String goAdd() {
		return super.goAdd();
	}

	@Override
	public String goModify() {
		Notice notice = noticeService.getEntity(Notice.class, id);
		title = notice.getTitle();
		content = notice.getContent();
		logger.info("content222------"+notice.getContent());
		return super.goModify();
	}

	@Override
	public String list() {
		this.pageBean = noticeService.queryForPage(Notice.class, 5, page);  
        if(pageBean.getList().isEmpty())
    		pageBean.setList(null);
        
		return super.list();
	}

	@Override
	public String modify() {
		logger.info("id--"+id);
		Notice notice = new Notice();
		notice = noticeService.getEntity(Notice.class, id);
		notice.setTitle(title.trim());
		notice.setContent(content);
		notice.setAuthor((Student) this.getSession().get("student"));
		notice.setTime(new Date());
		noticeService.updateEntity(notice);
		return super.modify();
	}
	
	@Override
	public String view(){
		Notice notice = noticeService.getEntity(Notice.class, id);
		this.getRequest().put("notice", notice);
		return super.view();
	}

	public NoticeService<Notice, Integer> getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(NoticeService<Notice, Integer> noticeService) {
		this.noticeService = noticeService;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getNids() {
		return nids;
	}

	public void setNids(int[] nids) {
		this.nids = nids;
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

}
