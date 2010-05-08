package cn.gdpu.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import cn.gdpu.dao.BaseDao;


@SuppressWarnings("unchecked")
public abstract  class BaseDaoImpl<T, ID extends Serializable> extends HibernateDaoSupport implements BaseDao<T, ID> {
	
	private Log logger = LogFactory.getLog(getClass());
	
	protected Class<T> entityClass;

	public BaseDaoImpl() {
	}

	protected Class getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			logger.debug("T class = " + entityClass.getName());
		}
		return entityClass;
	}
	
	@Override
	public void deleteById(Class<T> entityClass, ID id) {
		T t = (T) getHibernateTemplate().get(entityClass, id);
		if(t != null)
			this.getHibernateTemplate().delete(t);
	}

	@Override
	public void insert(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public List<T> queryAll(Class<T> entityClass) {
		return this.getHibernateTemplate().find("from " + entityClass.getName() + "");
	}

	@Override
	public T queryById(Class<T> entityClass, ID id) {
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}

	@SuppressWarnings("deprecation")
	public List<T> queryForPage(final String hql, final int offset, final int length) {
		
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(offset);
				query.setMaxResults(length);
				List items = query.list();
				//if (items.size() == 0) items = null;
				return items;
			}
		}, true);
	}
	
	 public int getAllRowCount(String hql){  
	        return getHibernateTemplate().find(hql).size();  
	    }


	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public List<T> queryByHql(Class<T> entityClass, String hql) {
		return this.getHibernateTemplate().find(hql);
	}  

	/*public void saveOrUpdate(T t) throws DataAccessException {
		this.getHibernateTemplate().saveOrUpdate(t);
	}

	public T load(ID id) throws DataAccessException {
		T load = (T) getHibernateTemplate().load(getEntityClass(), id);
		return load;
	}

	public T get(ID id) throws DataAccessException {
		T load = (T) getHibernateTemplate().get(getEntityClass(), id);
		return load;
	}

	public boolean contains(T t) throws DataAccessException {
		return getHibernateTemplate().contains(t);
	}

	public void delete(T t, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().delete(t, lockMode);
	}

	public void delete(T t) throws DataAccessException {
		getHibernateTemplate().delete(t);
	}

	public void deleteAll(Collection<T> entities) throws DataAccessException {
		getHibernateTemplate().deleteAll(entities);
	}

	public List<T> find(String queryString, Object value) throws DataAccessException {
		List<T> find = (List<T>) getHibernateTemplate().find(queryString, value);
		return find;
	}

	public List<T> find(String queryString, Object[] values) throws DataAccessException {
		List<T> finds = (List<T>) getHibernateTemplate().find(queryString, values);
		return finds;
	}

	public List<T> find(String queryString) throws DataAccessException {
		return (List<T>) getHibernateTemplate().find(queryString);
	}

	public void refresh(T t, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().refresh(t, lockMode);
	}

	public void refresh(T t) throws DataAccessException {
		getHibernateTemplate().refresh(t);
	}

	public Serializable save(T t) throws DataAccessException {
		return getHibernateTemplate().save(t);
	}

	public void saveOrUpdateAll(Collection<T> entities) throws DataAccessException {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	public void update(T t, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().update(t, lockMode);
	}

	public void update(T t) throws DataAccessException {
		getHibernateTemplate().update(t);
	}

	public List<T> list() throws DataAccessException {
		return getHibernateTemplate().loadAll(getEntityClass());
	}

	public List<T> findByNamedQuery(String queryName) throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName);
	}

	public List<T> findByNamedQuery(String queryName, Object value) throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName, value);
	}

	public List<T> findByNamedQuery(String queryName, Object[] values) throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName, values);
	}

	@SuppressWarnings("deprecation")
	public List<T> findPageByCriteria(final DetachedCriteria detachedCriteria, final int offset, final int length) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(org.hibernate.Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				criteria.setProjection(null);
				List items = criteria.setFirstResult(offset).setMaxResults(length).list();
				return items;
			}
		}, true);
	}*/
	
}