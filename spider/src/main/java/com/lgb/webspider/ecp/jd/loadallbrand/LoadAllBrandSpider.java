package com.lgb.webspider.ecp.jd.loadallbrand;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lgb.common.Constant;
import com.lgb.common.utils.FileUtils;
import com.lgb.goods.entity.GoodsCategory;
import com.lgb.goods.service.GoodsCategoryService;
import com.lgb.webspider.SpiderTask;
import com.lgb.webspider.downloader.PhantomJsDownloader;

import us.codecraft.webmagic.Spider;

@Component
public class LoadAllBrandSpider implements SpiderTask {
	private static final Logger LOGGER = Logger.getLogger(LoadAllBrandSpider.class);

	@Autowired
	private GoodsCategoryService goodsCategoryService;

	@Override
	public void execute() {
		// 获取配置
		String jsonContent = FileUtils.readFileToString("spider/config/jd/brands.json", "utf-8");
		@SuppressWarnings("unchecked")
		Map<String, String> map = JSON.parseObject(jsonContent, Map.class);

		for (Entry<String, String> entry : map.entrySet()) {
			StringBuffer path = new StringBuffer();
			/**
			 * 获取当前分类路径
			 */
			GoodsCategory category = goodsCategoryService.findById(entry.getKey());
			for (String id : category.getParents().split(Constant.CATEGORY_PARENT_SPLIT)) {
				path.append(goodsCategoryService.findById(id).getName()).append("-");
			}
			path.append(category.getName());
			LOGGER.info("分类路径:" + path.toString());

			/**
			 * 启动爬虫
			 */
			Spider.create(new LoadAllBrandProcessor(entry.getKey())).addUrl(entry.getValue())
					.setDownloader(new PhantomJsDownloader(
							PhantomJsDownloader.class.getClassLoader().getResource("driver/phantomjs.exe").getPath(),
							new LoadAllBrandScript()))
					.addPipeline(new LoadAllBrandPipeline()).
					thread(1).
					runAsync();
		}
	}

}
