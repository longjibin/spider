package spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class MyProcessor implements PageProcessor {

	@Override
	public void process(Page page) {
		//*[@id="setf"]
		System.out.println(page.getHtml().xpath("//*[@id='u1']/a[1]/text()").toString());
	}

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(3000).setTimeOut(6000).addHeader("scriptName", "com.lgb.webspider.ecp.jd.loadallbrand.LoadAllBrandScript");
	}

}
