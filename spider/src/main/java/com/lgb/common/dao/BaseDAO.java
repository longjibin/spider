package com.lgb.common.dao;

import java.util.List;

public interface BaseDAO<T> {
	
	/**
	 * 新增一条数据
	 * @param t
	 */
	void insert(T t);
	
	/**
	 * 修改一条数据
	 * @param t
	 */
	void update(T t);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	T select(String id);
	
	/**
	 * 查询记录数
	 * @param t
	 * @return
	 */
	Integer selectCount(T t);
	
	/**
	 * 
	 * @return
	 */
	List<T> selectAll();
}
