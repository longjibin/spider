package com.lgb.webspider.ecp.jd.goodsdetail;

import com.lgb.common.Constant;
import com.lgb.common.processor.AbstractProcessor;
import com.lgb.goods.entity.GoodsDetail;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.selector.Html;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
public class GoodsDetailProcessor extends AbstractProcessor {

	@Override
	public void process(Page page) {
		ResultItems resultItems=page.getResultItems();
		Html html=page.getHtml();
		String url=page.getRequest().getUrl();
		GoodsDetail goodsDetail=new GoodsDetail();
		goodsDetail.setSku(url.substring(url.lastIndexOf("/")+1, url.indexOf(".html")));
		goodsDetail.setPrice(Double.parseDouble(html.xpath("/html/body/div[7]/div/div[2]/div[3]/div/div[1]/div[2]/span[1]/span[2]/text()").toString().trim()));
		goodsDetail.setGoodsName(html.xpath("/html/body/div[7]/div/div[2]/div[1]/text()").toString().trim());
		goodsDetail.setGoodsCommit(html.xpath("//*[@id='detail']/div[1]/ul/li[5]/s/text()").toString());
		goodsDetail.setShopName(html.xpath("//*[@id='crumb-wrap']/div/div[2]/div[2]/div[1]/div/a/text()").toString());
		goodsDetail.setShopScore(Double.parseDouble(html.xpath("//*[@id='crumb-wrap']/div/div[2]/div[2]/div[2]/div/div/em/span/a/text()").toString().trim()));
		goodsDetail.setOther(html.xpath("//*[@id='p-ad']/text()").toString());
		goodsDetail.setGoodsDetailUrl(url);
		goodsDetail.setSource(Constant.PLATFORM_JD);
		
		resultItems.put(goodsDetail.getSku(), goodsDetail);
	}

}
