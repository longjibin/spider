package com.lgb.webspider.ecp.jd.goodssource;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang3.StringUtils;

import com.lgb.common.Constant;
import com.lgb.common.utils.SpringContextHelper;
import com.lgb.common.utils.UrlResolver;
import com.lgb.goods.entity.GoodsBrand;
import com.lgb.goods.entity.GoodsSource;
import com.lgb.goods.service.GoodsBrandService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 商品源信息爬虫
 * 
 * @author Administrator
 *
 * @date 2017年11月2日
 */
public class GoodsSourceProcessor implements PageProcessor {

	private GoodsBrandService goodsBrandService = (GoodsBrandService) SpringContextHelper
			.getBean("goodsBrandServiceImpl");

	/**
	 * 判断url中的页号是否有效
	 * 
	 * @param url
	 * @return true表示有效 false表示无效
	 */
	private Boolean isValidPageNo(String url) {
		/**
		 * 解析url获取page参数值
		 */
		String pageNoStr = UrlResolver.analysis(url).get("page");
		if (StringUtils.isBlank(pageNoStr)) {
			// 默认第一页
			pageNoStr = "1";
		}
		// 如果页号<=0,则排除
		if (Integer.parseInt(pageNoStr) <= 0) {
			return false;
		}
		return true;
	}

	@Override
	public void process(Page page) {
		Html html = page.getHtml();
		String requestUrl = page.getRequest().getUrl();
		try {
			requestUrl = URLDecoder.decode(requestUrl, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String sbId = UrlResolver.analysis(requestUrl).get("ev");
		sbId = sbId.substring(sbId.indexOf("_") + 1);
		GoodsBrand goodsBrand = goodsBrandService.findBySbId(sbId);
		String brandId = goodsBrand.getId();
		String categoryId = goodsBrand.getCategoryId();

		ResultItems resultItems = page.getResultItems();
		// 获取商品集合
		Selectable goodsList = html.xpath("//*[@id='plist']/ul/li");

		String host = "http://item.jd.com/";
		GoodsSource goodsSource = null;
		for (Selectable goods : goodsList.nodes()) {
			/**
			 * 解析封面商品并保存到集合
			 */
			goodsSource = new GoodsSource();
			goodsSource.setCategoryId(categoryId);
			goodsSource.setBrandId(brandId);
			goodsSource.setSku(goods.xpath("li/div/@data-sku").toString());
			goodsSource.setUrl(host + goodsSource.getSku() + ".html");
			goodsSource.setSource(Constant.PLATFORM_JD);
			resultItems.put(goodsSource.getSku(), goodsSource);

			/**
			 * 解析关联商品并保存到集合
			 */
			Selectable items = goods.xpath("li/div/div[2]/div/ul/li");
			for (Selectable item : items.nodes()) {
				String sku = item.xpath("li/@ids").toString();
				if (StringUtils.isNotBlank(sku)) {
					goodsSource = new GoodsSource();
					goodsSource.setCategoryId(categoryId);
					goodsSource.setBrandId(brandId);
					goodsSource.setSku(sku);
					goodsSource.setUrl(host + sku + ".html");
					goodsSource.setSource(Constant.PLATFORM_JD);
					resultItems.put(sku, goodsSource);
				}
			}
		}

		Selectable pageUrls = html.xpath("//*[@id='J_bottomPage']/span[1]/a");
		for (Selectable pageUrl : pageUrls.nodes()) {
			String url = pageUrl.xpath("a/@href").toString();
			if (!url.contains("undefined") && isValidPageNo(url)) {
				page.addTargetRequest(url);
			}
		}

	}

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(3000);
	}

}
