package com.lgb.webspider.ecp.jd.goodsdetail;

import java.util.List;

import org.apache.log4j.Logger;

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
	
	private static final Logger LOGGER=Logger.getLogger(GoodsDetailProcessor.class);

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
		
		if(!html.xpath("//span[@class='price J-p-"+sku+"']").nodes().isEmpty()){
			//商品没有下柜
			GoodsSource goodsSource=goodsSourceService.findBySkuAndSource(sku, Constant.PLATFORM_JD);
			
			GoodsDetail goodsDetail=new GoodsDetail();
			goodsDetail.setCategoryId(goodsSource.getCategoryId());
			goodsDetail.setBrandId(goodsSource.getBrandId());
			goodsDetail.setSku(sku);
			
			goodsDetail.setPrice(Double.parseDouble(html.xpath("//span[@class='price J-p-"+sku+"']/text()").toString()));
			goodsDetail.setGoodsName(html.xpath("//div[@class='sku-name']/text()").toString().trim());
			
			goodsDetail.setGoodCommint(goodCommint);
			//尝试获取国内店的好评率
//			if(!html.xpath("//*[@id='comment']/div[2]/div[1]/div[1]/div").nodes().isEmpty()){
//				goodsDetail.setGoodCommint(html.xpath("//*[@id='comment']/div[2]/div[1]/div[1]/div/text()").toString());
//			}else{
//				goodsDetail.setGoodCommint(html.xpath("//*[@id='i-comment']/div[1]/strong/text()").toString());
//			}
			
			goodsDetail.setOther(html.xpath("//*[@id='p-ad']/text()").toString().trim());
			
			goodsDetail.setGoodsUrl(url);
			goodsDetail.setSource(Constant.PLATFORM_JD);
			
			goodsDetailService.save(goodsDetail, goodsDetailService.count(goodsDetail)==0?true:false);
		}else{
			//商品已经下柜
			LOGGER.info(url+":商品已经下柜");
		}
		
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
