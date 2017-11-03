package com.lgb.goods.entity;

import com.lgb.common.entity.BaseEntity;

/**
 * 商品源对象
 * 
 * @author Administrator
 *
 * @date 2017年11月1日
 */
public class GoodsSource extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	private Integer brandId;

	/**
	 * 商品sku（不可拆分的商品标记）
	 */
	private String sku;

	/**
	 * 商品详情url
	 */
	private String url;

	public GoodsSource() {
		super();
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}
