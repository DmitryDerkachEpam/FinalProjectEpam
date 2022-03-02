package com.epam.pagemanager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PageManager {

	private static Properties properties = new Properties();
	
	private PageManager() {
		
	}
	
	static {
		loadPageProperties();
	}
	
	public static String getValue (String key) {
		return properties.getProperty(key);
	}
	
	private static void loadPageProperties() {
		try {
			InputStream inputStream = PageManager.class.getClassLoader().getResourceAsStream("pagename.properties");
			properties.load(inputStream);
		} catch (IOException e){
			throw new RuntimeException(e);
		}
	}
}
