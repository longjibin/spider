package com.lgb.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgb.common.service.impl.BaseServiceImpl;
import com.lgb.goods.dao.GoodsSourceDAO;
import com.lgb.goods.entity.GoodsSource;
import com.lgb.goods.service.GoodsSourceService;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
@Service
public class GoodsSourceServiceImpl extends BaseServiceImpl<GoodsSource, GoodsSourceDAO> implements GoodsSourceService {

	@Autowired
	private GoodsSourceDAO goodsSourceDAO;

	@Override
	public List<String> findUrlsBySource(String source) {
		GoodsSource query = new GoodsSource();
		query.setSource(source);
		return goodsSourceDAO.selectUrlsByModel(query);
	}

	@Override
	public GoodsSource findBySkuAndSource(String sku, String source) {
		GoodsSource query = new GoodsSource();
		query.setSku(sku);
		query.setSource(source);
		List<GoodsSource> list = goodsSourceDAO.selectByModel(query);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void removeByUrl(String currentUrl) {
		GoodsSource query=new GoodsSource();
		query.setUrl(currentUrl);
		goodsSourceDAO.deleteByModel(query);
	}

}
