package com.lgb.webspider;

import com.lgb.webspider.downloader.PageLoader;

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
	 * @param pageLoader
	 * @return 
	 */
	Page script(PageLoader pageLoader);
}
