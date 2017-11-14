package com.lgb.webspider.ecp.jd.goodsdetail;

import com.lgb.common.processor.AbstractProcessor;
import com.lgb.common.utils.SpringContextHelper;
import com.lgb.webspider.downloader.SeleniumDownloader;

import us.codecraft.webmagic.Page;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
public class GoodsDetailProcessor extends AbstractProcessor {

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
