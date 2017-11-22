package com.lgb.goods.service;

import java.util.List;

import com.lgb.common.service.BaseService;
import com.lgb.goods.entity.GoodsCb;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月22日
 */
public interface GoodsCbService extends BaseService<GoodsCb> {

	/**
	 * 查询指定电商的品牌和分类的关联
	 * @param source
	 * @return
	 */
	List<GoodsCb> findBySource(String source);

	/**
	 * 查询指定电商的分类url的分类关联
	 * @param platformJd
	 * @param url
	 * @return
	 */
	GoodsCb findBySourceAndUrl(String platformJd, String url);
}
