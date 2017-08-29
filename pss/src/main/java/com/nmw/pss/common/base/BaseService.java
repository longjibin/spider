package com.nmw.pss.common.base;

import java.util.List;

public interface BaseService<T> {
	/**
	 * 保存一条记录
	 * @param t
	 */
	public void save(T t);
	
	/**
	 * 删除指定id的记录
	 * @param id
	 */
	public void removeById(String id);
	
	/**
	 * 查询一条记录
	 * @param id
	 */
	public T findById(String id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<T> findByPage(Page<T> page);
}
