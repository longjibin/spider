package com.lgb.webspider;

import java.util.Map;

import org.openqa.selenium.WebDriver;

/**
 * 事件
 * @author Administrator
 *
 * @date 2017年11月2日
 */
public interface Event {

	/**
	 * 在selenium下载下来的页面上执行脚本逻辑
	 * @param webDriver
	 * @return
	 */
	Map<String, Object> action(WebDriver webDriver);
}
