package com.lgb.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 配置文件工具类
 * @author Administrator
 *
 * @date 2017年10月25日
 */
public class ConfigUtil {
	
	private static PropertiesConfiguration cfg = null;
	
	static{
		try {
			cfg=new PropertiesConfiguration("spider.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 加载properties配置文件
	 * @param key
	 */
	public static void loadProperties(String file){
		try {
			cfg=new PropertiesConfiguration(file);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, String> getKeyValueMap(){
		Map<String, String> map=new HashMap<String, String>();
		String key="";
		for (Iterator<String> iterator = cfg.getKeys(); iterator.hasNext();) {
			key=iterator.next();
			map.put(key, cfg.getString(key));
		}
		return map;
	}
	
	public static String getString(String key){
		return cfg.getString(key);
	}
	
	public static Integer getInteger(String key){
		return cfg.getInt(key);
	}
	
	
}
