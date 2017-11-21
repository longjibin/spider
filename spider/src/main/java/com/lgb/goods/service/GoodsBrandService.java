package com.lgb.goods.service;

import java.util.List;

import com.lgb.common.service.BaseService;
import com.lgb.goods.entity.GoodsBrand;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
public interface GoodsBrandService extends BaseService<GoodsBrand> {
	
	/**
	 * 查询指定分类下指定电商的商品品牌集合
	 * @param source 电商标记
	 * @param categoryId 分类id
	 * @return
	 */
	List<GoodsBrand> selectBySourceAndCategoryId(String source, String categoryId);

	/**
	 * 查询指定电商平台品牌信息
	 * @param sbId
	 * @return
	 */
	GoodsBrand findBySbId(String sbId);
}
