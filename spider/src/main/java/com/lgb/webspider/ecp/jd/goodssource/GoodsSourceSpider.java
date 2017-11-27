package com.lgb.webspider.ecp.jd.goodssource;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgb.common.Constant;
import com.lgb.common.utils.ConfigUtil;
import com.lgb.common.utils.WebDriverPool;
import com.lgb.goods.entity.GoodsBrand;
import com.lgb.goods.service.GoodsBrandService;
import com.lgb.webspider.SpiderTask;
import com.lgb.webspider.downloader.SeleniumDownloader;

import us.codecraft.webmagic.Spider;

@Component
public class GoodsSourceSpider implements SpiderTask {

	private static final Logger LOGGER = Logger.getLogger(GoodsSourceSpider.class);

	@Autowired
	private GoodsBrandService goodsBrandService;

	@Override
	public void execute() {
		long start = System.currentTimeMillis();
		/**
		 * 查询京东手机分类下的所有品牌
		 */
		List<GoodsBrand> goodsBrands = goodsBrandService.selectBySourceAndCategoryId(Constant.PLATFORM_JD, "3");

		// 最多3个爬虫同时执行
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		for (GoodsBrand goodsBrand : goodsBrands) {
			SeleniumDownloader seleniumDownloader2 = new SeleniumDownloader();
			seleniumDownloader2.setEvent(new LoadMoreEvent()).setDriver(WebDriverPool.DRIVER_CHROME);
			Spider spider = Spider.create(new GoodsSourceProcessor()).addUrl(goodsBrand.getGoodsListUrl())
					.setDownloader(seleniumDownloader2).addPipeline(new GoodsSourcePipeline())
					.thread(ConfigUtil.getInteger("thread.pool"));
			executorService.execute(spider);
		}
		executorService.shutdown();
		
		try {// 等待直到所有任务完成
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
			long end = System.currentTimeMillis();
			LOGGER.info("GoodsSourceSpider本次耗时:" + (end - start) + "ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
