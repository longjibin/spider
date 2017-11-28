package com.lgb.webspider.ecp.jd.goodsdetail;

import java.util.ArrayList;
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
import com.lgb.common.utils.WebDriverPool;
import com.lgb.goods.service.GoodsSourceService;
import com.lgb.webspider.downloader.SeleniumDownloader;

import us.codecraft.webmagic.Spider;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration({ "/spring-context.xml" }) // 加载配置文件
public class GoodsDetailSpiderTest {
	@Autowired
	private GoodsSourceService goodsSourceService;

	@Test
	public void test() {
		long start = System.currentTimeMillis();
		int spiderCount=2;
		List<String> urls = goodsSourceService.findUrlsBySource(Constant.PLATFORM_JD);
//		List<String> urls = Lists.newArrayList();
//		urls.add("http://item.jd.com/10614352223.html");
//		urls.add("http://item.jd.hk/19577758259.html");
		
		List<List<String>> list=new ArrayList<List<String>>();
		/**
		 * 初始化集合
		 */
		for (int i = 0; i < spiderCount; i++) {
			list.add(new ArrayList<String>());
		}
		for (int i = 0; i < urls.size(); i++) {
			list.get(i%spiderCount).add(urls.get(i));
		}

		ExecutorService executorService = Executors.newFixedThreadPool(spiderCount);
		for (List<String> list2 : list) {
			String url=list2.remove(0);
			SeleniumDownloader seleniumDownloader = new SeleniumDownloader();
			seleniumDownloader.setDriver(WebDriverPool.DRIVER_CHROME).setEvent(new GoodsCommintEvent());
			// 获取爬虫配置对象
			Spider spider = Spider.create(new GoodsDetailProcessor(list2)).addUrl(url).setDownloader(seleniumDownloader)
					.thread(1);
			executorService.execute(spider);
		}
		executorService.shutdown();

		try {// 等待直到所有任务完成
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
			long end = System.currentTimeMillis();
			System.out.println("GoodsDetailSpider本次耗时:" + (end - start) + "ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
