package com.lgb.spider.entity;

import com.lgb.common.entity.Resource;

/**
 * 爬虫实体
 * @author Administrator
 *
 * @date 2017年10月26日
 */
public class Spider extends Resource{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Integer STATE_OFF=1;
	public static final Integer STATE_ON=2;
	
	/**
	 * 爬虫名称
	 */
	private String name;
	
	/**
	 * 爬取网址
	 */
	private String url;
	
	/**
	 * 并发数
	 */
	private Integer threadNum;
	
	/**
	 * 状态 1表示停止 2表示正在爬取
	 */
	private Integer state;

	public Spider() {
		super();
	}

	public Spider(String name, String url, Integer threadNum, Integer state) {
		super();
		this.name = name;
		this.url = url;
		this.threadNum = threadNum;
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(Integer threadNum) {
		this.threadNum = threadNum;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Spider [name=" + name + ", url=" + url + ", threadNum=" + threadNum + ", state=" + state + ", id=" + id
				+ ", createDateTime=" + createDateTime + "]";
	}

}
