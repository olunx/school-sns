package cn.gdpu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.gdpu.service.GroupService;
import cn.gdpu.service.ImageService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.service.TopicService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.Group;
import cn.gdpu.vo.Image;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Topic;

@SuppressWarnings("serial")
public class GroupAction extends BaseAction {

	private int id;
	private Integer[] ids;
	private int topicId;
	private Group group;
	private GroupService<Group, Integer> groupService;
	private PeopleService<People, Integer> peopleService;
	private TopicService<Topic, Integer> topicService;
	private Topic topic;
	private PageBean pageBean;
	private int page;
	private Image image;
	private ImageService<Image, Integer> imageService;

	@Override
	public String add() {
		People author = (People) this.getSession().get("user");
		if (author != null) {
			group.setAdmin(author);
			imageService.addEntity(image);
			group.setPic(image);
			groupService.addEntity(group);
			FeedAction.init().add(group, author, FeedAction.ADD_GROUP);
		}
		return super.add();
	}

	@Override
	public String delete() {
		groupService.deleteEntity(Group.class, id);
		return super.delete();
	}

	@Override
	public String deleteMany() {
		groupService.deleteManyEntity(Group.class, ids);
		return super.deleteMany();
	}

	@Override
	public String goModify() {
		group = groupService.getEntity(Group.class, id);
		return super.goModify();
	}

	public String goAddTopic() {
		return "addTopicPage";
	}

	public String addTopic() {
		People author = (People) this.getSession().get("user");
		if (author != null) {
			topic.setAuthor(author);
			topic.setTime(new Date());
			topic.setIstopic(true);
			topic.setType("group");
			topicService.addEntity(topic);

			group = groupService.getEntity(Group.class, id);
			List<Topic> post = group.getPost();
			post.add(topic);
			group.setPost(post);
			groupService.updateEntity(group);
		}
		return "viewPage";
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

			Topic parent = topicService.getEntity(Topic.class, topicId);
			List<Topic> list = parent.getReply();
			if (list == null) {
				list = new ArrayList<Topic>();
			}
			list.add(topic);
			parent.setReply(list);
			parent.setHasreply(true);
			topicService.updateEntity(parent);

			FeedAction.init().add(topic, FeedAction.REPLY);

			group = groupService.getEntity(Group.class, id);
		}

		Log.init(getClass()).info("reply finish ");
		Log.init(getClass()).info("reply finish groupId" + id);

		return "viewPage";
	}

	@Override
	public String list() {
		People people = (People) this.getSession().get("user");
		if (people != null) {
			people = peopleService.getEntity(People.class, people.getId());
			this.getRequest().put("groups", people.getGroups());
		}
		this.pageBean = this.groupService.queryForPage(Group.class, 10, page);
		if (pageBean.getList().isEmpty())
			pageBean.setList(null);
		return super.list();
	}

	// 列出我创建的小组
	public String listMyCreate() {
		Log.init(getClass()).info("listMy");
		People people = (People) this.getSession().get("user");
		if (people != null) {
			this.pageBean = this.groupService.queryForPage("from Group g where g.admin = '" + people.getId() + "'", 10, page);
			if (pageBean.getList().isEmpty())
				pageBean.setList(null);
		}
		return super.list();
	}

	public String join() {
		Log.init(getClass()).info("join");

		People people = (People) this.getSession().get("user");
		Log.init(getClass()).info("people " + people);
		if (people != null) {
			group = groupService.getEntity(Group.class, id);
			people = peopleService.getEntity(People.class, people.getId());
			Set<People> members = group.getMembers();
			if (members.contains(people)) {
				members.remove(people);
				FeedAction.init().add(group, people, FeedAction.Quit_GROUP);
			} else {
				members.add(people);
				FeedAction.init().add(group, people, FeedAction.JOIN_GROUP);
			}
			group.setMembers(members);
			groupService.updateEntity(group);
		}

		Log.init(getClass()).info("join完成，跳转。");

		return super.LIST;
	}

	@Override
	public String modify() {
		Log.init(getClass()).info("ids: " + ids);
		if(ids != null) {
			Set<People> members = new HashSet<People>();
			for (int i = 0; i < ids.length; i++) {
				members.add(peopleService.getEntity(People.class, ids[i]));
			}
			Log.init(getClass()).info("members: " + members);
			group.setMembers(members);
		}
		
		imageService.addEntity(image);
		group.setPic(image);
		groupService.updateEntity(group);
		return super.modify();
	}

	@Override
	public String view() {
		group = groupService.getEntity(Group.class, id);
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public GroupService<Group, Integer> getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService<Group, Integer> groupService) {
		this.groupService = groupService;
	}

	public PeopleService<People, Integer> getPeopleService() {
		return peopleService;
	}

	public void setPeopleService(PeopleService<People, Integer> peopleService) {
		this.peopleService = peopleService;
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

	public TopicService<Topic, Integer> getTopicService() {
		return topicService;
	}

	public void setTopicService(TopicService<Topic, Integer> topicService) {
		this.topicService = topicService;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ImageService<Image, Integer> getImageService() {
		return imageService;
	}

	public void setImageService(ImageService<Image, Integer> imageService) {
		this.imageService = imageService;
	}
}
