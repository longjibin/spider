package com.lgb.webspider.downloader;

import java.io.Serializable;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.lgb.webspider.Script;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.PlainText;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月20日
 */
public class PageLoader implements Serializable {

	public static final String DRIVER_CHROME = "chrome";

	public static final String DRIVER_PHANTOMJS = "phantomjs";

	private static final Logger LOGGER = Logger.getLogger(PageLoader.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 请求对象
	 */
	private Request request;
	
	/**
	 * task
	 */
	private Task task;

	/**
	 * 网页下载进程
	 */
	private WebDriver webDriver;

	/**
	 * 动作脚本
	 */
	private Script script;

	public PageLoader(Request request, Task task, WebDriver webDriver, Script script) {
		super();
		this.request = request;
		this.task = task;
		this.webDriver = webDriver;
		this.script = script;
	}

	public Page loadPage() throws Exception {
		Page page = new Page();

		LOGGER.info("downloading page " + request.getUrl());
		webDriver.get(request.getUrl());

		// 执行脚本
		if (script != null) {
			script.script(webDriver, page);
		}

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
		return page;
	}

	public Task getTask() {
		return task;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

}
