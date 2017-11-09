package com.lgb.webspider.jd.processor.common;

import com.lgb.common.downloader.SeleniumDownloader;
import com.lgb.common.processor.CommonProcessor;
import com.lgb.common.utils.SpringContextHelper;

import us.codecraft.webmagic.Page;

public class GoodsDetailProcessor extends CommonProcessor {

	@Override
	public void process(Page page) {
		
	}

	@Override
	public void execute() {
		// SeleniumDownloader downloader=new
		// SeleniumDownloader("D:\\chromedriver.exe", new LoadMoreScript());
		GoodsDetailProcessor goodsDetailProcessor = (GoodsDetailProcessor) SpringContextHelper
				.getBean("goodsSourceProcessor");
		// 获取爬虫配置对象
		us.codecraft.webmagic.Spider.create(goodsDetailProcessor)
				.addUrl("https://item.jd.com/15503252649.html")
				//.setDownloader(downloader)
				.thread(1)
				.run();
	}
	
	public static void main(String[] args) {
		SeleniumDownloader downloader=new SeleniumDownloader("D:\\chromedriver.exe", null);
		GoodsDetailProcessor goodsDetailProcessor = new GoodsDetailProcessor();
		// 获取爬虫配置对象
		us.codecraft.webmagic.Spider.create(goodsDetailProcessor)
				.addUrl("https://item.jd.com/15503252649.html")
				.setDownloader(downloader)
				.thread(1)
				.run();
	}

}
