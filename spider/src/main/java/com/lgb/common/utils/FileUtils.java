package com.lgb.common.utils;

import java.io.File;
import java.io.IOException;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
public class FileUtils {

	public static String readFileToString(String path) {
		return readFileToString(path, null);
	}

	public static String readFileToString(String path, String encoding) {
		String content = null;
		try {
			content = org.apache.commons.io.FileUtils
					.readFileToString(new File(FileUtils.class.getClassLoader().getResource(path).getPath()), encoding);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
}
