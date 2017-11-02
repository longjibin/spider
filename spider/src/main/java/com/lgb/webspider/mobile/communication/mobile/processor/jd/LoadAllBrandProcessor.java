package com.lgb.webspider.mobile.communication.mobile.processor.jd;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgb.common.Constant;
import com.lgb.common.downloader.SeleniumDownloader;
import com.lgb.common.processor.CommonProcessor;
import com.lgb.common.utils.SpringContextHelper;
import com.lgb.common.utils.URLResolver;
import com.lgb.common.utils.UUIDUtil;
import com.lgb.goods.dao.GoodsBrandDAO;
import com.lgb.goods.entity.GoodsBrand;
import com.lgb.spider.dao.SpiderDAO;
import com.lgb.spider.entity.Spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Selectable;

@Component
public class LoadAllBrandProcessor extends CommonProcessor {

	@Autowired
	private SpiderDAO spiderDAO;
	
	@Autowired
	private GoodsBrandDAO goodsBrandDAO;
	
	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(3000);
	}

	@Override
	public void process(Page page) {
		Date date=new Date();
		GoodsBrand goodsBrand=null;
		GoodsBrand query=null;
		Selectable lis=page.getHtml().xpath("//ul[@id='brandsArea']/li");
		for (Selectable li : lis.nodes()) {
			goodsBrand=new GoodsBrand();
			String url=li.xpath("li/a/@href").toString();
			URLResolver.analysis(url);
			//设置电商品牌id brand-8557
			String sbId=li.xpath("li/@id").toString();
			goodsBrand.setSbId(sbId.substring(sbId.indexOf("-")+1));
			//设置品牌来源
			goodsBrand.setSource(Constant.PLATFORM_JD);
			//设置品牌名
			goodsBrand.setBrand(li.xpath("li/a/text()").toString());
			//设置商品列表url
			goodsBrand.setGoodsListUrl(li.xpath("li/a/@href").toString());
			//设置关联的分类
			goodsBrand.setCategoryId("3");
			
			query=new GoodsBrand();
			query.setSbId(goodsBrand.getSbId());
			Integer count=goodsBrandDAO.selectCount(query);
			if(count==0){
				/**
				 * 新增记录
				 */
				// 设置id
				goodsBrand.setId(UUIDUtil.getUUID());
				// 设置创建时间
				goodsBrand.setCreateDateTime(date);
				// 设置修改时间
				goodsBrand.setUpdateDateTime(date);
				goodsBrandDAO.insert(goodsBrand);
			}else{
				/**
				 * 修改记录
				 */
				goodsBrand.setUpdateDateTime(date);
				goodsBrandDAO.update(goodsBrand);
			}
			
		}
	}

	@Override
	public void execute() {
		SeleniumDownloader downloader=new SeleniumDownloader("D:\\chromedriver.exe", new LoadAllBrandScript());
		LoadAllBrandProcessor loadAllBrandProcessor = (LoadAllBrandProcessor) SpringContextHelper.getBean("loadAllBrandProcessor");
		// 获取爬虫配置对象
		Spider spider = spiderDAO.select("2");
		us.codecraft.webmagic.Spider.create(loadAllBrandProcessor).addUrl(spider.getUrl()).setDownloader(downloader)
				.thread(spider.getThreadNum()).run();
	}
	
}
