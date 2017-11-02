package com.lgb.common.processor;

import org.apache.http.HttpHost;

import com.lgb.common.utils.ConfigUtil;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public abstract class CommonProcessor implements PageProcessor{
	
	@Override
	public Site getSite() {
		Site site=Site.me().setRetryTimes(ConfigUtil.getInteger("retrytimes")).setSleepTime(ConfigUtil.getInteger("sleeptime"));
		site.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
		.addHeader("Accept-Encoding", "gzip, deflate")
		.addHeader("Accept-Language", "zh-CN,zh;q=0.8")
		//.addHeader("Host", "www.techweb.com.cn")
		.addHeader("Proxy-Connection", "keep-alive")
		//.addHeader("Upgrade-Insecure-Requests", "1")
		.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36");
		//.addHeader("Cookie", "BAIDU_SSP_lcr=http://news.baidu.com/internet; _ga=GA1.3.1257418910.1508833824; _gid=GA1.3.1212925986.1508834182; Hm_lvt_731b1f35c925c5f9156c5d1509dca1d8=1508834182; Hm_lpvt_731b1f35c925c5f9156c5d1509dca1d8=1508834182")
		if(Boolean.parseBoolean(ConfigUtil.getString("enable"))){
			site.setHttpProxy(new HttpHost(ConfigUtil.getString("host"), ConfigUtil.getInteger("port")));
		}
		return site;
	}
	
	/**
	 * 运行爬虫
	 */
	public abstract void execute();

}
