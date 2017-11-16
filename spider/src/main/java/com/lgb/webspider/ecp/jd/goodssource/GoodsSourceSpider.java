package com.lgb.webspider.ecp.jd.goodssource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgb.common.Constant;
import com.lgb.goods.entity.GoodsBrand;
import com.lgb.goods.service.GoodsBrandService;
import com.lgb.webspider.SpiderTask;
import com.lgb.webspider.downloader.PhantomJsDownloader;

import us.codecraft.webmagic.Spider;

@Component
public class GoodsSourceSpider implements SpiderTask {

	@Autowired
	private GoodsBrandService goodsBrandService;

	@Override
	public void execute() {
		/**
		 * 查询京东手机分类下的所有品牌
		 */
		List<GoodsBrand> goodsBrands = goodsBrandService.selectBySourceAndCategoryId(Constant.PLATFORM_JD, "3");
		for (GoodsBrand goodsBrand : goodsBrands) {
			// 获取爬虫配置对象
			PhantomJsDownloader phantomJsDownloader=new PhantomJsDownloader();
			phantomJsDownloader.setScript(new LoadMoreScript());
			Spider.create(new GoodsSourceProcessor(goodsBrand)).addUrl(goodsBrand.getGoodsListUrl())
					.setDownloader(phantomJsDownloader).addPipeline(new GoodsSourcePipeline(goodsBrand)).thread(2).run();
		}

	}

}
