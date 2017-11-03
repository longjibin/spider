package com.lgb.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;

public class JSONReader {
	
	public static String readFileToJson(File jsonFile){
		String json="";
		try {
			json=FileUtils.readFileToString(jsonFile, "utf-8");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> readFileToMap(File jsonFile){
		Map<String, String> map=new HashMap<String, String>();
		try {
			String json=FileUtils.readFileToString(jsonFile, "utf-8");
			map=JSON.parseObject(json, Map.class);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
