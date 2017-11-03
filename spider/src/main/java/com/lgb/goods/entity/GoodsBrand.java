package com.lgb.goods.entity;

import com.lgb.common.entity.BaseEntity;

/**
 * 商品品牌实体
 * 
 * @author Administrator
 *
 * @date 2017年11月2日
 */
public class GoodsBrand extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 电商平台的品牌id
	 */
	private String sbId;

	/**
	 * 电商平台
	 */
	private String source;

	/**
	 * 品牌
	 */
	private String brand;

	/**
	 * 品牌对应的商品列表url
	 */
	private String goodsListUrl;

	/**
	 * 品牌所属分类
	 */
	private String categoryId;

	public GoodsBrand() {
		super();
	}

	public String getSbId() {
		return sbId;
	}

	public void setSbId(String sbId) {
		this.sbId = sbId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getGoodsListUrl() {
		return goodsListUrl;
	}

	public void setGoodsListUrl(String goodsListUrl) {
		this.goodsListUrl = goodsListUrl;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "GoodsBrand [sbId=" + sbId + ", source=" + source + ", brand=" + brand + ", goodsListUrl=" + goodsListUrl
				+ ", categoryId=" + categoryId + ", id=" + id + ", createDateTime=" + createDateTime
				+ ", updateDateTime=" + updateDateTime + "]";
	}

}
