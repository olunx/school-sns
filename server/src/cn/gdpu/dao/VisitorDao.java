package cn.gdpu.dao;

import java.io.Serializable;

public interface VisitorDao<T,ID extends Serializable> extends BaseDao<T, ID> {

}