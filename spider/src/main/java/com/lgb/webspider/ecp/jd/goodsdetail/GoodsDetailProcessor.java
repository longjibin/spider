package com.lgb.webspider.ecp.jd.goodsdetail;

import java.util.List;

import com.lgb.common.Constant;
import com.lgb.common.utils.SpringContextHelper;
import com.lgb.goods.entity.GoodsDetail;
import com.lgb.goods.entity.GoodsSource;
import com.lgb.goods.service.GoodsDetailService;
import com.lgb.goods.service.GoodsSourceService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
public class GoodsDetailProcessor implements PageProcessor {

	private GoodsSourceService goodsSourceService=(GoodsSourceService) SpringContextHelper.getBean("goodsSourceServiceImpl");
	
	private GoodsDetailService goodsDetailService=(GoodsDetailService) SpringContextHelper.getBean("goodsDetailServiceImpl");
	
	private List<String> urls;
	
	private Boolean isFirst=true;
	
	public GoodsDetailProcessor(List<String> urls) {
		super();
		this.urls = urls;
	}

	@Override
	public void process(Page page) {
		Html html=page.getHtml();
		String url=page.getRequest().getUrl();
		
		String sku=url.substring(url.lastIndexOf("/")+1, url.indexOf(".html"));
		GoodsSource goodsSource=goodsSourceService.findBySkuAndSource(sku, Constant.PLATFORM_JD);
		
		GoodsDetail goodsDetail=new GoodsDetail();
		goodsDetail.setCategoryId(goodsSource.getCategoryId());
		goodsDetail.setBrandId(goodsSource.getBrandId());
		goodsDetail.setSku(sku);
		goodsDetail.setPrice(Double.parseDouble(html.xpath("//span[@class='price J-p-"+sku+"']/text()").toString()));
		goodsDetail.setGoodsName(html.xpath("//div[@class='sku-name']/text()").toString().trim());
		goodsDetail.setGoodsCommint(html.xpath("//li[@data-anchor='#comment']/s/text()").toString());
		
		//尝试获取国内店的好评率
		Selectable selectable=html.xpath("//*[@id='comment']/div[2]/div[1]/div[1]/div");
		if(!selectable.nodes().isEmpty()){
			goodsDetail.setGoodCommint(selectable.xpath("div/text()").toString());
			goodsDetail.setShopName(html.xpath("//*[@id='crumb-wrap']/div/div[2]/div[2]/div[1]/div/a/text()").toString());
			goodsDetail.setShopScore(Double.parseDouble(html.xpath("//*[@id='crumb-wrap']/div/div[2]/div[2]/div[2]/div/div/em/span/a/text()").toString().trim()));
			goodsDetail.setGoodsType(GoodsDetail.INLAND_SHOP);
		}else{
			goodsDetail.setGoodCommint(html.xpath("//*[@id='i-comment']/div[1]/strong/text()").toString());
			goodsDetail.setShopName(html.xpath("//*[@id='nav-shop']/div/div/div/div[1]/div[1]/strong/span/a/text()").toString());
			goodsDetail.setGoodsType(GoodsDetail.GLOBAL_SHOP);
		}
		
		goodsDetail.setOther(html.xpath("//*[@id='p-ad']/text()").toString().trim());
		
		goodsDetail.setGoodsUrl(url);
		goodsDetail.setSource(Constant.PLATFORM_JD);
		
		goodsDetailService.save(goodsDetail, goodsDetailService.count(goodsDetail)==0?true:false);
		
		if(isFirst){
			page.addTargetRequests(urls);
			isFirst=false;
		}
	}

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(3000);
	}

}
