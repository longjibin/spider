package com.lgb.webspider.ecp.jd.goodsdetail;

import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.lgb.common.utils.SpringContextHelper;
import com.lgb.goods.entity.GoodsDetail;
import com.lgb.goods.service.GoodsDetailService;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
public class GoodsDetailPipeline implements Pipeline {

	private static final Logger LOGGER = Logger.getLogger(GoodsDetailPipeline.class);

	@Override
	public void process(ResultItems resultItems, Task task) {
		Integer counter = 0;
		GoodsDetailService goodsDetailService = (GoodsDetailService) SpringContextHelper
				.getBean("goodsDetailServiceImpl");
		GoodsDetail query = new GoodsDetail();
		for (Entry<String, Object> entry : resultItems.getAll().entrySet()) {
			query.setSku(entry.getKey());
			goodsDetailService.save((GoodsDetail) entry.getValue(),
					goodsDetailService.count(query) == 0 ? true : false);
			counter++;
		}
		LOGGER.info("GoodsDetailPipeline-总共更新" + counter + "条数据");
	}

}
