package com.lgb.common.downloader;

import org.junit.Test;

import com.lgb.webspider.downloader.PhantomJSDownloader;
import com.lgb.webspider.ecp.jd.loadallbrand.LoadAllBrandProcessor;
import com.lgb.webspider.ecp.jd.loadallbrand.LoadAllBrandScript;

public class PhantomJSDownloaderTest {

	@Test
	public void test() {
		PhantomJSDownloader downloader = new PhantomJSDownloader("D:\\phantomjs\\phantomjs.exe", new LoadAllBrandScript());
		LoadAllBrandProcessor loadAllBrandProcessor = new LoadAllBrandProcessor();
		us.codecraft.webmagic.Spider.create(loadAllBrandProcessor)
				.addUrl("http://list.jd.com/list.html?cat=9987,653,655").setDownloader(downloader).thread(2).run();
	}

}
