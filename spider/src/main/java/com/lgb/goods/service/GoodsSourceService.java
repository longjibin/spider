package com.lgb.goods.service;

import java.util.List;

import com.lgb.common.service.BaseService;
import com.lgb.goods.entity.GoodsSource;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
public interface GoodsSourceService extends BaseService<GoodsSource> {

	/**
	 * 查询指定电商的商品链接
	 * @param platformJd
	 * @return
	 */
	List<String> findUrlsBySource(String platformJd);

	/**
	 * 根据sku和电商标记查询商品来源信息
	 * @param sku
	 * @param platformJd
	 * @return
	 */
	GoodsSource findBySkuAndSource(String sku, String platformJd);

	/**
	 * 根据商品链接移除商品
	 * @param currentUrl
	 */
	void removeByUrl(String currentUrl);

}
