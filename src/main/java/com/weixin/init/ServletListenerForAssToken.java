package com.weixin.init;

import com.weixin.util.Access_token;
import com.weixin.util.WXServerIps;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletListenerForAssToken implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Access_token.init_Access_token();
		// 微信服务ip
		WXServerIps.init_WXServerIps();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
