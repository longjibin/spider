package com.lgb.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * url解析工具类
 * @author Administrator
 *
 * @date 2017年11月2日
 */
public class URLResolver {
	/**
	 * url中的key-value
	 */
	private static Map<String, String> keyValueMap = new HashMap<String, String>();

	/**
	 * host与参数的分隔符
	 */
	private static final String START = "?";

	/**
	 * 参数与参数之间的分割符
	 */
	private static final String SPLIT = "&";

	/**
	 * 锚点标记(锚点位于url最后部分)
	 */
	private static final String ANCHOR = "#";

	/**
	 * key与value的分隔符
	 */
	private static final String EQUALS = "=";

	public static void analysis(String url) {
		url = url.substring(url.indexOf(START) + 1);
		/**
		 * 去除锚点干扰
		 */
		if (url.contains(ANCHOR)) {
			url = url.substring(0, url.indexOf(ANCHOR));
		}
		
		while(url.contains(EQUALS)){
			int eqIndex=url.lastIndexOf(EQUALS);
			int spIndex=url.lastIndexOf(SPLIT);
			int replaceIndex=-1;
			if(eqIndex<spIndex){
				//替换特殊字符
				url=url.substring(0, spIndex)+"*"+url.substring(spIndex+1);
				//记录替换的下标
				replaceIndex=spIndex;
				//重新定位分隔符
				spIndex=url.lastIndexOf(SPLIT);
			}
			if(replaceIndex!=-1){
				//替换回来
				url=url.substring(0, replaceIndex)+"&"+url.substring(replaceIndex+1);
			}
			String keyValue=url.substring(spIndex+1);
			keyValueMap.put(keyValue.substring(0, keyValue.indexOf(EQUALS)),
					keyValue.substring(keyValue.indexOf(EQUALS) + 1));
			if(spIndex!=-1){
				url=url.substring(0, spIndex);
			}else{
				url="";
			}
		}
		
	}

	public static String getValue(String key) {
		return keyValueMap.get(key);
	}
	
//	public static void main(String[] args) {
//		String url="/list.html?cat=9987,653,655&sort=sort%5Frank%5Fasc&trans=1&ev=exbrand_55524&JL=3_品牌_B&O PLAY";
//		URLResolver.analysis(url);
//		System.out.println(URLResolver.getValue("JL"));
//	}

}