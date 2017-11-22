package com.lgb.webspider.ecp.jd.loadallbrand;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.lgb.webspider.Script;
import com.lgb.webspider.downloader.PageLoader;

import us.codecraft.webmagic.Page;

/**
 * JD:加载分类下所有品牌的脚本
 * 
 * @author Administrator
 *
 * @date 2017年11月2日
 */
public class LoadAllScript implements Script {

	public Page script(PageLoader pageLoader) {
		// 找到加载所有的按钮
		WebElement loadAll = pageLoader.getWebDriver()
				.findElement(By.xpath("//*[@id='J_selector']/div[2]/div/div[3]/a[1]"));
		loadAll.click();

		// 睡眠1s
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 重新解析页面
		pageLoader.analysisHtml();
		return pageLoader.getPage();
	}

}
