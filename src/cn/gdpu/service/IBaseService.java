package cn.gdpu.service;

import java.io.Serializable;   
import java.util.List;   

import cn.gdpu.util.PageBean;
  
/**  
 *  Service 层基接口，处理业务逻辑.这里只提供基本的操作增、删、查、改的方法.  
 *    
 *    
 * @author Fucahl  
 * @version 1.0  
 * @since 1.0  
 */  
public interface IBaseService<T,ID extends Serializable> {   
       
        /**  
         * 添加实体类  
         *   
         * @param entity:需要添加的实体类  
         * @return 无返回值  
         * */  
	public abstract void addEntity(final T entity);   
       
        /**  
         * 修改实体类  
         *   
         * @param entity:修改持久化的对象  
         * @return 实现序列化的对象  
         * */  
	public abstract void updateEntity(final T entity);   
        
        /**  
         * 删除实体类  
         *   
         * @param entity:删除持久化的对象  
         * @return 无返回值 
         * */  
	public abstract void deleteEntity(final Class<T> entityClass,final ID id);   
        
        /**  
         * 查找实体类  
         *   
         * @param entity:修改持久化的对象  
         * @param clazz:
         * @param id:
         * @return 实现序列化的对象  
         * */  
	public abstract T getEntity(final Class<T> entityClass,final ID id);   
        
        /**  
         * 查找实体类  
         *   
         * @param conditionMap:Map<ID,T> 
         * @return List<T>  
         * */  
	public abstract List<T> getAllEntity(final Class<T> entityClass);   
	
		/**
		 * 分页
		 * @param pageSize
		 * @param currentPage
		 * @return
		 */
	public abstract PageBean queryForPage(final Class<T> entityClass, final int pageSize, final int currentPage);
}  
