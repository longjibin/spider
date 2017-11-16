package com.lgb.webspider.ecp.jd.goodssource;

import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.lgb.common.utils.SpringContextHelper;
import com.lgb.goods.entity.GoodsBrand;
import com.lgb.goods.entity.GoodsSource;
import com.lgb.goods.service.GoodsSourceService;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
public class GoodsSourcePipeline implements Pipeline {

	private static final Logger LOGGER = Logger.getLogger(GoodsSourcePipeline.class);
	
	private GoodsBrand goodsBrand;
	
	public GoodsSourcePipeline(GoodsBrand goodsBrand) {
		this.goodsBrand=goodsBrand;
	}

	@Override
	public void process(ResultItems resultItems, Task task) {
		Integer counter=0;
		GoodsSourceService goodsSourceService=(GoodsSourceService) SpringContextHelper.getBean("goodsSourceServiceImpl");
		GoodsSource query = new GoodsSource();
		for (Entry<String, Object> entry : resultItems.getAll().entrySet()) {
			query.setSku(entry.getKey());
			goodsSourceService.save((GoodsSource) entry.getValue(),
					goodsSourceService.count(query) == 0 ? true : false);
			counter++;
		}
		LOGGER.info(goodsBrand.getSource()+"-"+"GoodsSource-"+goodsBrand.getBrand()+":更新"+counter+"条数据");
	}

}
