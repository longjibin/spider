package com.lgb.common.dao;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
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
	 * 查询指定id的数据
	 * @param id
	 * @return
	 */
	T select(String id);
	
	/**
	 * 查询满足条件的数据
	 * @param t
	 * @return
	 */
	List<T> selectByModel(T t);
	
	/**
	 * 查询记录数
	 * @param t
	 * @return
	 */
	Integer selectCount(T t);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<T> selectAll();
}
