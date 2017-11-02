package com.lgb.spider.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgb.spider.dao.SpiderDAO;
import com.lgb.spider.entity.Spider;
import com.lgb.spider.service.SpiderService;

@Service
public class SpiderServiceImpl implements SpiderService {
	
	@Autowired
	private SpiderDAO spiderDAO;
	
	@Override
	public void save(Spider spider) {
		if(StringUtils.isNotBlank(spider.getId())){
			spiderDAO.update(spider);
		}else{
			spider.setId(UUID.randomUUID().toString());
			spider.setCreateDateTime(new Date());
			spiderDAO.insert(spider);
		}
	}

	@Override
	public Spider findById(String id) {
		return spiderDAO.select(id);
	}

	@Override
	public List<Spider> findAll() {
		return spiderDAO.selectAll();
	}

}
