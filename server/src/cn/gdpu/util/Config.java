package cn.gdpu.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	public Config() {
		super();
		propertiePath = getClass().getResource("/system.properties").getPath();
	}

	private static String propertiePath ;
	
	public final static String FEED_INDEX_FEEDS_COUNT = "FEED_INDEX_FEEDS_COUNT";
	public final static String FEED_SCHOOL_PEOPLE_COUNT = "FEED_SCHOOL_PEOPLE_COUNT";
	public final static String FEED_CLASSES_PEOPLE_COUNT = "FEED_CLASSES_PEOPLE_COUNT";
	
	/**
	 * 返回系统设置
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		Properties p = new Properties();
		try {
			p.load(new BufferedInputStream(new FileInputStream(new File(propertiePath))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(key);
	}
}
