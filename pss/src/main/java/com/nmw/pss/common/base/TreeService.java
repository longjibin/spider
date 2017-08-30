package com.nmw.pss.common.base;

import java.util.List;

public interface TreeService<T> {
	
	/**
	 * 按树表结构组织查询的数据(忽略登录用户的身份)
	 * @param t
	 * @return
	 */
	public List<T> findTreeTable();
	
}
