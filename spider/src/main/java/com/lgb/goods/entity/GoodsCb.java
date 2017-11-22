package com.lgb.goods.entity;

import com.lgb.common.entity.BaseEntity;

/**
 * 电商分类url与本系统分类的关联类
 * 
 * @author Administrator
 *
 * @date 2017年11月22日
 */
public class GoodsCb extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 分类id
	 */
	private String categoryId;

	/**
	 * 分类url
	 */
	private String url;

	/**
	 * 电商标记
	 */
	private String source;

	public GoodsCb() {
		super();
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
