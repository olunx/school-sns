package cn.gdpu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.service.FeedService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.Feed;
import cn.gdpu.vo.Goods;
import cn.gdpu.vo.Group;
import cn.gdpu.vo.Issue;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Topic;
import cn.gdpu.vo.Twitter;
import cn.gdpu.vo.Vote;

@SuppressWarnings("serial")
public class FeedAction extends BaseAction {

	private static FeedService<Feed, Integer> service;
	private FeedService<Feed, Integer> feedService;
	private static FeedAction feedAction;
	private PeopleService<People, Integer> peopleService;
	private PageBean pageBean;
	private String moreAction;
	private int page;

	public final static String ADD_TWITTER = "add_twitter";
	public final static String ADD_LINK = "add_link";
	public final static String ADD_IMAGE = "add_image";
	public final static String REPLY_TWITTER = "reply_twitter";
	public final static String ADD_TOPIC = "add_topic";
	public final static String REPLY_TOPIC = "reply_topic";
	public final static String ADD_FRIEND = "add_friend";
	public final static String DEL_FRIEND = "del_friend";
	public final static String ADD_GROUP = "add_group";
	public final static String JOIN_GROUP = "join_group";
	public final static String Quit_GROUP = "quit_group";
	public final static String ADD_VOTE = "add_vote";
	public final static String JOIN_VOTE = "join_vote";
	public final static String ADD_GOODS = "add_goods";
	public final static String JOIN_GOODS = "join_goods";
	public final static String ADD_ISSUE = "add_issue";
	public final static String JOIN_ISSUE = "join_issue";

	@SuppressWarnings("unchecked")
	public static FeedAction init() {
		if (feedAction == null) {
			feedAction = new FeedAction();
		}
		if (service == null) {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			service = (FeedService<Feed, Integer>) ctx.getBean("feedService");
		}

		return feedAction;
	}

	// 微博
	public String add(Twitter twitter, String type) {

		Log.init(getClass()).info("FeedAction add");
		Log.init(getClass()).info("FeedService " + feedService);

		Feed feed = new Feed();
		feed.setAuthor(twitter.getAuthor());
		feed.setMessage(twitter.getContent());
		feed.setMsgId(twitter.getId());
		feed.setTime(twitter.getTime());

		if (twitter.getType() != null) {
			if (twitter.getType().equalsIgnoreCase("link")) {
				feed.setType(FeedAction.ADD_LINK);
				feed.setLink(twitter.getLink());
			} else if (twitter.getType().equalsIgnoreCase("image")) {
				feed.setType(FeedAction.ADD_IMAGE);
				feed.setLink(twitter.getImage().getMinFileUrl());
			}
		} else {
			feed.setType(type);
		}

		service.addEntity(feed);

		return super.add();
	}

	// 话题
	public String add(Topic topic, String type) {

		Log.init(getClass()).info("FeedAction add");
		Log.init(getClass()).info("FeedService " + feedService);

		Feed feed = new Feed();
		feed.setAuthor(topic.getAuthor());
		feed.setType(type);
		feed.setMessage(topic.getContent());
		feed.setMsgId(topic.getId());
		feed.setTime(topic.getTime());
		service.addEntity(feed);

		return super.add();
	}

	// 圈子
	public String add(Group group, People author, String type) {

		Feed feed = new Feed();
		feed.setAuthor(author);
		feed.setType(type);
		feed.setMessage(group.getName());
		feed.setMsgId(group.getId());
		feed.setTime(new Date());
		service.addEntity(feed);

		return super.add();
	}

	// 好友
	public String add(People people, People friend, String type) {

		Feed feed = new Feed();
		feed.setAuthor(people);
		feed.setType(type);
		feed.setMessage(friend.getName());
		feed.setMsgId(friend.getId());
		feed.setWhose(friend);
		feed.setTime(new Date());
		service.addEntity(feed);

		return super.add();
	}

