package com.weixin.util.single;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.weixin.constant.WeixinConstant;
import com.weixin.util.PropertiesUtil;

public class WXBizMsgCryptSingleton {
	private static String token = PropertiesUtil.weixinPropertiesVal(WeixinConstant.WX_PARAM_TOKEN);
	private static String corpid = PropertiesUtil.weixinPropertiesVal(WeixinConstant.WX_CORPID);
	private static String encodingaeskey = PropertiesUtil.weixinPropertiesVal(WeixinConstant.WX_ENCODINGAESKEY);
	private volatile static WXBizMsgCrypt wmc = null;

	private WXBizMsgCryptSingleton() {

	}

	public static WXBizMsgCrypt getWXBizMsgCryptInstance() {
		if (wmc == null) {
			synchronized (WXBizMsgCryptSingleton.class) {
				if (wmc == null) {
					try {
						wmc = new WXBizMsgCrypt(token, encodingaeskey, corpid);
					} catch (AesException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return wmc;
	}
}
