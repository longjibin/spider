package com.nmw.pss.common.base;

import java.util.List;

public interface TreeService<T> {

	/**
	 * 按树结构返回满足条件的数据集合
	 * @param t
	 * @return 返回根节点
	 */
	public void findTree(T t, List<T> list);
	
	
	/**
	 * 查询子节点
	 * @param t
	 * @return
	 */
	public List<T> findChildren(T t);
	
	/**
	 * 
	 * @param t
	 * @return
	 */
	public List<T> findTreeTable();
	
}
