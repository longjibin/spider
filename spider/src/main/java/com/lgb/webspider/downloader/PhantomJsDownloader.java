package com.lgb.webspider.downloader;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lgb.webspider.script.Script;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
@Component
@Scope("prototype")
public class PhantomJsDownloader implements Downloader {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private WebDriver webDriver;

	private Script script;

	public PhantomJsDownloader() {
		System.getProperties().setProperty("phantomjs.binary.path", "D:/phantomjs/phantomjs.exe");

	}

	public void setScript(Script script) {
		this.script = script;
	}

	@Override
	public Page download(Request request, Task task) {
		Page page = new Page();

		logger.info("downloading page " + request.getUrl());
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

	@Override
	public void setThread(int thread) {

	}

}
