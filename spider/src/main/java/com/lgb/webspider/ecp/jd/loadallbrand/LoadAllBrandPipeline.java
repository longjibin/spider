package com.lgb.webspider.ecp.jd.loadallbrand;

import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgb.goods.entity.GoodsBrand;
import com.lgb.goods.service.GoodsBrandService;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
@Component
public class LoadAllBrandPipeline implements Pipeline {
	
	private static final Logger LOGGER = Logger.getLogger(LoadAllBrandPipeline.class);
	
	@Autowired
	private GoodsBrandService goodsBrandService;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		Integer counter=0;
		GoodsBrand query=new GoodsBrand();
		for (Entry<String, Object> entry : resultItems.getAll().entrySet()) {
			query.setSbId(entry.getKey());
			goodsBrandService.save((GoodsBrand)entry.getValue(), goodsBrandService.count(query)==0?true:false);
			counter++;
		}
		LOGGER.info("LoadAllBrandPipeline-总共更新"+counter+"条数据");
	}

}
