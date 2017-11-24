package com.lgb.webspider.ecp.jd.goodsdetail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.lgb.webspider.Event;
import com.lgb.webspider.downloader.PageLoader;

import us.codecraft.webmagic.Page;

/**
 * 获取商品好评度的脚本
 * @author Administrator
 *
 * @date 2017年11月22日
 */
public class GoodsCommintScript implements Event {

	@Override
	public Page script(PageLoader pageLoader) {
		// 找到商品评价按钮
		WebElement commintBtn = pageLoader.getWebDriver().findElement(By.xpath("//*[@id='detail']/div[1]/ul/li[5]"));
		commintBtn.click();
		// 睡眠1s
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//重新解析页面
		pageLoader.analysisHtml();
		Page page=pageLoader.getPage();
		String goodCommint=page.getHtml().xpath("//*[@id='comment']/div[2]/div[1]/div[1]/div/text()").toString();
		page.getResultItems().put("goodCommint", goodCommint.trim());
		return page;
	}

}
