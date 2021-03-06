package com.lgb.webspider.ecp.jd.loadallbrand;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lgb.common.Constant;
import com.lgb.common.utils.ConfigUtil;
import com.lgb.common.utils.WebDriverPool;
import com.lgb.goods.entity.GoodsCb;
import com.lgb.goods.service.GoodsCbService;
import com.lgb.webspider.downloader.SeleniumDownloader;

import us.codecraft.webmagic.Spider;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration({ "/spring-context.xml" }) // 加载配置文件
public class LoadAllBrandSpiderTest {

	@Autowired
	private GoodsCbService goodsCbService;

	@Test
	public void test() {
		long start = System.currentTimeMillis();
		List<GoodsCb> goodsCbs = goodsCbService.findBySource(Constant.PLATFORM_JD);

		// 最多3个爬虫同时执行
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
