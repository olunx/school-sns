package cn.gdpu.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

public interface IBaseDao<T, ID extends Serializable> {
	public abstract void saveOrUpdate(T t); 
	
	public abstract T load(ID id);

	public abstract T get(ID id);

	public abstract boolean contains(T t);

	public abstract void delete(T t, LockMode lockMode);

	public abstract void delete(T t);
	
	public abstract void deleteAll(Collection<T> entities);

	public abstract List<T> find(String queryString, Object value);

	public abstract List<T> find(String queryString, Object[] values);

	public abstract List<T> find(String queryString);

	public abstract void refresh(T t, LockMode lockMode);

	public abstract void refresh(T t);

	public abstract Serializable save(T t);

	public abstract void saveOrUpdateAll(Collection<T> entities);

	public abstract void update(T t, LockMode lockMode);

	public abstract void update(T t);

	public abstract List<T> list();

	public abstract List<T> findByNamedQuery(String queryName);
	
	public abstract List<T> findByNamedQuery(String queryName, Object value);

	public abstract List<T> findByNamedQuery(String queryName, Object[] values);

	public abstract List<T> findPageByCriteria(final DetachedCriteria detachedCriteria, final int offset,final int length);

	public abstract List<T> findPageByQuery(final String hql,final int offset,final int length);
	
	public abstract int getAllRowCount(String hql);  
}
