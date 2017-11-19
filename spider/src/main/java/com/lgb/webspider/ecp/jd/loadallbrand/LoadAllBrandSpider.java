package com.lgb.webspider.ecp.jd.loadallbrand;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lgb.common.utils.ConfigUtil;
import com.lgb.common.utils.FileUtils;
import com.lgb.common.utils.WebDriverPool;
import com.lgb.webspider.SpiderTask;
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

	@Override
	public void execute() {
		// 获取配置
		String jsonContent = FileUtils.readFileToString("spider/config/jd/brands.json", "utf-8");
		@SuppressWarnings("unchecked")
		Map<String, String> configMap = JSON.parseObject(jsonContent, Map.class);

		/**
		 * 定义可执行的爬虫最大数量
		 */
		for (int i = 0; i < 2; i++) {
			Spider.create(new LoadAllBrandProcessor(configMap))
			.addUrl(configMap.keySet().toArray(new String[configMap.size()]))
			.setDownloader(new SeleniumDownloader("phantomjs.binary.path", ConfigUtil.getString("driver.path"),
					WebDriverPool.DRIVER_PHANTOMJS, new LoadAllScript()))
			.addPipeline(new LoadAllBrandPipeline()).runAsync();
		}
		
	}

}
