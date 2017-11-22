package com.lgb.webspider.ecp.jd.goodssource;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.lgb.webspider.Script;
import com.lgb.webspider.downloader.PageLoader;

import us.codecraft.webmagic.Page;

/**
 * JD:加载更多脚本
 * 
 * @author Administrator
 *
 * @date 2017年11月2日
 */
public class LoadMoreScript implements Script {

	public Page script(PageLoader pageLoader) {
		if (StringUtils.isBlank(pageLoader.getPage().getHtml().xpath("//*[@id='J_bottomPage']").toString())) {
			// 页面不存在分页元素
			return pageLoader.getPage();
		}
		// 找到滑动到的元素
		WebElement loadMore = pageLoader.getWebDriver().findElement(By.xpath("//*[@id='J_bottomPage']"));
		// 声明一个动作
		Actions action = new Actions(pageLoader.getWebDriver());
		// 滑动到指定元素
		action.moveToElement(loadMore).build().perform();

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
