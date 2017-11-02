package com.lgb.phone.processor.taobao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class HuabanProcessorTest {

	@Test
	public void test() {
		System.getProperties().setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(
				"https://search.jd.com/search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E6%89%8B%E6%9C%BA&cid2=653&cid3=655&ev=exbrand_Apple%5E&uc=0#J_searchWrap");

		
		// 声明一个动作
		Actions action = new Actions(webDriver);
		// 找到滑动到的元素
		WebElement loadMore = webDriver.findElement(By.xpath("//div[@id='J_scroll_loading']"));
		while(loadMore!=null){
			// 滑动到指定元素
			action.moveToElement(loadMore).build().perform();
			loadMore = webDriver.findElement(By.xpath("//div[@id='J_scroll_loading']"));
			/**
			 * 等待2秒加载更多
			 */
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		WebElement webElement = webDriver.findElement(By.xpath("/html"));

		byte[] buffer = new byte[1024 * 2];
		ByteArrayInputStream inputStream = null;
		FileOutputStream outputStream = null;
		int index = -1;
		try {
			inputStream = new ByteArrayInputStream(webElement.getAttribute("outerHTML").getBytes());
			outputStream = new FileOutputStream(new File("D:\\text.html"));
			while ((index = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, index);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// System.out.println(webElement.getAttribute("outerHTML"));
		webDriver.close();
	}

}
