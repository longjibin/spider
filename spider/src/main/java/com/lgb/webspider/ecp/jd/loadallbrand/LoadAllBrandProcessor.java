package com.lgb.webspider.ecp.jd.loadallbrand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgb.common.Constant;
import com.lgb.goods.entity.GoodsBrand;
import com.lgb.goods.entity.GoodsCb;
import com.lgb.goods.service.GoodsCbService;

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
@Component
public class LoadAllBrandProcessor implements PageProcessor {

	@Autowired
	private GoodsCbService goodsCbService;
	
	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(3000);
	}

	@Override
	public void process(Page page) {
		GoodsCb goodsCb=goodsCbService.findBySourceAndUrl(Constant.PLATFORM_JD, page.getRequest().getUrl());

		Selectable lis = page.getHtml().xpath("//ul[@id='brandsArea']/li");

		ResultItems resultItems = page.getResultItems();

		GoodsBrand goodsBrand = null;
		for (Selectable li : lis.nodes()) {
			goodsBrand = new GoodsBrand();
			// 设置电商品牌id brand-8557
			String sbId = li.xpath("li/@id").toString();
			goodsBrand.setSbId(sbId.substring(sbId.indexOf("-") + 1));
			// 设置品牌来源
			goodsBrand.setSource(Constant.PLATFORM_JD);
			// 设置品牌名
			goodsBrand.setBrand(li.xpath("li/a/text()").toString());
			// 设置商品列表url
			goodsBrand.setGoodsListUrl("http://list.jd.com"+li.xpath("li/a/@href").toString());
			// 设置关联的分类
			goodsBrand.setCategoryId(goodsCb.getCategoryId());

			resultItems.put(goodsBrand.getSbId(), goodsBrand);
		}
	}
}
