package com.lgb.webspider.downloader;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.collect.Maps;
import com.lgb.common.utils.ConfigUtil;
import com.lgb.common.utils.WebDriverPool;
import com.lgb.webspider.Event;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

/**
 * 使用Selenium调用浏览器进行渲染。目前仅支持chrome。<br>
 * 需要下载Selenium driver支持。<br>
 *
 * @author code4crafter@gmail.com <br>
 *         Date: 13-7-26 <br>
 *         Time: 下午1:37 <br>
 */
public class SeleniumDownloader implements Downloader, Closeable {

	private WebDriverPool webDriverPool;

	private Logger logger = Logger.getLogger(getClass());

	private int sleepTime = 0;

	private int poolSize = 1;

	private Event event;

	private String driver;

	/**
	 * Constructor without any filed. Construct PhantomJS browser
	 * 
	 * @author bob.li.0718@gmail.com
	 */
	public SeleniumDownloader() {
		System.getProperties().setProperty("phantomjs.binary.path", ConfigUtil.getString("driver.phantomjs.path"));
		System.getProperties().setProperty("webdriver.chrome.driver", ConfigUtil.getString("driver.chrome.path"));
	}

	/**
	 * set sleep time to wait until load success
	 *
	 * @param sleepTime
	 *            sleepTime
	 * @return this
	 */
	public SeleniumDownloader setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
		return this;
	}

	public SeleniumDownloader setEvent(Event event) {
		this.event = event;
		return this;
	}

	public SeleniumDownloader setDriver(String driver) {
		this.driver = driver;
		return this;
	}

	@Override
	public Page download(Request request, Task task) {
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
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriver.Options manage = webDriver.manage();
		Site site = task.getSite();
		if (site.getCookies() != null) {
			for (Map.Entry<String, String> cookieEntry : site.getCookies().entrySet()) {
				Cookie cookie = new Cookie(cookieEntry.getKey(), cookieEntry.getValue());
				manage.addCookie(cookie);
			}
		}

		/*
		 * TODO You can add mouse event or other processes
		 * 
		 * @author: bob.li.0718@gmail.com
		 */
		Page page = new Page();
		Map<String, Object> dataMap = Maps.newHashMap();
		if (event != null) {
			dataMap = event.action(webDriver);
			for (Entry<String, Object> entry : dataMap.entrySet()) {
				page.getResultItems().put(entry.getKey(), entry.getValue());
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
				webDriverPool = new WebDriverPool(poolSize,
						StringUtils.isNotBlank(driver) ? driver : WebDriverPool.DRIVER_CHROME);
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