	// 投票
	public String add(Vote vote, String type) {

		Feed feed = new Feed();
		feed.setAuthor(vote.getAuthor());
		feed.setType(type);
		feed.setMessage(vote.getTitle());
		feed.setMsgId(vote.getId());
		feed.setTime(new Date());
		service.addEntity(feed);

		return super.add();
	}

	// 群组
	public String add(Goods goods, String type) {

		Feed feed = new Feed();
		feed.setAuthor(goods.getOwner());
		feed.setType(type);
		feed.setMessage(goods.getName());
		feed.setMsgId(goods.getId());
		feed.setTime(new Date());
		service.addEntity(feed);

		return super.add();
	}

	// 问答
	public String add(Issue issue, String type) {
		Feed feed = new Feed();
		feed.setAuthor(issue.getOwner());
		feed.setType(type);
		feed.setMessage(issue.getName());
		feed.setMsgId(issue.getId());
		feed.setTime(new Date());
		service.addEntity(feed);

		return super.add();
	}

	@Override
	public String list() {
		People user = (People) this.getSession().get("user");
		user = peopleService.getEntity(People.class, user.getId());
		Set<People> friends = user.getFriends();
		friends.add(user);
		List<People> peoples = new ArrayList<People>();
		for (People p : friends) {
			peoples.add(p);
		}
		pageBean = this.getFeedList(peoples, null);
		moreAction = "listFeed";

		return super.list();
	}

	public String listSchool() {

		People user = (People) this.getSession().get("user");
		user = peopleService.getEntity(People.class, user.getId());
		String hql = "from People p where p.school = '" + user.getSchool().getId() + "' order by p.activity desc";

		pageBean = this.getFeedList(null, hql);
		moreAction = "listSchoolFeed";
		return super.list();
	}

	public String listClass() {
		People user = (People) this.getSession().get("user");
		user = peopleService.getEntity(People.class, user.getId());
		String hql ="from People p where p.classes = '" + user.getClasses().getId() + "' order by p.activity desc";
		pageBean = this.getFeedList(null, hql);
		moreAction = "listClassFeed";
		return super.list();
	}

	/**
	 * 在指定人内，按时间顺序返回Feed
	 * 可选参数 @peoples：指定人物，可为空 @hqlstr:Hql语句，可为空 
	 * 优先级：sqlstr > peoples
	 */
	public PageBean getFeedList(List<People> peoples, String hqlstr) {
		List<People> friendList = new ArrayList<People>();
		StringBuffer sql = new StringBuffer();
		if (peoples != null && peoples.size() > 0) {
			for (People f : peoples) {
				sql.append(f.getId() + ", ");
			}
			sql.delete(sql.lastIndexOf(","), sql.length());
		}

		if (hqlstr != null)
			sql = sql.delete(0, sql.length()).append(hqlstr);

		// 从feed中找到所有关注好友并按时间排序
		String hql = "from Feed f where f.author in (" + sql + ") group by f.author order by max(f.time) desc";
		PageBean friendPage = feedService.queryForPage(hql, 6, page);
		List<Feed> feedList = friendPage.getList();
		Log.init(getClass()).info(hql);
		for (Feed f : feedList) {
			friendList.add(f.getAuthor());
		}

		// 每个人中取出5条数据
		List<PageBean> result = new ArrayList<PageBean>();
		if (friendList != null && friendList.size() > 0) {
			for (People p : friendList) {
				result.add(feedService.queryForPage("from Feed f where f.author = " + p.getId() + " order by f.time desc", 5, 1));
			}
		}
		friendPage.setList(result);
		Log.init(getClass()).info(result.size());

		if (friendPage.getList().isEmpty()) {
			friendPage.setList(null);
		}
		return friendPage;
	}

	public FeedService<Feed, Integer> getFeedService() {
		return feedService;
	}

	public void setFeedService(FeedService<Feed, Integer> feedService) {
		this.feedService = feedService;
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

	public String getMoreAction() {
		return moreAction;
	}

	public void setMoreAction(String moreAction) {
		this.moreAction = moreAction;
	}

}
