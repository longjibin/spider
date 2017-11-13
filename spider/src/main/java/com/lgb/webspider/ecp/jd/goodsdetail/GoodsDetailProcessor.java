package com.lgb.webspider.ecp.jd.goodsdetail;

import com.lgb.common.processor.CommonProcessor;
import com.lgb.common.utils.SpringContextHelper;
import com.lgb.goods.entity.GoodsDetail;
import com.lgb.webspider.downloader.PhantomJSDownloader;
import com.lgb.webspider.downloader.SeleniumDownloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

public class GoodsDetailProcessor extends CommonProcessor {

	private String sku="15503252649";
	
	private String source="JD";
	
	private String goodsDetailUrl="http://item.jd.com/15503252649.html";
	
	@Override
	public void process(Page page) {
		Html html=page.getHtml();
		GoodsDetail goodsDetail=new GoodsDetail();
		goodsDetail.setSku(sku);
		goodsDetail.setPrice(Double.parseDouble(html.xpath("/html/body/div[7]/div/div[2]/div[3]/div/div[1]/div[2]/span[1]/span[2]/text()").toString().trim()));
		goodsDetail.setGoodsName(html.xpath("/html/body/div[7]/div/div[2]/div[1]/text()").toString().trim());
		goodsDetail.setGoodsCommit(html.xpath("//*[@id='detail']/div[1]/ul/li[5]/s/text()").toString());
		goodsDetail.setShopName(html.xpath("//*[@id='crumb-wrap']/div/div[2]/div[2]/div[1]/div/a/text()").toString());
		goodsDetail.setShopScore(Double.parseDouble(html.xpath("//*[@id='crumb-wrap']/div/div[2]/div[2]/div[2]/div/div/em/span/a/text()").toString().trim()));
		goodsDetail.setOther(html.xpath("//*[@id='p-ad']/text()").toString());
		goodsDetail.setGoodsDetailUrl(goodsDetailUrl);
		goodsDetail.setSource(source);
		System.out.println(goodsDetail);
	}

	@Override
	public void execute() {
		SeleniumDownloader downloader=new SeleniumDownloader("D:/chromedriver.exe", null);
		GoodsDetailProcessor goodsDetailProcessor = (GoodsDetailProcessor) SpringContextHelper
				.getBean("goodsSourceProcessor");
		// 获取爬虫配置对象
		us.codecraft.webmagic.Spider.create(goodsDetailProcessor).addUrl("https://item.jd.com/15503252649.html")
				 .setDownloader(downloader)
				.thread(1).run();
	}

	public static void main(String[] args) {
		PhantomJSDownloader downloader = new PhantomJSDownloader(
				GoodsDetailProcessor.class.getClassLoader().getResource("driver/phantomjs.exe").getPath(), null);
//		SeleniumDownloader downloader=new SeleniumDownloader("D:/chromedriver.exe", null);
		GoodsDetailProcessor goodsDetailProcessor = new GoodsDetailProcessor();
		// 获取爬虫配置对象
		us.codecraft.webmagic.Spider.create(goodsDetailProcessor).addUrl("http://item.jd.com/15503252649.html")
				.setDownloader(downloader).thread(1).run();
	}

}
