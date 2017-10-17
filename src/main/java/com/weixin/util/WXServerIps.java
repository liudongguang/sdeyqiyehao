package com.weixin.util;

import com.ldg.api.util.JsonUtil;
import com.weixin.constant.WeixinConstant;
import com.weixin.vo.wxhandlermsg.WXServerIpsVo;

import java.text.MessageFormat;
import java.util.HashMap;

public class WXServerIps {
	private static HashMap<String, String> ips = new HashMap<String, String>();

	public static void init_WXServerIps() {
		String url = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.GETWXSERVERIP),
				Access_token.getAccessToken());
		HttpClientUtil htc = HttpClientUtil.getInstance();
		String rtStr = htc.sendHttpGet(url.toString());
		WXServerIpsVo wv = JsonUtil.getObjectByJSON(rtStr, WXServerIpsVo.class);
		for (String ip : wv.getIp_list()) {
			String hip = ip.substring(0, ip.lastIndexOf("."));
			ips.put(hip, hip);
		}
	}

	public static boolean isWXRequest(String ip) {
		if (ip.lastIndexOf(".") != -1) {
			String hip = ip.substring(0, ip.lastIndexOf("."));
			if (ips.get(hip) != null) {
				return true;
			}
		}

		return false;
	}
}
