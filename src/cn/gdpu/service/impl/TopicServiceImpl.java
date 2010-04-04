package cn.gdpu.service.impl;

import java.util.List;

import cn.gdpu.dao.impl.TopicDaoImpl;
import cn.gdpu.service.TopicService;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Topic;

public class TopicServiceImpl extends BaseServiceImpl<Topic, Integer, TopicDaoImpl> implements TopicService<Topic, Integer> {

	@Override
	public List<Topic> getTopicByAuthor(People author) {
		return this.getBaseDao().queryTopicByAuthor(author);
	}

	@Override
	public List<Topic> getAllTopic() {
		return this.getBaseDao().queryAllTopic();
	}

}
