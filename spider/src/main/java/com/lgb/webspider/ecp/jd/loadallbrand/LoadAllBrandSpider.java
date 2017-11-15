package com.lgb.webspider.ecp.jd.loadallbrand;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lgb.common.utils.FileUtils;
import com.lgb.webspider.SpiderTask;
import com.lgb.webspider.downloader.PhantomJsDownloader;

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
	private PhantomJsDownloader phantomJsDownloader;

	@Override
	public void execute() {

		// 获取配置
		String jsonContent = FileUtils.readFileToString("spider/config/jd/brands.json", "utf-8");
		@SuppressWarnings("unchecked")
		Map<String, String> configMap = JSON.parseObject(jsonContent, Map.class);

		/**
		 * 启动爬虫
		 */
		phantomJsDownloader.setScript(new LoadAllBrandScript());
		Spider.create(new LoadAllBrandProcessor(configMap))
				.addUrl(configMap.keySet().toArray(new String[configMap.keySet().size()]))
				.setDownloader(phantomJsDownloader)
				.addPipeline(new LoadAllBrandPipeline())
				.run();
	}

}
