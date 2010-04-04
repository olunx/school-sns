package cn.gdpu.dao.impl;

import java.util.List;

import cn.gdpu.dao.TopicDao;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Topic;

public class TopicDaoImpl extends BaseDaoImpl<Topic, Integer> implements TopicDao<Topic, Integer> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> queryTopicByAuthor(People author) {
		List list = this.getHibernateTemplate().find("from Topic t where t.author = '" + author + "'");
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> queryAllTopic() {
		List list = this.getHibernateTemplate().find("from Topic t where t.istopic = '1'");
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
