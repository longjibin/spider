package com.lgb.goods.dao;

import java.util.List;

import com.lgb.common.annotation.MyBatisDao;
import com.lgb.common.dao.BaseDAO;
import com.lgb.goods.entity.GoodsSource;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
@MyBatisDao
public interface GoodsSourceDAO extends BaseDAO<GoodsSource> {
	/**
	 * 根据条件查询商品来源的url
	 * @param goodsSource
	 * @return
	 */
	List<String> selectUrlsByModel(GoodsSource goodsSource);
}
