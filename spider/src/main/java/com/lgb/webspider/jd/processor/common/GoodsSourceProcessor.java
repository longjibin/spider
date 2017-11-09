package com.lgb.webspider.jd.processor.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgb.common.Constant;
import com.lgb.common.downloader.SeleniumDownloader;
import com.lgb.common.processor.CommonProcessor;
import com.lgb.common.utils.SpringContextHelper;
import com.lgb.common.utils.URLResolver;
import com.lgb.common.utils.UUIDUtil;
import com.lgb.goods.dao.GoodsBrandDAO;
import com.lgb.goods.dao.GoodsSourceDAO;
import com.lgb.goods.entity.GoodsBrand;
import com.lgb.goods.entity.GoodsSource;
import com.lgb.webspider.jd.script.common.LoadMoreScript;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 商品概要信息爬虫
 * @author Administrator
 *
 * @date 2017年11月2日
 */
@Component
public class GoodsSourceProcessor extends CommonProcessor {

	private static final Logger LOGGER = Logger.getLogger(GoodsSourceProcessor.class);
	
	@Autowired
	private GoodsBrandDAO goodsBrandDAO;
	
	@Autowired
	private GoodsSourceDAO goodsSourceDAO;
	
	private String brandId="b615952e-e344-4212-96cf-2edf69b54ac6";
	
	/**
	 * 页号列表
	 */
	private List<String> pageNos=new ArrayList<String>();
	
	/**
	 * 判断是否重复访问相同页号
	 * @param url
	 * @return true表示重复 false表示不重复
	 */
	private Boolean havePageNo(String url){
		/**
		 * 解析url获取page参数值
		 */
		URLResolver.analysis(url);
		String pageNo=URLResolver.getValue("page");
		if(StringUtils.isBlank(pageNo)){
			//默认第一页
			pageNo="1";
		}
		Boolean isHave=false;
		//如果页号<=0,则排除
		if(pageNo.contains("-")||pageNo.equals("0")){
			isHave=true;
		}else{
			for (String page : pageNos) {
				if(page.equals(pageNo)){
					isHave=true;
					break;
				}
			}
		}
		if(!isHave){
			pageNos.add(pageNo);
		}
		return isHave;
	}

	@Override
	public void process(Page page) {
		Date date=new Date();
		// 获取商品集合
		Selectable goodsList = page.getHtml().xpath("//*[@id='plist']/ul/li");
		List<GoodsSource> goodsSources=new ArrayList<GoodsSource>();
		GoodsSource goodsSource=null;
		for (Selectable goods : goodsList.nodes()) {
			/**
			 * 解析封面商品并保存到集合
			 */
			goodsSource=new GoodsSource();
			goodsSource.setBrandId(brandId);
			goodsSource.setSku(goods.xpath("li/div/@data-sku").toString());
			goodsSource.setUrl("https://item.jd.com/"+goodsSource.getSku()+".html");
			goodsSources.add(goodsSource);
			
			/**
			 * 解析关联商品并保存到集合
			 */
			Selectable items=goods.xpath("li/div/div[2]/div/ul/li");
			for (Selectable item : items.nodes()) {
				String sku=item.xpath("li/@ids").toString();
				if(StringUtils.isNotBlank(sku)){
					goodsSource=new GoodsSource();
					goodsSource.setBrandId(brandId);
					goodsSource.setSku(sku);
					goodsSource.setUrl("https://item.jd.com/"+sku+".html");
					goodsSources.add(goodsSource);
				}
			}
			
			/**
			 * 将集合中的数据保存到数据库
			 */
			for (GoodsSource goodsSourcePo : goodsSources) {
				Integer count=goodsSourceDAO.selectCount(goodsSourcePo);
				if(count==0){
					/**
					 * 新增
					 */
					// 设置id
					goodsSourcePo.setId(UUIDUtil.getUUID());
					// 设置创建时间
					goodsSourcePo.setCreateDateTime(date);
					// 设置修改时间
					goodsSourcePo.setUpdateDateTime(date);
					goodsSourceDAO.insert(goodsSourcePo);
				}else{
					/**
					 * 更新
					 */
					goodsSourcePo.setUpdateDateTime(date);
					goodsSourceDAO.update(goodsSourcePo);
				}
			}
		}
		
		Selectable pageUrls = page.getHtml().xpath("//*[@id='J_bottomPage']/span[1]/a");
		for (Selectable pageUrl : pageUrls.nodes()) {
			String url = pageUrl.xpath("a/@href").toString();
			if(!url.contains("undefined") && !havePageNo(url)){
				page.addTargetRequest(url);
			}
		}
		
	}

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(3000);
	}

	@Override
	public void execute() {
		/**
		 * 
		 */
		GoodsBrand query=new GoodsBrand();
		query.setSource(Constant.PLATFORM_JD);
		query.setCategoryId("3");
		List<GoodsBrand> goodsBrands=goodsBrandDAO.selectByModel(query);
		
		for (GoodsBrand goodsBrand : goodsBrands) {
			LOGGER.info(goodsBrand.getGoodsListUrl());
		}
		SeleniumDownloader downloader=new SeleniumDownloader("D:\\chromedriver.exe", new LoadMoreScript());
		GoodsSourceProcessor goodsSourceProcessor = (GoodsSourceProcessor) SpringContextHelper.getBean("goodsSourceProcessor");
		// 获取爬虫配置对象
		us.codecraft.webmagic.Spider.create(goodsSourceProcessor).addUrl("https://list.jd.com/list.html?cat=9987,653,655&ev=exbrand%5F14026&sort=sort%5Frank%5Fasc&trans=1&JL=3_品牌_Apple").setDownloader(downloader)
				.thread(1).run();
	}

}
