package com.weixin.util.single;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.weixin.constant.WeixinConstant;
import com.weixin.util.PropertiesUtil;

public class WXINKFBizMsgCryptSingleton {
	private static String token = PropertiesUtil.weixinPropertiesVal(WeixinConstant.WXINKF_PARAM_TOKEN);
	private static String corpid = PropertiesUtil.weixinPropertiesVal(WeixinConstant.WX_CORPID);
	private static String encodingaeskey = PropertiesUtil.weixinPropertiesVal(WeixinConstant.WXINKF_ENCODINGAESKEY);
	private volatile static WXBizMsgCrypt wmc = null;

	private WXINKFBizMsgCryptSingleton() {

	}

	public static WXBizMsgCrypt getWXBizMsgCryptInstance() {
		if (wmc == null) {
			synchronized (WXINKFBizMsgCryptSingleton.class) {
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
