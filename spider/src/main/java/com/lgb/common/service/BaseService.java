package com.lgb.common.service;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
public interface BaseService<T> {
	/**
	 * 新增或者更新一条记录
	 * @param t
	 * @param operation true表示新增 false表示更新
	 */
	void save(T t, Boolean operation);
	
	/**
	 * 统计数据量
	 * @param t
	 * @return
	 */
	Integer count(T t);
	
	/**
	 * 查询指定id的数据
	 * @param id id
	 * @return
	 */
	T findById(String id);
}
