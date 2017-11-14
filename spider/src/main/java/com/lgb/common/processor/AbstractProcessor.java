package com.lgb.common.processor;

import org.apache.http.HttpHost;

import com.lgb.common.utils.ConfigUtil;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
public abstract class AbstractProcessor implements PageProcessor {

	private static final String PROXY_ENABLE = "proxy.enable";
	private static final String PROXY_HOST = "proxy.host";
	private static final String PROXY_PORT = "proxy.port";

	@Override
	public Site getSite() {
		Site site = Site.me().setRetryTimes(ConfigUtil.getInteger("retrytimes"))
				.setSleepTime(ConfigUtil.getInteger("sleeptime"));
		site.addHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
				.addHeader("Accept-Encoding", "gzip, deflate").addHeader("Accept-Language", "zh-CN,zh;q=0.8")
				.addHeader("Proxy-Connection", "keep-alive").addHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36");

		// 加载配置文件
		ConfigUtil.loadProperties("spider.properties");
		if (Boolean.parseBoolean(ConfigUtil.getString(PROXY_ENABLE))) {
			site.setHttpProxy(new HttpHost(ConfigUtil.getString(PROXY_HOST), ConfigUtil.getInteger(PROXY_PORT)));
		}
		return site;
	}

}
