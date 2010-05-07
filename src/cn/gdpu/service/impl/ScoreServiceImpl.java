package cn.gdpu.service.impl;

import java.math.BigDecimal;
import java.util.List;

import cn.gdpu.dao.impl.*;

import cn.gdpu.service.*;

import cn.gdpu.vo.*;

public class ScoreServiceImpl extends BaseServiceImpl<Score, Integer, ScoreDaoImpl> implements ScoreService<Score, Integer> {
	public double getAvgSubject(String subject) {
		String hql = "from Score s where s.subject ='" + subject + "'";
		List<Score> list = getBaseDao().queryByHql(Score.class, hql);
		double total = 0;
		for (int i = 0; i < list.size(); i++) {
			total += list.get(i).getMarks();
		}
		BigDecimal b = new BigDecimal(Double.toString(total));
		BigDecimal one = new BigDecimal(list.size());
		double result = b.divide(one, 1, BigDecimal.ROUND_HALF_UP).doubleValue() ;
		return result;
	}
}