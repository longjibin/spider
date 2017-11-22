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

	List<String> findUrlsBySource(String platformJd);

}
