package com.lgb.webspider.ecp.jd.loadallbrand;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.collect.Maps;
import com.lgb.webspider.Event;

/**
 * JD:加载分类下所有品牌的脚本
 * 
 * @author Administrator
 *
 * @date 2017年11月2日
 */
public class LoadAllEvent implements Event {

	@Override
	public Map<String, Object> action(WebDriver webDriver) {
		Map<String, Object> mapData = Maps.newHashMap();
		// 找到加载所有的按钮
		WebElement loadAll = webDriver.findElement(By.xpath("//*[@id='J_selector']/div[2]/div/div[3]/a[1]"));
		loadAll.click();

		// 睡眠1s
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return mapData;
	}

}
