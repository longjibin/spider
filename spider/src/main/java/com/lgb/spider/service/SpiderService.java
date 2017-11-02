package com.lgb.spider.service;

import java.util.List;

import com.lgb.spider.entity.Spider;

public interface SpiderService {
	/**
	 * 保存爬虫
	 * @param spider
	 */
	void save(Spider spider);
	
	/**
	 * 查询指定爬虫
	 * @param id
	 * @return
	 */
	Spider findById(String id);
	
	/**
	 * 查询所有的爬虫
	 */
	List<Spider> findAll();
}
