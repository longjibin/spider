package com.lgb.webspider.ecp.jd.loadallbrand;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lgb.common.Constant;
import com.lgb.common.processor.AbstractProcessor;
import com.lgb.common.utils.FileUtils;
import com.lgb.common.utils.SpringContextHelper;
import com.lgb.common.utils.URLResolver;
import com.lgb.goods.entity.GoodsBrand;
import com.lgb.webspider.downloader.PhantomJSDownloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 京东 爬取指定分类下的所有品牌
 * 
 * @author Administrator
 *
 * @date 2017年11月3日
 */
@Component
public class LoadAllBrandProcessor extends AbstractProcessor {

	private static final Logger LOGGER = Logger.getLogger(LoadAllBrandProcessor.class);

	@Autowired
	private LoadAllBrandPipeline loadAllBrandPipeline;

	/**
	 * 指定分类id
	 */
	private String categoryId;

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(3000);
	}

	@Override
	public void process(Page page) {
		Selectable lis = page.getHtml().xpath("//ul[@id='brandsArea']/li");
		ResultItems resultItems = page.getResultItems();

		GoodsBrand goodsBrand = null;
		for (Selectable li : lis.nodes()) {
			goodsBrand = new GoodsBrand();
			String url = li.xpath("li/a/@href").toString();
			URLResolver.analysis(url);
			// 设置电商品牌id brand-8557
			String sbId = li.xpath("li/@id").toString();
			goodsBrand.setSbId(sbId.substring(sbId.indexOf("-") + 1));
			// 设置品牌来源
			goodsBrand.setSource(Constant.PLATFORM_JD);
			// 设置品牌名
			goodsBrand.setBrand(li.xpath("li/a/text()").toString());
			// 设置商品列表url
			goodsBrand.setGoodsListUrl(li.xpath("li/a/@href").toString());
			// 设置关联的分类
			goodsBrand.setCategoryId(categoryId);

			resultItems.put(goodsBrand.getSbId(), goodsBrand);
		}
	}

	@Override
	public void execute() {
		// 获取配置
		String jsonContent = FileUtils.readFileToString("spider/config/jd/brands.json", "utf-8");
		@SuppressWarnings("unchecked")
		Map<String, String> map = JSON.parseObject(jsonContent, Map.class);
		// 初始化页面下载器并设置脚本动作
		PhantomJSDownloader downloader = new PhantomJSDownloader(
				PhantomJSDownloader.class.getClassLoader().getResource("driver/phantomjs.exe").getPath(),
				new LoadAllBrandScript());
		// 加载页面处理器
		LoadAllBrandProcessor loadAllBrandProcessor = (LoadAllBrandProcessor) SpringContextHelper
				.getBean("loadAllBrandProcessor");
		for (Entry<String, String> entry : map.entrySet()) {
			LOGGER.info("key:" + entry.getKey() + "\t value:" + entry.getValue());
			categoryId = entry.getKey();

			Spider.create(loadAllBrandProcessor).addUrl(entry.getValue()).setDownloader(downloader)
					.addPipeline(loadAllBrandPipeline).thread(1).run();
		}
	}

}
