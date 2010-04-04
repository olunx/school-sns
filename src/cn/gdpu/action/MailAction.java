package cn.gdpu.action;

import java.util.Date;

import cn.gdpu.service.MailService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Mail;

@SuppressWarnings("serial")
public class MailAction extends BaseAction {

	private int id;
	private String ids;
	private Mail mail;
	private MailService<Mail, Integer> mailService;
	private int receiverId;
	private PeopleService<People, Integer> peopleService;
	private PageBean pageBean;
	private int page;

	@Override
	public String add() {
		People receiver = peopleService.getEntity(People.class, receiverId);
		if (receiver != null) {
			mail.setReceiver(receiver);
			Object author = this.getSession().get("student");
			if (author != null) {
				if (author instanceof People) {
					mail.setSender((People) author);
				}
				mail.setTime(new Date());
				mailService.addEntity(mail);
			}
		}

		return super.add();
	}

	@Override
	public String delete() {
		mailService.deleteEntity(Mail.class, id);
		return super.delete();
	}

	@Override
	public String deleteMany() {
		Log.init(getClass()).info("deleMamy " + ids);
		// TODO Auto-generated method stub
		return super.deleteMany();
	}

	@Override
	public String list() {
		Log.init(getClass()).info("list ");
		this.pageBean = this.mailService.queryForPage("from Mail t where t.istopic = '1'", 10, page);
		if (pageBean.getList().isEmpty()) {
			pageBean.setList(null);
		}
		Log.init(getClass()).info("list finish");
		return super.list();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public MailService<Mail, Integer> getMailService() {
		return mailService;
	}

	public void setMailService(MailService<Mail, Integer> mailService) {
		this.mailService = mailService;
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

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public PeopleService<People, Integer> getPeopleService() {
		return peopleService;
	}

	public void setPeopleService(PeopleService<People, Integer> peopleService) {
		this.peopleService = peopleService;
	}

}
