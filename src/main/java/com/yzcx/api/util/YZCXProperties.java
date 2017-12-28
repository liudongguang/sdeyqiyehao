package com.yzcx.api.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

public class YZCXProperties {
	private static Properties properties = new Properties();
	private static String server;
	static {
		ClassPathResource cr = new ClassPathResource("yzcx.properties");
		try {

			properties.load(cr.getInputStream());
			server=properties.getProperty("server");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getPropertiesVal(String key) {
		return properties.getProperty(key);
	}
	public static String getRequestPropertiesVal(String key) {
		return server+properties.getProperty(key);
	}
	public static void main(String[] args) {
		System.out.println(YZCXProperties.getPropertiesVal("menzhen"));
	}
}
