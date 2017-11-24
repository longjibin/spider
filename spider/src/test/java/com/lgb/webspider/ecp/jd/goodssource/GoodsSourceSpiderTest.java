package com.lgb.webspider.ecp.jd.goodssource;

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
import com.lgb.goods.entity.GoodsBrand;
import com.lgb.goods.service.GoodsBrandService;
import com.lgb.webspider.downloader.SeleniumDownloader2;

import us.codecraft.webmagic.Spider;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration({ "/spring-context.xml" }) // 加载配置文件
public class GoodsSourceSpiderTest {

	@Autowired
	private GoodsBrandService goodsBrandService;

	@Test
	public void test() {
		long start = System.currentTimeMillis();
		/**
		 * 查询京东手机分类下的所有品牌
		 */
		List<GoodsBrand> goodsBrands = goodsBrandService.selectBySourceAndCategoryId(Constant.PLATFORM_JD, "3");

		// 最多3个爬虫同时执行
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		for (GoodsBrand goodsBrand : goodsBrands) {
			SeleniumDownloader2 seleniumDownloader2 = new SeleniumDownloader2();
			seleniumDownloader2.setEvent(new LoadMoreEvent()).setDriver(WebDriverPool.DRIVER_PHANTOMJS);
			Spider spider = Spider.create(new GoodsSourceProcessor()).addUrl(goodsBrand.getGoodsListUrl())
					.setDownloader(seleniumDownloader2).addPipeline(new GoodsSourcePipeline())
					.thread(ConfigUtil.getInteger("thread.pool"));
			executorService.execute(spider);
		}
		executorService.shutdown();
		
		try {// 等待直到所有任务完成
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
			long end = System.currentTimeMillis();
			System.out.println("GoodsSourceSpider本次耗时:" + (end - start) + "ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
