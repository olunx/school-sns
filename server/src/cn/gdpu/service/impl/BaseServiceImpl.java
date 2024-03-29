package cn.gdpu.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.gdpu.dao.BaseDao;
import cn.gdpu.service.BaseService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;

public abstract class BaseServiceImpl<T, ID extends Serializable, GeneralDAO extends BaseDao<T, ID>> implements BaseService<T, ID> {

	private GeneralDAO baseDao;

	public GeneralDAO getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(GeneralDAO baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void addEntity(T entity) {
		baseDao.insert(entity);
	}

	@Override
	public void deleteEntity(Class<T> entityClass, ID id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public List<T> getAllEntity(Class<T> entityClass) {
		return baseDao.queryAll(entityClass);
	}

	@Override
	public T getEntity(Class<T> entityClass, ID id) {
		return baseDao.queryById(entityClass, id);
	}

	@Override
	public PageBean queryForPage(Class<T> entityClass, int pageSize, int currentPage) {
		final String hql = "from " + entityClass.getName() + ""; // 查询语句
		
		return this.queryForPage(hql, pageSize, currentPage);
	}

	@Override
	public PageBean queryForPage(String hql, int pageSize, int page) {
		Log.init(getClass()).info("queryForPagehql---" + hql);
		int allRow = baseDao.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		page = page > totalPage ? totalPage : page; // 防止删除最后一条记录，不能向上一页返回
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);
		List<T> list = baseDao.queryForPage(hql, offset, length); // "一页"的记录

		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		
		return pageBean;
	}
	
	@Override
	public List<T> queryForLimit(String hql, int offset, int length) {
		List<T> list = baseDao.queryForPage(hql, offset, length); // "一页"的记录
		return list;
	}
	
	@Override
	public void updateEntity(T entity) {
		baseDao.update(entity);
	}

	@Override
	public List<T> getEntity(Class<T> entityClass, String hql) {
		return baseDao.queryByHql(entityClass, hql);
	}

	@Override
	public void deleteManyEntity(Class<T> entityClass, ID[] ids) {
		if (ids != null && ids.length > 0) {
			for (ID id : ids) {
				baseDao.deleteById(entityClass, id);
			}
		}
	}

}
