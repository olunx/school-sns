package cn.gdpu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.gdpu.service.TopicService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Topic;

@SuppressWarnings("serial")
public class TopicAction extends BaseAction {

	private int id;
	private Integer[] ids;
	private int otherId;
	private Topic topic;
	private TopicService<Topic, Integer> topicService;
	private PageBean pageBean;
	private int page;

	@Override
	public String add() {
		People author = (People) this.getSession().get("user");
		if (author != null) {
			Log.init(getClass()).info("people name " + author.getName());
			topic.setAuthor(author);
			topic.setType("twitter");
			topic.setTime(new Date());
			topic.setIstopic(true);
			topicService.addEntity(topic);
		}

		FeedAction.init().add(topic, FeedAction.ADD_TWITTER);

		Log.init(getClass()).info("add finish ");

		return super.add();
	}

	public String addMy() {
		this.add();
		return "listMy";
	}

	public String goReply() {
		return "replyPage";
	}

	public String reply() {

		People author = (People) this.getSession().get("user");
		if (author != null) {
			Log.init(getClass()).info("people name " + author.getName());
			topic.setAuthor(author);
			topic.setTime(new Date());
			topic.setIstopic(false);
			topicService.addEntity(topic);

			Topic parent = topicService.getEntity(Topic.class, id);
			List<Topic> list = parent.getReply();
			if (list == null) {
				list = new ArrayList<Topic>();
			}
			list.add(topic);
			parent.setReply(list);
			parent.setHasreply(true);
			topicService.updateEntity(parent);

			FeedAction.init().add(topic, FeedAction.REPLY);
		}

		Log.init(getClass()).info("add finish ");

		return super.add();
	}

	@Override
	public String delete() {
		topicService.deleteEntity(Topic.class, id);
		return super.delete();
	}

	@Override
	public String deleteMany() {
		Log.init(getClass()).info("deleMamy " + ids);
		topicService.deleteManyEntity(Topic.class, ids);
		return super.deleteMany();
	}

	@Override
	public String list() {
		Log.init(getClass()).info("list ");
		this.pageBean = this.topicService.queryForPage("from Topic t where t.istopic = '1' and t.type = 'twitter' order by t.time DESC", 10, page);
		if (pageBean.getList().isEmpty()) {
			pageBean.setList(null);
		}
		Log.init(getClass()).info("list finish");
		return super.list();
	}

	public String listMy() {
		Log.init(getClass()).info("listMy");
		People author = (People) this.getSession().get("user");
		if (author != null) {
			this.pageBean = this.topicService.queryForPage("from Topic t where t.istopic = '1' and t.type = 'twitter' and t.author = '"
					+ author.getId() + "' order by t.time DESC", 10, page);
			if (pageBean.getList().isEmpty()) {
				pageBean.setList(null);
			}
		}

		Log.init(getClass()).info("listMy finish");

		return super.list();
	}

	public String listOther() {
		Log.init(getClass()).info("listOther");
		this.pageBean = this.topicService.queryForPage("from Topic t where t.istopic = '1' and t.type = 'twitter' and t.author = '"
				+ otherId + "' order by t.time DESC", 10, page);
		if (pageBean.getList().isEmpty()) {
			pageBean.setList(null);
		}

		Log.init(getClass()).info("listOther finish");
		return super.list();
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

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public TopicService<Topic, Integer> getTopicService() {
		return topicService;
	}

	public void setTopicService(TopicService<Topic, Integer> topicService) {
		this.topicService = topicService;
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

	public int getOtherId() {
		return otherId;
	}

	public void setOtherId(int otherId) {
		this.otherId = otherId;
	}
}
