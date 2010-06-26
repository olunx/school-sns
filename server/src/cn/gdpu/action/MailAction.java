package cn.gdpu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.gdpu.service.MailService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Mail;

@SuppressWarnings("serial")
public class MailAction extends BaseAction {

	private int id;
	private Integer[] ids;
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
			People author = (People) this.getSession().get("user");
			if (author != null) {
				mail.setSender(author);
				mail.setIsreaded(false);
				mail.setTime(new Date());
				mail.setIstopic(true);
				mailService.addEntity(mail);
			}
		}

		return super.add();
	}

	public String goReply() {
		return "replyPage";
	}

	public String box() {
		return "boxPage";
	}

	public String reply() {

		People author = (People) this.getSession().get("user");
		if (author != null) {
			Log.init(getClass()).info("people name " + author.getName());
			mail.setSender(author);
			mail.setIsreaded(false);
			mail.setTime(new Date());
			mail.setIstopic(false);
			mailService.addEntity(mail);

			Mail parent = mailService.getEntity(Mail.class, id);
			List<Mail> list = parent.getReply();
			if (list == null) {
				list = new ArrayList<Mail>();
			}
			list.add(mail);
			parent.setReply(list);
			parent.setHasreply(true);

			mailService.updateEntity(parent);
		}

		Log.init(getClass()).info("add finish ");

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
		mailService.deleteManyEntity(Mail.class, ids);
		return super.deleteMany();
	}

	@Override
	public String list() {
		Log.init(getClass()).info("list ");
		this.pageBean = this.mailService.queryForPage("from Mail m where m.istopic = '1'", 10, page);
		if (pageBean.getList().isEmpty()) {
			pageBean.setList(null);
		}
		Log.init(getClass()).info("list finish");
		return super.list();
	}

	public String listMySend() {
		Log.init(getClass()).info("listMy");
		People author = (People) this.getSession().get("user");
		if (author != null) {
			this.pageBean = this.mailService.queryForPage("from Mail m where m.istopic = '1' and m.sender = '" + author.getId() + "'", 10,
					page);
			if (pageBean.getList().isEmpty()) {
				pageBean.setList(null);
			}
		}

		Log.init(getClass()).info("listMy finish");
		return super.list();
	}

	public String listMyRece() {
		Log.init(getClass()).info("listMy");
		People author = (People) this.getSession().get("user");
		if (author != null) {
			this.pageBean = this.mailService.queryForPage("from Mail m where m.istopic = '1' and m.receiver = '" + author.getId() + "'",
					10, page);
			if (pageBean.getList().isEmpty()) {
				pageBean.setList(null);
			}
		}

		Log.init(getClass()).info("listMy finish");
		return super.list();
	}

	@Override
	public String view() {
		mail = mailService.getEntity(Mail.class, id);
		mail.setIsreaded(true);
		mail.setHasreply(false);
		mailService.updateEntity(mail);
		return super.view();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
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
