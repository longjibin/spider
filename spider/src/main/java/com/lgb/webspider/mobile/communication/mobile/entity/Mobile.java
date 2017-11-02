package com.lgb.webspider.mobile.communication.mobile.entity;

import com.lgb.common.entity.Resource;

public class Mobile extends Resource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品sku
	 */
	private String sku;

	/**
	 * 价格
	 */
	private Double price;

	/**
	 * 商品名
	 */
	private String goodsName;

	/**
	 * 评论数
	 */
	private String goodsCommit;

	/**
	 * 店铺名
	 */
	private String shopName;

	/**
	 * 其他信息
	 */
	private String other;

	/**
	 * 商品详情url
	 */
	private String goodsDetailUrl;

	/**
	 * 商品来源，京东，淘宝等
	 */
	private String source;

	public Mobile() {
		super();
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCommit() {
		return goodsCommit;
	}

	public void setGoodsCommit(String goodsCommit) {
		this.goodsCommit = goodsCommit;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getGoodsDetailUrl() {
		return goodsDetailUrl;
	}

	public void setGoodsDetailUrl(String goodsDetailUrl) {
		this.goodsDetailUrl = goodsDetailUrl;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "Phone [sku=" + sku + ", price=" + price + ", goodsName=" + goodsName + ", goodsCommit=" + goodsCommit
				+ ", shopName=" + shopName + ", other=" + other + ", goodsDetailUrl=" + goodsDetailUrl + ", source="
				+ source + ", id=" + id + ", createDateTime=" + createDateTime + ", updateDateTime=" + updateDateTime
				+ "]";
	}

}
