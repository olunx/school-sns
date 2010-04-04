package cn.gdpu.dao;

import java.io.Serializable;
import java.util.List;

import cn.gdpu.vo.People;
import cn.gdpu.vo.Topic;

public interface TopicDao<T, ID extends Serializable> extends BaseDao<T, ID> {
	List<Topic> queryAllTopic();
	List<Topic> queryTopicByAuthor(People author);
}
