package com.lgb.webspider.ecp.jd.goodsdetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgb.common.utils.ConfigUtil;
import com.lgb.goods.service.GoodsSourceService;
import com.lgb.webspider.SpiderTask;
import com.lgb.webspider.downloader.PageLoader;
import com.lgb.webspider.downloader.SeleniumDownloader;

import us.codecraft.webmagic.Spider;

@Component
public class GoodsDetailSpider implements SpiderTask {
	
	@Autowired
	private GoodsSourceService goodsSourceService;
	
	@Autowired
	private GoodsDetailProcessor goodsDetailProcessor;
	@Autowired
	private SeleniumDownloader seleniumDownloader;
	
	@Override
	public void execute() {
		
//		List<String> urls=goodsSourceService.findUrlsBySource(Constant.PLATFORM_JD);
		
		seleniumDownloader.addConfig(new GoodsCommintScript(), PageLoader.DRIVER_CHROME);
		// 获取爬虫配置对象
		Spider.create(goodsDetailProcessor).addUrl("http://item.jd.com/11491676217.html")
				.setDownloader(seleniumDownloader)
				.thread(ConfigUtil.getInteger("thread.pool"))
				.run();
	}

}
