package com.lgb.webspider.downloader;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import com.lgb.common.utils.WebDriverPool;
import com.lgb.webspider.Script;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

/**
 * SeleniumDownloader
 * 
 * @author Administrator
 *
 * @date 2017年11月2日
 */
@Component
public class SeleniumDownloader2 implements Downloader, Closeable {

	private volatile WebDriverPool webDriverPool;

	private Logger logger = Logger.getLogger(getClass());

	private int poolSize = 1;

	private Script script;

	private String driverName;
	
	public SeleniumDownloader2(String systemProperty, String chromeDriverPath, String driverName, Script script) {
		System.getProperties().setProperty(systemProperty, chromeDriverPath);
		// System.getProperties().setProperty("phantomjs.binary.path",
		// chromeDriverPath);
		// System.getProperties().setProperty("webdriver.chrome.driver",
		// chromeDriverPath);
		this.driverName = driverName;
		this.script = script;
	}

	@Override
	public Page download(Request request, Task task) {
		Page page = new Page();
		checkInit();
		WebDriver webDriver;
		try {
			webDriver = webDriverPool.get();
		} catch (InterruptedException e) {
			logger.warn("interrupted", e);
			return null;
		}
		logger.info("downloading page " + request.getUrl());
		webDriver.get(request.getUrl());

		// 执行脚本
//		if (script != null) {
//			script.script(webDriver, page);
//		}

		WebDriver.Options manage = webDriver.manage();
		Site site = task.getSite();
		if (site.getCookies() != null) {
			for (Map.Entry<String, String> cookieEntry : site.getCookies().entrySet()) {
				Cookie cookie = new Cookie(cookieEntry.getKey(), cookieEntry.getValue());
				manage.addCookie(cookie);
			}
		}
		WebElement webElement = webDriver.findElement(By.xpath("/html"));
		String content = webElement.getAttribute("outerHTML");

		page.setRawText(content);
		page.setUrl(new PlainText(request.getUrl()));
		page.setRequest(request);
		webDriverPool.returnToPool(webDriver);
		return page;
	}

	private void checkInit() {
		if (webDriverPool == null) {
			synchronized (this) {
				webDriverPool = new WebDriverPool(driverName, poolSize);
			}
		}
	}

	@Override
	public void setThread(int thread) {
		this.poolSize = thread;
	}

	@Override
	public void close() throws IOException {
		webDriverPool.closeAll();
	}

}
