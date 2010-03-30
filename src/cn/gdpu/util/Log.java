package cn.gdpu.util;

import org.apache.commons.logging.LogFactory;

public class Log {
	private static org.apache.commons.logging.Log logger;
	
	public static org.apache.commons.logging.Log init(Class c) {
		if (logger == null) {
			logger = LogFactory.getLog(c);
		}
		return logger;
	}
}
