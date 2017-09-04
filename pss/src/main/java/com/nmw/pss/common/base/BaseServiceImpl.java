package com.nmw.pss.common.base;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.nmw.pss.common.utils.UserUtils;
import com.nmw.pss.module.login.bean.Employee;

public class BaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T>{
	
	@Autowired
	protected D dao;
	
	@Transactional
	@Override
	public void save(T t){
		Date date=new Date();
		Employee current=UserUtils.getCurrentUser();
		t.setUpdateTime(date);
		t.setUpdateUserId(current.getId());
//		t.setUpdateUserId("1");
		if(StringUtils.isBlank(t.getId())){
			t.setId(UUID.randomUUID().toString().replace("-", ""));
			t.setCreateTime(date);
			t.setCreateUserId(current.getId());
//			t.setCreateUserId("1");
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

	@Override
	public List<T> findByPage(Page<T> page) {
		//分页查询结果
		List<T> records=dao.selectByPage(page);
		//设置数据集合
		page.setRecords(records);
		//根据条件查询总记录数量
		page.setRecordCount(page.getRecordCount());
		//计算分页总数
		page.calculatePageCount();
		return records;
	}
}
