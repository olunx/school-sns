package cn.gdpu.action;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.service.FeedService;
import cn.gdpu.util.Log;
import cn.gdpu.vo.Feed;
import cn.gdpu.vo.Topic;

@SuppressWarnings("serial")
public class FeedAction extends BaseAction {

	private static FeedService<Feed, Integer> feedService;

	private static FeedAction feedAction;

	@SuppressWarnings("unchecked")
	public static FeedAction init() {
		if (feedAction == null) {
			feedAction = new FeedAction();
		}
		if(feedService == null) {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			feedService = (FeedService<Feed, Integer>) ctx.getBean("feedService");
		}

		return feedAction;
	}

	public String add(Topic topic) {

		Log.init(getClass()).info("FeedAction add");
		Log.init(getClass()).info("FeedService " + feedService);

		Feed feed = new Feed();
		feed.setAuthor(topic.getAuthor());
		feed.setType("Topic");
		feed.setMessage(topic.getContent());
		feed.setTime(topic.getTime());
		feedService.addEntity(feed);

		return super.add();
	}
}
