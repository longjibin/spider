package com.lgb.webspider.jd.processor.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgb.common.downloader.SeleniumDownloader;
import com.lgb.common.processor.CommonProcessor;
import com.lgb.common.utils.SpringContextHelper;
import com.lgb.goods.dao.GoodsSourceDAO;
import com.lgb.webspider.jd.script.common.LoadMoreScript;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

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
	private GoodsSourceDAO goodsSourceDAO;

	@Override
	public void process(Page page) {
//		// 获取手机列表
//		Selectable iphoneList = page.getHtml().xpath("//div[@id='J_goodsList']/ul/li");
//		// 商品源对象
//		GoodsSource goodsSource = null;
//		for (Selectable iphone : iphoneList.nodes()) {
//			/**
//			 * 爬取商品源信息
//			 */
//			goodsSource = new GoodsSource();
//			goodsSource.setBrandId(brandId);
//			goodsSource.setSku(iphone.xpath("li/@data-sku").toString());
//			goodsSource.setUrl(iphone.xpath("li/div/div[@class='p-name p-name-type-2']/a/@href").toString());
//
//			/**
//			 * 获取sku构造查询对象
//			 */
//			if (StringUtils.isNotBlank(goodsSource.getSku())) {
//				Integer count = goodsSourceDAO.selectCount(goodsSource.getSku());
//				if (count == 0) {
//					Date date = new Date();
//					/**
//					 * 新增
//					 */
//					// 设置id
//					phone.setId(UUIDUtil.getUUID());
//					// 设置创建时间
//					phone.setCreateDateTime(date);
//					// 设置修改时间
//					phone.setUpdateDateTime(date);
//					phoneDAO.insert(phone);
//				} else {
//					/**
//					 * 更新记录
//					 */
//					phone.setUpdateDateTime(new Date());
//					phoneDAO.update(phone);
//				}
//			} else {
//				LOGGER.info("无效的sku");
//			}
//		}

		// SEARCH.page(1, true)
//		List<String> urls = new ArrayList<String>();
//		Selectable pageUrls = page.getHtml().xpath("//div[@id='J_bottomPage']/span/a");
//		for (Selectable pageUrl : pageUrls.nodes()) {
//			String url = pageUrl.xpath("a/@onclick").toString();
//			if (StringUtils.isNotBlank(url)) {
//				String pageNo = url.substring(url.indexOf("(") + 1, url.indexOf(","));
//				System.out.println("pageNo:" + pageNo);
//				urls.add(
//						"https://search.jd.com/search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E6%89%8B%E6%9C%BA&cid2=653&cid3=655&ev=exbrand_Apple%5E&page="
//								+ pageNo);
//			}
//		}
//
//		page.addTargetRequests(urls);
	}

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(3000);
	}

	@Override
	public void execute() {
		SeleniumDownloader downloader=new SeleniumDownloader("D:\\chromedriver\\chromedriver.exe", new LoadMoreScript());
		GoodsSourceProcessor mobileProcessor = (GoodsSourceProcessor) SpringContextHelper.getBean("jdPhoneProcessor");
		// 获取爬虫配置对象
		us.codecraft.webmagic.Spider.create(mobileProcessor).addUrl("").setDownloader(downloader)
				.thread(1).run();
	}

}
