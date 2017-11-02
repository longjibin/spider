package com.lgb.webspider.mobile.communication.mobile.processor.jd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.lgb.common.downloader.Script;

/**
 * JD:加载更多脚本
 * @author Administrator
 *
 * @date 2017年11月2日
 */
public class LoadMoreScript implements Script {

	@Override
	public void script(WebDriver webDriver) {
		// 找到滑动到的元素
		WebElement loadMore = webDriver.findElement(By.xpath("//div[@id='J_bottomPage']"));
		// 声明一个动作
		Actions action = new Actions(webDriver);

		// 滑动到指定元素
		action.moveToElement(loadMore).build().perform();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
