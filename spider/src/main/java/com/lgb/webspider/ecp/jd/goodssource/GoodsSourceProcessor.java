package com.lgb.webspider.ecp.jd.goodssource;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lgb.common.processor.AbstractProcessor;
import com.lgb.common.utils.UrlResolver;
import com.lgb.goods.entity.GoodsBrand;
import com.lgb.goods.entity.GoodsSource;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 商品源信息爬虫
 * 
 * @author Administrator
 *
 * @date 2017年11月2日
 */
public class GoodsSourceProcessor extends AbstractProcessor {

	/**
	 * 商品品牌信息
	 */
	private GoodsBrand goodsBrand;
	
	/**
	 * 页号列表
	 */
	private List<Integer> pageNos = new ArrayList<Integer>();

	public GoodsSourceProcessor(GoodsBrand goodsBrand) {
		this.goodsBrand = goodsBrand;
	}

	/**
	 * 判断是否重复访问相同页号
	 * 
	 * @param url
	 * @return true表示重复 false表示不重复
	 */
	private Boolean havePageNo(String url) {
		/**
		 * 解析url获取page参数值
		 */
		UrlResolver.analysis(url);
		String pageNoStr = UrlResolver.getValue("page");
		if (StringUtils.isBlank(pageNoStr)) {
			// 默认第一页
			pageNoStr = "1";
		}
		Integer pageNo = Integer.parseInt(pageNoStr);
		Boolean isHave = false;
		// 如果页号<=0,则排除
		if (pageNo <= 0) {
			isHave = true;
		} else {
			for (Integer page : pageNos) {
				if (page.equals(pageNo)) {
					isHave = true;
					break;
				}
			}
		}
		if (!isHave) {
			pageNos.add(pageNo);
		}
		return isHave;
	}

	@Override
	public void process(Page page) {
		ResultItems resultItems = page.getResultItems();
		// 获取商品集合
		Selectable goodsList = page.getHtml().xpath("//*[@id='plist']/ul/li");

		GoodsSource goodsSource = null;
		for (Selectable goods : goodsList.nodes()) {
			/**
			 * 解析封面商品并保存到集合
			 */
			goodsSource = new GoodsSource();
			goodsSource.setBrandId(goodsBrand.getId());
			goodsSource.setSku(goods.xpath("li/div/@data-sku").toString());
			goodsSource.setUrl("http://item.jd.com/" + goodsSource.getSku() + ".html");
			resultItems.put(goodsSource.getSku(), goodsSource);

			/**
			 * 解析关联商品并保存到集合
			 */
			Selectable items = goods.xpath("li/div/div[2]/div/ul/li");
			for (Selectable item : items.nodes()) {
				String sku = item.xpath("li/@ids").toString();
				if (StringUtils.isNotBlank(sku)) {
					goodsSource = new GoodsSource();
					goodsSource.setBrandId(goodsBrand.getId());
					goodsSource.setSku(sku);
					goodsSource.setUrl("http://item.jd.com/" + sku + ".html");
					resultItems.put(goodsSource.getSku(), goodsSource);
				}
			}
		}

		Selectable pageUrls = page.getHtml().xpath("//*[@id='J_bottomPage']/span[1]/a");
		for (Selectable pageUrl : pageUrls.nodes()) {
			String url = pageUrl.xpath("a/@href").toString();
			if (!url.contains("undefined") && !havePageNo(url)) {
				page.addTargetRequest(url);
			}
		}

	}

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(3000);
	}

}
