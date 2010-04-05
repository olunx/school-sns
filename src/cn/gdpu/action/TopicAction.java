package cn.gdpu.action;

import java.util.Date;

import cn.gdpu.service.TopicService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Topic;

@SuppressWarnings("serial")
public class TopicAction extends BaseAction {

	private int id;
	private String ids;
	private Topic topic;
	private TopicService<Topic, Integer> topicService;
	private PageBean pageBean;
	private int page;

	@Override
	public String add() {
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				topic.setAuthor((People) author);
			}
			topic.setTime(new Date());
			topicService.addEntity(topic);
		}

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
		// TODO Auto-generated method stub
		return super.deleteMany();
	}

	@Override
	public String list() {
		Log.init(getClass()).info("list ");
		this.pageBean = this.topicService.queryForPage("from Topic t where t.istopic = '1'", 10, page);
		if (pageBean.getList().isEmpty()) {
			pageBean.setList(null);
		}
		Log.init(getClass()).info("list finish");
		return super.list();
	}

	public String listMy() {
		Log.init(getClass()).info("listMy");
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				People people = (People) author;
				this.pageBean = this.topicService.queryForPage(
						"from Topic t where t.istopic = '1' and author = '" + people.getName() + "'", 10, page);
				if (pageBean.getList().isEmpty()) {
					pageBean.setList(null);
				}
			}
		}

		Log.init(getClass()).info("listMy finish");
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

}
