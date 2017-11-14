package com.lgb.goods.entity;

import com.lgb.common.entity.BaseEntity;

/**
 * 商品详情
 * @author Administrator
 *
 */
public class GoodsDetail extends BaseEntity {

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
	 * 店铺评分
	 */
	private Double shopScore;

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

	public Double getShopScore() {
		return shopScore;
	}

	public void setShopScore(Double shopScore) {
		this.shopScore = shopScore;
	}

	@Override
	public String toString() {
		return "GoodsDetail [sku=" + sku + ", price=" + price + ", goodsName=" + goodsName + ", goodsCommit="
				+ goodsCommit + ", shopName=" + shopName + ", shopScore=" + shopScore + ", other=" + other
				+ ", goodsDetailUrl=" + goodsDetailUrl + ", source=" + source + "]";
	}

}
