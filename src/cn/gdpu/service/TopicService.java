package cn.gdpu.service;

import java.io.Serializable;
import java.util.List;

import cn.gdpu.vo.People;
import cn.gdpu.vo.Topic;

public interface TopicService<T, ID extends Serializable> extends BaseService<T, ID> {
	List<Topic> getAllTopic();
	List<Topic> getTopicByAuthor(People author);
}
