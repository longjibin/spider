package com.lgb.webspider.ecp.jd.goodssource;

import java.util.LinkedHashMap;
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
	private PhantomJsDownloader phantomJsDownloader;

	@Autowired
	private GoodsBrandService goodsBrandService;

	@Override
	public void execute() {
		/**
		 * 查询京东手机分类下的所有品牌
		 */
		List<GoodsBrand> goodsBrands = goodsBrandService.selectBySourceAndCategoryId(Constant.PLATFORM_JD, "3");
		Map<String, Object> configMap = new LinkedHashMap<String, Object>();
		for (GoodsBrand goodsBrand : goodsBrands) {
			configMap.put(goodsBrand.getGoodsListUrl(), goodsBrand.getId());
		}

		// 测试数据
		String[] urls = new String[] {
				"http://list.jd.com/list.html?cat=9987,653,655&ev=exbrand%5F14026&sort=sort%5Frank%5Fasc&trans=1&JL=3_品牌_Apple" };

		phantomJsDownloader.setScript(new LoadMoreScript());
		// 获取爬虫配置对象
		Spider.create(new GoodsSourceProcessor(configMap)).addUrl(urls).setDownloader(phantomJsDownloader)
				.addPipeline(new GoodsSourcePipeline()).run();
	}

}
