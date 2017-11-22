package com.lgb.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgb.common.service.impl.BaseServiceImpl;
import com.lgb.goods.dao.GoodsCbDAO;
import com.lgb.goods.entity.GoodsCb;
import com.lgb.goods.service.GoodsCbService;

@Service
public class GoodsCbServiceImpl extends BaseServiceImpl<GoodsCb, GoodsCbDAO> implements GoodsCbService {

	@Autowired
	private GoodsCbDAO goodsCbDAO;
	
	@Override
	public List<GoodsCb> findBySource(String source) {
		GoodsCb query=new GoodsCb();
		query.setSource(source);
		return goodsCbDAO.selectByModel(query);
	}

	@Override
	public GoodsCb findBySourceAndUrl(String source, String url) {
		GoodsCb query=new GoodsCb();
		query.setSource(source);
		query.setUrl(url);
		List<GoodsCb> goodsCbs=goodsCbDAO.selectByModel(query);
		return goodsCbs.isEmpty()?null:goodsCbs.get(0);
	}

}
