package com.lgb.webspider.ecp.jd.loadallbrand;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.lgb.webspider.script.Script;

import us.codecraft.webmagic.Page;

/**
 * 加载所有品牌的脚本
 * @author Administrator
 *
 * @date 2017年11月2日
 */
public class LoadAllBrandScript implements Script {

	@Override
	public void script(WebDriver webDriver, Page page) {
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
