package com.lgb.webspider.downloader;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lgb.common.utils.ConfigUtil;
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
public class SeleniumDownloader2 implements Downloader, Closeable {

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
	public SeleniumDownloader2() {
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
	public SeleniumDownloader2 setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
		return this;
	}

	public SeleniumDownloader2 setEvent(Event event) {
		this.event = event;
		return this;
	}

	public SeleniumDownloader2 setDriver(String driver) {
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
			dataMap = event.action(webDriver, page);
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

class WebDriverPool {
	private Logger logger = Logger.getLogger(getClass());

	private final int capacity;

	private final static int STAT_RUNNING = 1;

	private final static int STAT_CLODED = 2;

	private AtomicInteger stat = new AtomicInteger(STAT_RUNNING);

	private String driver;

	/*
	 * new fields for configuring phantomJS
	 */
	private WebDriver mDriver = null;

	public static final String DRIVER_FIREFOX = "firefox";
	public static final String DRIVER_CHROME = "chrome";
	public static final String DRIVER_PHANTOMJS = "phantomjs";

	public WebDriverPool(int capacity, String driver) {
		this.capacity = capacity;
		this.driver = driver;
	}

	/**
	 * Configure the GhostDriver, and initialize a WebDriver instance. This part
	 * of code comes from GhostDriver.
	 * https://github.com/detro/ghostdriver/tree/master/test/java/src/test/java/
	 * ghostdriver
	 * 
	 * @author bob.li.0718@gmail.com
	 * @throws IOException
	 */
	public void configure() throws IOException {
		DesiredCapabilities sCaps=null;
		switch (driver) {
		case DRIVER_CHROME:
			sCaps=DesiredCapabilities.chrome();
			sCaps.setJavascriptEnabled(true);
			sCaps.setCapability("takesScreenshot", false);

			/**
			 * 谷歌浏览器参数
			 */
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			// 禁用图片
			prefs.put("profile.managed_default_content_settings.images", 2);
			options.setExperimentalOption("prefs", prefs);
			sCaps.setCapability("chromeOptions", options);
			mDriver = new ChromeDriver(sCaps);
			break;
		case DRIVER_PHANTOMJS:
			sCaps=DesiredCapabilities.phantomjs();
			sCaps.setJavascriptEnabled(true);
			sCaps.setCapability("takesScreenshot", false);

			/**
			 * PHANTOMJS参数
			 */
			ArrayList<String> cliArgsCap = Lists.newArrayList();
			cliArgsCap.add("--web-security=false");
			cliArgsCap.add("--ssl-protocol=any");
			cliArgsCap.add("--ignore-ssl-errors=true");
			cliArgsCap.add("--load-images=no");
			cliArgsCap.add("--disk-cache=no");
			sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);

			// Control LogLevel for GhostDriver, via CLI arguments
			sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
					new String[] {
							"--logLevel=" + (StringUtils.isNotBlank(ConfigUtil.getString("phantomjs_driver_loglevel"))
									? ConfigUtil.getString("phantomjs_driver_loglevel") : "INFO") });
			mDriver = new PhantomJSDriver(sCaps);
			break;
		case DRIVER_FIREFOX:
			sCaps=DesiredCapabilities.firefox();
			sCaps.setJavascriptEnabled(true);
			sCaps.setCapability("takesScreenshot", false);
			
			mDriver = new FirefoxDriver(sCaps);
			break;
		}
	}

	/**
	 * store webDrivers created
	 */
	private List<WebDriver> webDriverList = Collections.synchronizedList(new ArrayList<WebDriver>());

	/**
	 * store webDrivers available
	 */
	private BlockingDeque<WebDriver> innerQueue = new LinkedBlockingDeque<WebDriver>();

	/**
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public WebDriver get() throws InterruptedException {
		checkRunning();
		WebDriver poll = innerQueue.poll();
		if (poll != null) {
			return poll;
		}
		if (webDriverList.size() < capacity) {
			synchronized (webDriverList) {
				if (webDriverList.size() < capacity) {

					// add new WebDriver instance into pool
					try {
						configure();
						innerQueue.add(mDriver);
						webDriverList.add(mDriver);
					} catch (IOException e) {
						e.printStackTrace();
					}

					// ChromeDriver e = new ChromeDriver();
					// WebDriver e = getWebDriver();
					// innerQueue.add(e);
					// webDriverList.add(e);
				}
			}

		}
		return innerQueue.take();
	}

	public void returnToPool(WebDriver webDriver) {
		checkRunning();
		innerQueue.add(webDriver);
	}

	protected void checkRunning() {
		if (!stat.compareAndSet(STAT_RUNNING, STAT_RUNNING)) {
			throw new IllegalStateException("Already closed!");
		}
	}

	public void closeAll() {
		boolean b = stat.compareAndSet(STAT_RUNNING, STAT_CLODED);
		if (!b) {
			throw new IllegalStateException("Already closed!");
		}
		for (WebDriver webDriver : webDriverList) {
			logger.info("Quit webDriver" + webDriver);
			webDriver.quit();
			webDriver = null;
		}
	}

}
