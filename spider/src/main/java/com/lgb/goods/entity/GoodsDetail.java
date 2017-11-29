package com.lgb.goods.entity;

import com.lgb.common.entity.BaseEntity;

/**
 * 商品详情
 * 
 * @author Administrator
 *
 */
public class GoodsDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Integer INLAND_SHOP = 1;

	public static final Integer GLOBAL_SHOP = 2;

	/**
	 * 分类id
	 */
	private String categoryId;

	/**
	 * 品牌id
	 */
	private String brandId;

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

//	/**
//	 * 评论数
//	 */
//	private String goodsCommint;

	/**
	 * 好评率
	 */
	private String goodCommint;

//	/**
//	 * 店铺名
//	 */
//	private String shopName;
//
//	/**
//	 * 店铺评分
//	 */
//	private Double shopScore;

	/**
	 * 其他信息
	 */
	private String other;

	/**
	 * 商品详情url
	 */
	private String goodsUrl;

	/**
	 * 商品来源，京东，淘宝等
	 */
	private String source;

//	/**
//	 * 商品类型 1表示国内 2表示全球购
//	 */
//	private Integer goodsType;

	public GoodsDetail() {
		super();
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
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

//	public String getGoodsCommint() {
//		return goodsCommint;
//	}
//
//	public void setGoodsCommint(String goodsCommint) {
//		this.goodsCommint = goodsCommint;
//	}

	public String getGoodCommint() {
		return goodCommint;
	}

	public void setGoodCommint(String goodCommint) {
		this.goodCommint = goodCommint;
	}

//	public String getShopName() {
//		return shopName;
//	}
//
//	public void setShopName(String shopName) {
//		this.shopName = shopName;
//	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getGoodsUrl() {
		return goodsUrl;
	}

	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

//	public Double getShopScore() {
//		return shopScore;
//	}
//
//	public void setShopScore(Double shopScore) {
//		this.shopScore = shopScore;
//	}
//
//	public Integer getGoodsType() {
//		return goodsType;
//	}
//
//	public void setGoodsType(Integer goodsType) {
//		this.goodsType = goodsType;
//	}

//	@Override
//	public String toString() {
//		return "GoodsDetail [shopName=" + shopName + "]";
//	}

}
