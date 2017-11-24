package com.lgb.webspider.ecp.jd.goodssource;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.google.common.collect.Maps;
import com.lgb.webspider.Event;

import us.codecraft.webmagic.Page;

/**
 * JD:加载更多脚本
 * 
 * @author Administrator
 *
 * @date 2017年11月2日
 */
public class LoadMoreEvent implements Event {

	@Override
	public Map<String, Object> action(WebDriver webDriver, Page page) {
		Map<String, Object> map=Maps.newHashMap();
		// 找到滑动到的元素
		WebElement loadMore;
		try {
			loadMore = webDriver.findElement(By.xpath("//*[@id='J_bottomPage']"));
			// 声明一个动作
			Actions action = new Actions(webDriver);
			// 滑动到指定元素
			action.moveToElement(loadMore).build().perform();
			
			Thread.sleep(1000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return map;
	}

}
