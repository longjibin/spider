package spider;

import com.lgb.webspider.downloader.HttpClientDownloader;

import us.codecraft.webmagic.Spider;

public class Test {
	public static void main(String[] args) {
		HttpClientDownloader downloader = new HttpClientDownloader();
		downloader.setInterfaceUrl("http://localhost:8888/phantomjs/down");
		Spider.create(new MyProcessor()).setDownloader(downloader)
				.addUrl("http://list.jd.com/list.html?cat=9987,653,655&ev=exbrand%5F14026&sort=sort%5Frank%5Fasc&trans=1&JL=3_品牌_Apple")
				.run();
	}
}
