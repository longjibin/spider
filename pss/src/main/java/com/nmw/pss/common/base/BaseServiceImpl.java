package com.nmw.pss.common.base;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class BaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T>{
	
	@Autowired
	protected D dao;
	
	@Transactional
	@Override
	public void save(T t){
		Date date=new Date();
		t.setUpdateTime(date);
		t.setUpdateUserId("1");
		if(StringUtils.isBlank(t.getId())){
			t.setId(UUID.randomUUID().toString().replace("-", ""));
			t.setCreateTime(date);
			t.setCreateUserId("1");
			dao.insert(t);
		}else{
			dao.updateById(t);
		}
	}

	@Transactional
	@Override
	public void removeById(String id) {
		dao.deleteById(id);
	}

	@Override
	public T findById(String id) {
		return dao.selectById(id);
	}

	@Override
	public List<T> findAll() {
		return dao.selectAll();
	}
}
