package com.lgb.webspider.ecp.jd.goodsdetail;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lgb.goods.entity.GoodsDetail;
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

		// 点击商品评价
		((JavascriptExecutor)webDriver).executeScript("arguments[0].click();", webDriver.findElement(By.xpath("//li[@data-anchor='#comment']")));
		
		try {
			new WebDriverWait(webDriver, 2).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='comment']/div[2]/div[1]/div[1]/div")));
			dataMap.put("goodsType", GoodsDetail.INLAND_SHOP);
		} catch (Exception e) {
			try {
				new WebDriverWait(webDriver, 2).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='i-comment']/div[1]/strong")));
				dataMap.put("goodsType", GoodsDetail.GLOBAL_SHOP);
			} catch (Exception e1) {
				LOGGER.error(webDriver.getCurrentUrl(), e1);
//				e1.printStackTrace();
			}
		}
		
//
//		new WebDriverWait(webDriver, 2).until(new ExpectedCondition<Boolean>() {
//			@Override
//			public Boolean apply(WebDriver driver) {
//				Boolean result = false;
//				try {
//					driver.findElements();
//					result = true;
//				} catch (Exception e) {
//				}
//				return result;
//			}
//		});

		// try {
		// // 国内店
		// WebElement webElement =
		// webDriver.findElement(By.xpath("//*[@id='comment']/div[2]/div[1]/div[1]/div"));
		// String goodCommint = webElement.getText();
		// dataMap.put("goodCommint", goodCommint);
		// } catch (NoSuchElementException e) {
		// try {
		// // 全球购
		// WebElement element = webDriver
		// .findElement(By.xpath("//div[@id='i-comment']/div[@class='rate']/strong"));
		// String goodCommint = element.getText();
		// dataMap.put("goodCommint", goodCommint);
		// } catch (NoSuchElementException exception) {
		// LOGGER.error(webDriver.getCurrentUrl(), exception);
		// }
		// }

		return dataMap;
	}

}
