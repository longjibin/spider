package com.lgb.webspider.ecp.jd.goodssource;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Maps;
import com.lgb.webspider.Event;

/**
 * JD:加载更多脚本
 * 
 * @author Administrator
 *
 * @date 2017年11月2日
 */
public class LoadMoreEvent implements Event {

	@Override
	public Map<String, Object> action(WebDriver webDriver) {
		Map<String, Object> dataMap = Maps.newHashMap();

		try {
			new WebDriverWait(webDriver, 3)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='J_bottomPage']")));
		} catch (TimeoutException e) {
			// 无分页元素，默认只有一页
			return dataMap;
		}
		// 找到滑动到的元素
		WebElement loadMore = webDriver.findElement(By.xpath("//*[@id='J_bottomPage']"));
		JavascriptExecutor executor = (JavascriptExecutor) webDriver;
		executor.executeScript("arguments[0].scrollIntoView(true)", loadMore);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return dataMap;
	}

}
