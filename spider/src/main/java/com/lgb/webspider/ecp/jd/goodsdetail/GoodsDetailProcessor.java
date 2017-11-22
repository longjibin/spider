package com.lgb.webspider.ecp.jd.goodsdetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgb.common.Constant;
import com.lgb.common.processor.AbstractProcessor;
import com.lgb.goods.entity.GoodsDetail;
import com.lgb.goods.service.GoodsDetailService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
@Component
public class GoodsDetailProcessor extends AbstractProcessor {

	@Autowired
	private GoodsDetailService goodsDetailService;
	
	@Override
	public void process(Page page) {
		Html html=page.getHtml();
		String url=page.getRequest().getUrl();
		String goodCommint=page.getResultItems().get("goodCommint");
		GoodsDetail goodsDetail=new GoodsDetail();
		goodsDetail.setSku(url.substring(url.lastIndexOf("/")+1, url.indexOf(".html")));
		goodsDetail.setPrice(Double.parseDouble(html.xpath("/html/body/div[7]/div/div[2]/div[3]/div/div[1]/div[2]/span[1]/span[2]/text()").toString().trim()));
		goodsDetail.setGoodsName(html.xpath("/html/body/div[7]/div/div[2]/div[1]/text()").toString().trim());
		goodsDetail.setGoodsCommint(html.xpath("//*[@id='detail']/div[1]/ul/li[5]/s/text()").toString());
		goodsDetail.setGoodCommint(goodCommint);
		goodsDetail.setShopName(html.xpath("//*[@id='crumb-wrap']/div/div[2]/div[2]/div[1]/div/a/text()").toString());
		goodsDetail.setShopScore(Double.parseDouble(html.xpath("//*[@id='crumb-wrap']/div/div[2]/div[2]/div[2]/div/div/em/span/a/text()").toString().trim()));
		goodsDetail.setOther(html.xpath("//*[@id='p-ad']/text()").toString());
		goodsDetail.setGoodsUrl(url);
		goodsDetail.setSource(Constant.PLATFORM_JD);
		
		goodsDetailService.save(goodsDetail, goodsDetailService.count(goodsDetail)==0?true:false);
	}

}
