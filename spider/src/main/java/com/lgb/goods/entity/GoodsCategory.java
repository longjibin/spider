package com.lgb.goods.entity;

import com.lgb.common.entity.BaseEntity;

/**
 * 商品分类
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
public class GoodsCategory extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 分类名
	 */
	private String name;

	/**
	 * 父级id
	 */
	private String parents;

	public GoodsCategory() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParents() {
		return parents;
	}

	public void setParents(String parents) {
		this.parents = parents;
	}

}
