package com.lgb.webspider.ecp.jd.goodssource;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgb.common.Constant;
import com.lgb.common.utils.ConfigUtil;
import com.lgb.goods.entity.GoodsBrand;
import com.lgb.goods.service.GoodsBrandService;
import com.lgb.webspider.SpiderTask;
import com.lgb.webspider.downloader.PageLoader;
import com.lgb.webspider.downloader.SeleniumDownloader;

import us.codecraft.webmagic.Spider;

@Component
public class GoodsSourceSpider implements SpiderTask {

	private static final Logger LOGGER = Logger.getLogger(GoodsSourceSpider.class);

	@Autowired
	private GoodsBrandService goodsBrandService;

	@Autowired
	private GoodsSourceProcessor goodsSourceProcessor;

	@Autowired
	private SeleniumDownloader seleniumDownloader;

	@Autowired
	private GoodsSourcePipeline goodsSourcePipeline;

	@Override
	public void execute() {
		long start = System.currentTimeMillis();
		/**
		 * 查询京东手机分类下的所有品牌
		 */
		List<GoodsBrand> goodsBrands = goodsBrandService.selectBySourceAndCategoryId(Constant.PLATFORM_JD, "3");
		List<String> urls = new ArrayList<String>();
		for (GoodsBrand goodsBrand : goodsBrands) {
			urls.add(goodsBrand.getGoodsListUrl());
		}

		seleniumDownloader.addConfig(new LoadMoreEvent(), PageLoader.DRIVER_CHROME);
		Spider.create(goodsSourceProcessor).addUrl(urls.toArray(new String[urls.size()]))
				.setDownloader(seleniumDownloader).addPipeline(goodsSourcePipeline)
				.thread(ConfigUtil.getInteger("thread.pool")).run();
		long end = System.currentTimeMillis();
		LOGGER.info("GoodsSourceSpider本次耗时:" + (end - start) + "ms");
	}

}
