package com.lgb.webspider.ecp.jd.loadallbrand;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgb.common.Constant;
import com.lgb.common.utils.ConfigUtil;
import com.lgb.common.utils.WebDriverPool;
import com.lgb.goods.entity.GoodsCb;
import com.lgb.goods.service.GoodsCbService;
import com.lgb.webspider.SpiderTask;
import com.lgb.webspider.downloader.SeleniumDownloader;

import us.codecraft.webmagic.Spider;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月15日
 */
@Component
public class LoadAllBrandSpider implements SpiderTask {

	@Autowired
	private GoodsCbService goodsCbService;

	@Override
	public void execute() {
		long start = System.currentTimeMillis();
		List<GoodsCb> goodsCbs = goodsCbService.findBySource(Constant.PLATFORM_JD);

		// 最多4个爬虫同时执行
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		for (GoodsCb goodsCb : goodsCbs) {
			SeleniumDownloader seleniumDownloader = new SeleniumDownloader();
			seleniumDownloader.setDriver(WebDriverPool.DRIVER_PHANTOMJS).setEvent(new LoadAllEvent());
			Spider spier = Spider.create(new LoadAllBrandProcessor()).addUrl(goodsCb.getUrl())
					.setDownloader(seleniumDownloader).addPipeline(new LoadAllBrandPipeline())
					.thread(ConfigUtil.getInteger("thread.pool"));
			executorService.execute(spier);
		}
		executorService.shutdown();

		try {// 等待直到所有任务完成
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
			long end = System.currentTimeMillis();
			System.out.println("LoadAllBrandSpider本次耗时:" + (end - start) + "ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
