package com.lgb.webspider.ecp.jd.goodsdetail;

import org.springframework.stereotype.Component;

import com.lgb.webspider.SpiderTask;

@Component
public class GoodsDetailSpider implements SpiderTask {
	
//	@Autowired
//	private GoodsSourceService goodsSourceService;
	
	@Override
	public void execute() {
		// 获取爬虫配置对象
		us.codecraft.webmagic.Spider.create(new GoodsDetailProcessor()).addUrl("http://item.jd.com/15503252649.html")
				.addPipeline(new GoodsDetailPipeline())
//				.setDownloader(new PhantomJsDownloader())
				.thread(1)
				.run();
	}

}
