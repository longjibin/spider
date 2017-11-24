package com.lgb.webspider.ecp.jd.loadallbrand;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.lgb.common.Constant;
import com.lgb.common.utils.ConfigUtil;
import com.lgb.goods.entity.GoodsCb;
import com.lgb.goods.service.GoodsCbService;
import com.lgb.webspider.downloader.PageLoader;
import com.lgb.webspider.downloader.SeleniumDownloader;

import us.codecraft.webmagic.Spider;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"/spring-context.xml"}) //加载配置文件	
public class LoadAllBrandSpiderTest {

	@Autowired
	private LoadAllBrandProcessor loadAllBrandProcessor;

	@Autowired
	private SeleniumDownloader seleniumDownloader;

	@Autowired
	private LoadAllBrandPipeline loadAllBrandPipeline;

	@Autowired
	private GoodsCbService goodsCbService;
	
	@Test
	public void test() {
		long start=System.currentTimeMillis();
		List<GoodsCb> goodsCbs = goodsCbService.findBySource(Constant.PLATFORM_JD);
		List<String> urls = Lists.newArrayList();
		for (GoodsCb goodsCb : goodsCbs) {
			urls.add(goodsCb.getUrl());
		}

		seleniumDownloader.setDriverName(PageLoader.DRIVER_PHANTOMJS).setScript(new LoadAllScript());
		Spider.create(loadAllBrandProcessor).addUrl(urls.toArray(new String[urls.size()]))
				.setDownloader(seleniumDownloader).addPipeline(loadAllBrandPipeline).thread(ConfigUtil.getInteger("thread.pool")).run();
		long end=System.currentTimeMillis();
		System.out.println("LoadAllBrandSpider本次耗时:"+(end-start)+"ms");
	}

}
