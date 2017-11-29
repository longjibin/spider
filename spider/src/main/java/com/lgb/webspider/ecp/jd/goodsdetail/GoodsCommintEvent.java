package com.lgb.webspider.ecp.jd.goodsdetail;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lgb.common.utils.SpringContextHelper;
import com.lgb.goods.service.GoodsSourceService;
import com.lgb.webspider.Event;

/**
 * 获取商品好评度的脚本
 * 
 * @author Administrator
 *
 * @date 2017年11月22日
 */
public class GoodsCommintEvent implements Event {

	private GoodsSourceService goodsSourceService = (GoodsSourceService) SpringContextHelper
			.getBean("goodsSourceServiceImpl");

	private static final Logger LOGGER = Logger.getLogger(GoodsCommintEvent.class);

	@Override
	public Map<String, Object> action(WebDriver webDriver) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		// 点击商品评价
		try {
			new WebDriverWait(webDriver, 3)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@data-anchor='#comment']")));
		} catch (TimeoutException e) {
			goodsSourceService.removeByUrl(webDriver.getCurrentUrl());
			LOGGER.error(webDriver.getCurrentUrl() + ":该商品可能失效，已被移除");
			return dataMap;
		}
		WebElement element = webDriver.findElement(By.xpath("//li[@data-anchor='#comment']"));
		JavascriptExecutor executor = (JavascriptExecutor) webDriver;
		executor.executeScript("arguments[0].scrollIntoView(true)", element);
		executor.executeScript("arguments[0].click();", element);
		
		WebElement goodCommint;
		try {
			goodCommint=new WebDriverWait(webDriver, 2).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='comment']/div[2]/div[1]/div[1]/div")));
		} catch (TimeoutException e) {
			try {
				goodCommint=new WebDriverWait(webDriver, 2).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='i-comment']/div[1]/strong")));
			} catch (TimeoutException e1) {
				LOGGER.error(webDriver.getCurrentUrl() + ":获取该商品的好评率失败");
				dataMap.put("goodCommint", "0%");
				return dataMap;
			}
		}
		if(goodCommint!=null){
			dataMap.put("goodCommint", goodCommint.getText());
		}

		return dataMap;
	}

}
