package com.lgb.webspider.ecp.jd.loadallbrand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.lgb.common.Constant;
import com.lgb.common.utils.ConfigUtil;
import com.lgb.goods.entity.GoodsCb;
import com.lgb.goods.service.GoodsCbService;
import com.lgb.webspider.SpiderTask;
import com.lgb.webspider.downloader.PageLoader;
import com.lgb.webspider.downloader.SeleniumDownloader;

import us.codecraft.webmagic.Spider;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月15日
 */
@Component
public class LoadAllBrandSpider implements SpiderTask {

	@Autowired
	private LoadAllBrandProcessor loadAllBrandProcessor;

	@Autowired
	private SeleniumDownloader seleniumDownloader;

	@Autowired
	private LoadAllBrandPipeline loadAllBrandPipeline;

	@Autowired
	private GoodsCbService goodsCbService;

	@Override
	public void execute() {
		List<GoodsCb> goodsCbs = goodsCbService.findBySource(Constant.PLATFORM_JD);
		List<String> urls = Lists.newArrayList();
		for (GoodsCb goodsCb : goodsCbs) {
			urls.add(goodsCb.getUrl());
		}

		seleniumDownloader.addConfig(new LoadAllScript(), PageLoader.DRIVER_CHROME);
		Spider.create(loadAllBrandProcessor).addUrl(urls.toArray(new String[urls.size()]))
				.setDownloader(seleniumDownloader).addPipeline(loadAllBrandPipeline).thread(ConfigUtil.getInteger("thread.pool")).run();

	}

}
