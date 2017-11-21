package com.lgb.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgb.common.service.impl.BaseServiceImpl;
import com.lgb.goods.dao.GoodsBrandDAO;
import com.lgb.goods.entity.GoodsBrand;
import com.lgb.goods.service.GoodsBrandService;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
@Service
public class GoodsBrandServiceImpl extends BaseServiceImpl<GoodsBrand, GoodsBrandDAO> implements GoodsBrandService {

	@Autowired
	private GoodsBrandDAO goodsBrandDAO;
	
	@Override
	public List<GoodsBrand> selectBySourceAndCategoryId(String source, String categoryId) {
		GoodsBrand query=new GoodsBrand();
		query.setSource(source);
		query.setCategoryId(categoryId);
		return goodsBrandDAO.selectByModel(query);
	}

	@Override
	public GoodsBrand findBySbId(String sbId) {
		GoodsBrand query=new GoodsBrand();
		query.setSbId(sbId);
		List<GoodsBrand> brands=goodsBrandDAO.selectByModel(query);
		return brands.isEmpty()==true?null:brands.get(0);
	}
	
}
