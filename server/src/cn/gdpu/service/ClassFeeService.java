package cn.gdpu.service;

import java.io.Serializable;

public interface ClassFeeService<T, ID extends Serializable> extends BaseService<T, ID> {
	public abstract double getTotalMoney();
}