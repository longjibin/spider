package com.lgb.common.utils;

import org.junit.Test;

public class UrlResolverTest {

	@Test
	public void testGetHost() {
		System.out.println(UrlResolver.getHost("http://list.jd.com/list.html?cat=9987,653,655"));
	}

	@Test
	public void testAnalysis() {
		System.out.println(UrlResolver.analysis("http://list.jd.com/list.html?cat=9987,653,655"));
		
	}

}
