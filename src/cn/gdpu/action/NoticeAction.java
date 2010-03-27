package cn.gdpu.action;

import java.util.Date;

import cn.gdpu.service.*;
import cn.gdpu.vo.*;

@SuppressWarnings("serial")
public class NoticeAction extends BaseAction {
	private NoticeService<Notice, Integer> noticeService;
	private String title;
	private String content;
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
		// TODO Auto-generated method stub
		return super.delete();
	}

	@Override
	public String deleteMany() {
		// TODO Auto-generated method stub
		return super.deleteMany();
	}

	@Override
	public String goAdd() {
		// TODO Auto-generated method stub
		return super.goAdd();
	}

	@Override
	public String goModify() {
		// TODO Auto-generated method stub
		return super.goModify();
	}

	@Override
	public String list() {
		// TODO Auto-generated method stub
		return super.list();
	}

	@Override
	public String modify() {
		// TODO Auto-generated method stub
		return super.modify();
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

}
