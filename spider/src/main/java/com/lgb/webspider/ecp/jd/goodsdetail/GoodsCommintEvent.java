package com.lgb.webspider.ecp.jd.goodsdetail;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			WebElement comment = webDriver.findElement(By.xpath("//li[@data-anchor='#comment']"));
			comment.click();

			// 睡眠1s
			Thread.sleep(1000);
		} catch (NoSuchElementException e) {
			LOGGER.error(webDriver.getCurrentUrl(), e);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		try {
//			// 国内店
//			WebElement webElement = webDriver.findElement(By.xpath("//*[@id='comment']/div[2]/div[1]/div[1]/div"));
//			String goodCommint = webElement.getText();
//			dataMap.put("goodCommint", goodCommint);
//		} catch (NoSuchElementException e) {
//			try {
//				// 全球购
//				WebElement element = webDriver
//						.findElement(By.xpath("//div[@id='i-comment']/div[@class='rate']/strong"));
//				String goodCommint = element.getText();
//				dataMap.put("goodCommint", goodCommint);
//			} catch (NoSuchElementException exception) {
//				LOGGER.error(webDriver.getCurrentUrl(), exception);
//			}
//		}

		return dataMap;
	}

}
