package com.lgb.webspider.downloader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lgb.common.utils.ConfigUtil;
import com.lgb.common.utils.WebDriverManager;
import com.lgb.webspider.Script;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;

/**
 * SeleniumDownloader
 * 
 * @author Administrator
 *
 * @date 2017年11月2日
 */
@Component
@Scope("prototype")
public class SeleniumDownloader implements Downloader {

	private static final Logger LOGGER=Logger.getLogger(SeleniumDownloader.class);
	
	@Autowired
	private WebDriverManager webDriverManager;

	// private Logger logger = Logger.getLogger(getClass());

	/**
	 * 脚本
	 */
	private Script script;

	/**
	 * 驱动
	 */
	private String driverName;

	/**
	 * 环境变量
	 */
	static {
		System.getProperties().setProperty("phantomjs.binary.path", ConfigUtil.getString("driver.phantomjs.path"));
		System.getProperties().setProperty("webdriver.chrome.driver", ConfigUtil.getString("driver.chrome.path"));
	}

	public void addConfig(Script script, String driverName) {
		this.script = script;
		this.driverName = driverName;
	}

	@Override
	public Page download(Request request, Task task) {
		// 加载页面
		Page page=null;
		// 创建页面加载器
		PageLoader pageLoader = webDriverManager.buildPageLoader(request, task, script, driverName);
		try {
			// 添加加载器
			webDriverManager.addToPool(pageLoader);
			page = pageLoader.loadPage();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
//			e.printStackTrace();
		} finally {
			// 清理当前进程
			webDriverManager.destroy(pageLoader);
		}
		return page;
	}

	@Override
	public void setThread(int thread) {
		
	}

}
