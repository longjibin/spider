package com.lgb.webspider.ecp.jd.goodsdetail;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.lgb.webspider.Event;

/**
 * 获取商品好评度的脚本
 * 
 * @author Administrator
 *
 * @date 2017年11月22日
 */
public class GoodsCommintEvent implements Event {

	private static final Logger LOGGER = Logger.getLogger(GoodsCommintEvent.class);

	@Override
	public Map<String, Object> action(WebDriver webDriver) {
		boolean success = false;
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("arguments[0].click();", webDriver.findElement(By.xpath("//li[@data-anchor='#comment']")));
			// 睡眠1s
			Thread.sleep(2000);
		} catch (NoSuchElementException e) {
			LOGGER.info(webDriver.getCurrentUrl() + ":未找到商品评价元素");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			// 国内店
			WebElement webElement = webDriver.findElement(By.xpath("//div[@class='percent-con']/s"));
			String goodCommint = webElement.getText();
			dataMap.put("goodCommint", goodCommint);
			success = true;
		} catch (NoSuchElementException e) {
			LOGGER.info(webDriver.getCurrentUrl() + ":国内店未找到");
		}
		if (!success) {
			try {
				// 全球购
				WebElement webElement = webDriver.findElement(By.xpath("//div[@class='i-comment']/div[@class='rate']/strong"));
				String goodCommint = webElement.getText();
				dataMap.put("goodCommint", goodCommint);
			} catch (NoSuchElementException e) {
				LOGGER.info(webDriver.getCurrentUrl() + ":全球购未找到");
			}

		}

		return dataMap;
	}

}
