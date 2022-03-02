package com.epam.dbconnection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {
	private static Properties properties = new Properties();
	
	private PropertiesUtil() {
		
	}
	
	static {
		loadProperties();
	}
	
	public static String get (String key) {
		return properties.getProperty(key);
	}

	private static void loadProperties() {
		try {
			InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");
			properties.load(inputStream);
		} catch (IOException e){
			throw new RuntimeException(e);
		}
		
	}
	
}
