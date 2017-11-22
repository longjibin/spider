package com.lgb.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.stereotype.Component;

import com.lgb.webspider.Script;
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
	public PageLoader buildPageLoader(Request request, Task task, Script script, String driverName) {
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
		WebDriver webDriver = null;
		switch (driverName) {
		case PageLoader.DRIVER_CHROME:
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
			Map<String, Object> prefs = new HashMap<String, Object>();
	        prefs.put("profile.managed_default_content_settings.images", 2);
	        options.setExperimentalOption("prefs", prefs);
			webDriver = new ChromeDriver(options);
			break;
		case PageLoader.DRIVER_PHANTOMJS:
			webDriver = new PhantomJSDriver();
			break;
		default:
			webDriver = new ChromeDriver();
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
			while(currentSize<=0){
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
