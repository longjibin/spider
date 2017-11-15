package com.lgb.webspider.ecp.jd.loadallbrand;

import java.util.Map;

import com.lgb.common.Constant;
import com.lgb.common.utils.UrlResolver;
import com.lgb.goods.entity.GoodsBrand;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 京东 爬取指定分类下的所有品牌
 * 
 * @author Administrator
 *
 * @date 2017年11月3日
 */
public class LoadAllBrandProcessor implements PageProcessor {

	/**
	 * 配置
	 */
	private Map<String, String> configMap;
	
	public LoadAllBrandProcessor(Map<String, String> configMap) {
		this.configMap = configMap;
	}

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(3000);
	}

	@Override
	public void process(Page page) {
		String categoryId=configMap.get(page.getRequest().getUrl());
		
		Selectable lis = page.getHtml().xpath("//ul[@id='brandsArea']/li");
		ResultItems resultItems = page.getResultItems();

		GoodsBrand goodsBrand = null;
		for (Selectable li : lis.nodes()) {
			goodsBrand = new GoodsBrand();
			String url = li.xpath("li/a/@href").toString();
			UrlResolver.analysis(url);
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
}
