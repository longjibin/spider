package com.lgb.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.Lists;

public class WebDriverPool {
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