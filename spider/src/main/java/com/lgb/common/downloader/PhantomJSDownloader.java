package com.lgb.common.downloader;

import java.io.Closeable;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

public class PhantomJSDownloader implements Downloader, Closeable {
	
	private WebDriver webDriver;
	
	private Logger logger = Logger.getLogger(getClass());
	
	private Script script;
	
	public PhantomJSDownloader(String phantomJSDriverPath, Script script) {
		System.getProperties().setProperty("phantomjs.binary.path", phantomJSDriverPath);
		this.script = script;
	}

	@Override
	public Page download(Request request, Task task) {
		Page page=new Page();
		if(webDriver==null){
			webDriver=new PhantomJSDriver();
		}
		logger.info("downloading page " + request.getUrl());
		webDriver.get(request.getUrl());
		
		// 执行脚本
		if(script!=null){
			script.script(webDriver, page);
		}
		
		WebElement webElement = webDriver.findElement(By.xpath("/html"));
		String content = webElement.getAttribute("outerHTML");
		page.setRawText(content);
		page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
		return page;
	}

	@Override
	public void setThread(int threadNum) {
		
	}

	@Override
	public void close() throws IOException {
		if(webDriver!=null){
			webDriver.quit();
		}
	}

}
