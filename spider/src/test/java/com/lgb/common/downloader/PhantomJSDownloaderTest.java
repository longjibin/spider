package com.lgb.common.downloader;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomJSDownloaderTest {

	@Test
	public void test() {
		System.setProperty("phantomjs.binary.path","D:/phantomjs/phantomjs.exe");
	    
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {"--web-security=no", "--ignore-ssl-errors=yes"});
		WebDriver driver = new PhantomJSDriver(capabilities);
		
	    driver.get("http://list.jd.com/list.html?cat=9987,653,655");
	    System.out.println(driver.getPageSource());
	}

}
