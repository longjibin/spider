package com.lgb.webspider.ecp.jd.goodssource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgb.common.Constant;
import com.lgb.goods.entity.GoodsBrand;
import com.lgb.goods.service.GoodsBrandService;
import com.lgb.webspider.SpiderTask;
import com.lgb.webspider.downloader.PhantomJsDownloader;

import us.codecraft.webmagic.Spider;

@Component
public class GoodsSourceSpider implements SpiderTask {

	@Autowired
	private GoodsBrandService goodsBrandService;

	@Override
	public void execute() {
		/**
		 * 查询京东手机分类下的所有品牌
		 */
		List<GoodsBrand> goodsBrands = goodsBrandService.selectBySourceAndCategoryId(Constant.PLATFORM_JD, "3");
		Map<String, GoodsBrand> configMap=new HashMap<String, GoodsBrand>();
		for (GoodsBrand goodsBrand : goodsBrands) {
			configMap.put(goodsBrand.getGoodsListUrl(), goodsBrand);
		}
		
		Spider.create(new GoodsSourceProcessor(configMap)).addUrl(configMap.keySet().toArray(new String[configMap.size()]))
				.setDownloader(new PhantomJsDownloader(new LoadMoreScript())).thread(2).run();

	}

}
