package com.nmw.pss.common.base;

import java.util.List;

public interface BaseDao<T> {
	/**
	 * 新增一条数据
	 * @param t
	 */
	public void insert(T t);
	
	/**
	 * 删除指定id的数据
	 * @param id
	 */
	public void deleteById(String id);
	
	/**
	 * 查询指定id的数据
	 * @param id
	 * @return
	 */
	public T selectById(String id);
	
	/**
	 * 修改指定id的数据
	 * @param t
	 */
	public void updateById(T t);
	
	/**
	 * 删除满足条件的数据
	 * @param t
	 */
	public void deleteByModel(T t);
	
	/**
	 * 查询满足条件的数据
	 * @param t
	 * @return
	 */
	public List<T> selectByModel(T t);
	
	/**
	 * 查询所有的记录
	 * @return
	 */
	public List<T> selectAll();
	
	/**
	 * 分页查询记录
	 * @return
	 */
	public List<T> selectByPage(Page<T> page);
}
