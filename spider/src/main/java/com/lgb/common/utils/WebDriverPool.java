package com.lgb.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * @author code4crafter@gmail.com <br>
 *         Date: 13-7-26 <br>
 *         Time: 下午1:41 <br>
 */
public class WebDriverPool {
	public static final String DRIVER_CHROME = "ChromeDriver";

	public static final String DRIVER_PHANTOMJS = "PhantomJSDriver";

	private String driverName;

	private Logger logger = Logger.getLogger(getClass());

	private final static int DEFAULT_CAPACITY = 5;

	private final int capacity;

	private final static int STAT_RUNNING = 1;

	private final static int STAT_CLODED = 2;

	private AtomicInteger stat = new AtomicInteger(STAT_RUNNING);

	/**
	 * store webDrivers created
	 */
	private List<WebDriver> webDriverList = Collections.synchronizedList(new ArrayList<WebDriver>());

	/**
	 * store webDrivers available
	 */
	private BlockingDeque<WebDriver> innerQueue = new LinkedBlockingDeque<WebDriver>();

	public WebDriverPool(int capacity) {
		this(DRIVER_CHROME, capacity);
	}

	public WebDriverPool(String driverName, int capacity) {
		this.driverName = driverName;
		this.capacity = capacity;
	}

	public WebDriverPool() {
		this(DEFAULT_CAPACITY);
	}

	public WebDriver get() throws InterruptedException {
		checkRunning();
		WebDriver poll = innerQueue.poll();
		if (poll != null) {
			return poll;
		}
		if (webDriverList.size() < capacity) {
			synchronized (webDriverList) {
				if (webDriverList.size() < capacity) {
					WebDriver webDriver = null;
					switch (driverName) {
					case DRIVER_CHROME:
						webDriver = new ChromeDriver();
						break;
					case DRIVER_PHANTOMJS:
						webDriver = new PhantomJSDriver();
						break;
					default:
						webDriver = new ChromeDriver();
						break;
					}
					innerQueue.add(webDriver);
					webDriverList.add(webDriver);
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
		}
	}

}
