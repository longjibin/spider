package com.lgb.webspider.jd.processor.common;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgb.common.Constant;
import com.lgb.common.processor.CommonProcessor;
import com.lgb.common.utils.ConfigUtil;
import com.lgb.common.utils.URLResolver;
import com.lgb.common.utils.UUIDUtil;
import com.lgb.goods.dao.GoodsBrandDAO;
import com.lgb.goods.entity.GoodsBrand;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 京东
 * 爬取指定分类下的所有品牌
 * @author Administrator
 *
 * @date 2017年11月3日
 */
@Component
public class LoadAllBrandProcessor extends CommonProcessor {
	
	private static final Logger LOGGER=Logger.getLogger(LoadAllBrandProcessor.class);
	
	@Autowired
	private GoodsBrandDAO goodsBrandDAO;
	
	/**
	 * 指定分类id
	 */
	private String categoryId;
	
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
			goodsBrand.setCategoryId(categoryId);
			
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
		/**
		 * 加载需要更新的分类下的品牌
		 */
		ConfigUtil.loadProperties("spider/config/jd/brands.properties");
		Map<String, String> map=ConfigUtil.getKeyValueMap();
		for (Entry<String, String> entry : map.entrySet()) {
			LOGGER.info("key:"+entry.getKey()+"\t value:"+entry.getValue());
//			categoryId=entry.getKey();
//			SeleniumDownloader downloader=new SeleniumDownloader("D:\\chromedriver.exe", new LoadAllBrandScript());
//			LoadAllBrandProcessor loadAllBrandProcessor = (LoadAllBrandProcessor) SpringContextHelper.getBean("loadAllBrandProcessor");
//			us.codecraft.webmagic.Spider.create(loadAllBrandProcessor).addUrl(entry.getValue()).setDownloader(downloader)
//					.thread(1).run();
		}
	}
	
}
