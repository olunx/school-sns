package cn.gdpu.service.impl;

import java.util.List;

import cn.gdpu.dao.impl.*;

import cn.gdpu.service.*;

import cn.gdpu.vo.*;

public class ClassFeeServiceImpl extends BaseServiceImpl<ClassFee, Integer, ClassFeeDaoImpl> implements ClassFeeService<ClassFee, Integer> {
	public double getTotalMoney() {
		List<ClassFee> list = getBaseDao().queryAll(ClassFee.class);
		double total = 0;
		for (int i = 0; i < list.size(); i++) {
			total += list.get(i).getFee();
		}
		return total;
	}
}