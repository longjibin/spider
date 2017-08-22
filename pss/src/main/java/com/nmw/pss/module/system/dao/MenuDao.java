package com.nmw.pss.module.system.dao;

import java.util.List;

import com.nmw.pss.common.annotation.MyBatisDao;
import com.nmw.pss.common.base.BaseDao;
import com.nmw.pss.module.system.bean.Menu;

@MyBatisDao
public interface MenuDao extends BaseDao<Menu> {
	
	/**
	 * 查询当前节点的子节点
	 * @param menu 当前节点
	 * @return 子节点结合
	 */
	public List<Menu> selectChildrenByModel(Menu menu);
}
