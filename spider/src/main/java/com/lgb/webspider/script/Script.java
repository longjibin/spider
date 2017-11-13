package com.lgb.webspider.script;

import org.openqa.selenium.WebDriver;

import us.codecraft.webmagic.Page;

/**
 * 脚本接口
 * @author Administrator
 *
 * @date 2017年11月2日
 */
public interface Script {

	/**
	 * 在selenium下载下来的页面上执行脚本逻辑
	 */
	void script(WebDriver webDriver, Page page);
}
