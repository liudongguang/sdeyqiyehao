package com.weixin.util.single;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.weixin.constant.WeixinConstant;
import com.weixin.util.PropertiesUtil;

public class WXHuiHuanBizMsgCryptSingleton {
	private static String token = PropertiesUtil.weixinPropertiesVal(WeixinConstant.WXHUIHUA_PARAM_TOKEN);
	private static String corpid = PropertiesUtil.weixinPropertiesVal(WeixinConstant.WX_CORPID);
	private static String encodingaeskey = PropertiesUtil.weixinPropertiesVal(WeixinConstant.WXHUIHUA_ENCODINGAESKEY);
	private volatile static WXBizMsgCrypt wmc = null;

	private WXHuiHuanBizMsgCryptSingleton() {

	}

	public static WXBizMsgCrypt getWXBizMsgCryptInstance() {
		if (wmc == null) {
			synchronized (WXHuiHuanBizMsgCryptSingleton.class) {
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
