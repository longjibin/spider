package com.lgb.common.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgb.common.dao.BaseDAO;
import com.lgb.common.entity.BaseEntity;
import com.lgb.common.service.BaseService;
import com.lgb.common.utils.UUIDUtil;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
@Service
public class BaseServiceImpl<T extends BaseEntity, D extends BaseDAO<T>> implements BaseService<T> {

	@Autowired
	private D dao;

	@Override
	public void save(T t, Boolean operation) {
		Date date = new Date();
		if (operation) {
			/**
			 * 新增记录
			 */
			// 设置id
			t.setId(UUIDUtil.getUUID());
			// 设置创建时间
			t.setCreateDateTime(date);
			// 设置修改时间
			t.setUpdateDateTime(date);
			dao.insert(t);
		} else {
			/**
			 * 修改记录
			 */
			t.setUpdateDateTime(date);
			dao.update(t);
		}
	}

	@Override
	public Integer count(T t) {
		return dao.selectCount(t);
	}

}
