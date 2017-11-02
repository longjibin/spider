package com.lgb.webspider.mobile.communication.mobile.processor.jd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.lgb.common.downloader.Script;

/**
 * 加载所有品牌的脚本
 * @author Administrator
 *
 * @date 2017年11月2日
 */
public class LoadAllBrandScript implements Script {

	@Override
	public void script(WebDriver webDriver) {
		// 找到加载所有的按钮
		WebElement loadAll = webDriver.findElement(By.xpath("//*[@id='J_selector']/div[2]/div/div[3]/a[1]"));
		loadAll.click();
		
		//睡眠1s
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
