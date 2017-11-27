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
		String goodCommint=page.getResultItems().get("goodCommint");
		
		String sku=url.substring(url.lastIndexOf("/")+1, url.indexOf(".html"));
		GoodsSource goodsSource=goodsSourceService.findBySkuAndSource(sku, Constant.PLATFORM_JD);
		
		GoodsDetail goodsDetail=new GoodsDetail();
		goodsDetail.setCategoryId(goodsSource.getCategoryId());
		goodsDetail.setBrandId(goodsSource.getBrandId());
		goodsDetail.setSku(sku);
		
		String price=html.xpath("//span[@class='price J-p-"+sku+"']/text()").toString();
		System.out.println(price);
//		if(!selectable.nodes().isEmpty()){
//			goodsDetail.setPrice(Double.parseDouble(html.xpath("/html/body/div[7]/div/div[2]/div[3]/div/div[1]/div[2]/span[1]/span[2]/text()").toString().trim()));
//			goodsDetail.setGoodsName(html.xpath("/html/body/div[7]/div/div[2]/div[1]/text()").toString().trim());
//			goodsDetail.setGoodsCommint(html.xpath("//*[@id='detail']/div[1]/ul/li[5]/s/text()").toString());
//			goodsDetail.setShopName(html.xpath("//*[@id='crumb-wrap']/div/div[2]/div[2]/div[1]/div/a/text()").toString());
//			goodsDetail.setShopScore(Double.parseDouble(html.xpath("//*[@id='crumb-wrap']/div/div[2]/div[2]/div[2]/div/div/em/span/a/text()").toString().trim()));
//			goodsDetail.setOther(html.xpath("//*[@id='p-ad']/text()").toString());
//		}else{
//			/**
//			 * 海外购
//			 */
//			/html/body/div[5]/div/div[2]/div[3]/div/div[1]/div[2]/span[1]/span[2]
//			goodsDetail.setPrice(Double.parseDouble(html.xpath("//*[@id='summary-wrap']/div/div[1]/div[2]/span/span[2]/text()").toString().trim()));
//			goodsDetail.setGoodsName(html.xpath("/html/body/div[6]/div/div[2]/div[2]/text()").toString().trim());
//			goodsDetail.setGoodsCommint(html.xpath("//*[@id='detail']/div[1]/ul/li[3]/s/text()").toString());
//			goodsDetail.setShopName(html.xpath("//*[@id='nav-shop']/div/div/div/div[1]/div[1]/strong/span/a/text()").toString());
////			goodsDetail.setShopScore(Double.parseDouble(html.xpath("//*[@id='crumb-wrap']/div/div[2]/div[2]/div[2]/div/div/em/span/a/text()").toString().trim()));
//			goodsDetail.setOther(html.xpath("//*[@id='p-ad']/text()").toString());
//		}
//		
//		goodsDetail.setGoodsUrl(url);
//		goodsDetail.setGoodCommint(goodCommint);
//		goodsDetail.setSource(Constant.PLATFORM_JD);
//		
//		goodsDetailService.save(goodsDetail, goodsDetailService.count(goodsDetail)==0?true:false);
		
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
