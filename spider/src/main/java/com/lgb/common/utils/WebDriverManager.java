package com.lgb.common.utils;

import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM;
import static org.openqa.selenium.remote.CapabilityType.VERSION;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.lgb.webspider.Event;
import com.lgb.webspider.downloader.PageLoader;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

/**
 * 页面加载器的管理器
 * 
 * @author Administrator
 *
 * @date 2017年11月20日
 */
@Component
public class WebDriverManager {
	/**
	 * 进程池
	 */
	private static List<PageLoader> pageLoaderPool;

	/**
	 * 线程池容量
	 */
	private Integer poolSize = ConfigUtil.getInteger("thread.pool");

	/**
	 * 当前活跃进程数
	 */
	private Integer currentSize = 0;

	public WebDriverManager() {
		pageLoaderPool = new ArrayList<PageLoader>(this.poolSize);
	}

	/**
	 * 添加页面加载器到进程池
	 * 
	 * @param pageLoader
	 *            页面加载器
	 */
	public PageLoader buildPageLoader(Request request, Task task, Event script, String driverName) {
		synchronized (pageLoaderPool) {
			while (currentSize >= poolSize) {
				try {
					pageLoaderPool.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 活跃进程数加1
			currentSize++;
			pageLoaderPool.notifyAll();
		}
		/**
		 * 创建指定的页面驱动
		 */
		DesiredCapabilities sCaps = new DesiredCapabilities();
		sCaps.setJavascriptEnabled(true);
		sCaps.setCapability("takesScreenshot", false);

		WebDriver webDriver = null;
		switch (driverName) {
		case PageLoader.DRIVER_CHROME:
			sCaps.setCapability(BROWSER_NAME, BrowserType.CHROME);
			sCaps.setCapability(VERSION, "");
			sCaps.setCapability(PLATFORM, Platform.ANY);

			/**
			 * 谷歌浏览器参数
			 */
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			// 禁用图片
			prefs.put("profile.managed_default_content_settings.images", 2);
			options.setExperimentalOption("prefs", prefs);
//			sCaps.setCapability("chromeOptions", options);
			webDriver = new ChromeDriver(options);
			break;
		case PageLoader.DRIVER_PHANTOMJS:
			sCaps.setCapability(BROWSER_NAME, BrowserType.PHANTOMJS);
			sCaps.setCapability(VERSION, "");
			sCaps.setCapability(PLATFORM, Platform.ANY);

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
			webDriver = new PhantomJSDriver(sCaps);
			break;
		}
		PageLoader pageLoader = new PageLoader(request, task, webDriver, script);
		return pageLoader;
	}

	/**
	 * 添加加载器
	 * 
	 * @param pageLoader
	 */
	public void addToPool(PageLoader pageLoader) {
		synchronized (pageLoaderPool) {
			pageLoaderPool.add(pageLoader);
		}
	}

	/**
	 * 销毁加载器
	 * 
	 * @param pageLoader
	 */
	public void destroy(PageLoader pageLoader) {
		synchronized (pageLoaderPool) {
			while (currentSize <= 0) {
				try {
					pageLoaderPool.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 结束进程
			pageLoader.getWebDriver().quit();
			// 活跃进程数减1
			currentSize--;
			pageLoaderPool.notifyAll();
		}
	}
}
