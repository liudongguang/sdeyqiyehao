package com.weixin.util;

import com.ldg.api.util.JsonUtil;
import com.weixin.constant.WeixinConstant;
import com.weixin.vo.group.GroupTicket;
import com.weixin.vo.jssdk.JSSDKTicket;
import com.weixin.vo.wxhandlermsg.AsscessToken;

import java.text.MessageFormat;

public class Access_token {
	private static String accessToken = null;

	private static String jsapi_ticket = null;

	private static GroupTicket group = null;

	public static void init_Access_token() {
		String getTokenAccessURL = PropertiesUtil.weixinPropertiesVal(WeixinConstant.GETACCESS_TOKENURL);
		String corpid = PropertiesUtil.weixinPropertiesVal(WeixinConstant.WX_CORPID);
		String corpsecret = PropertiesUtil.weixinPropertiesVal(WeixinConstant.WX_secret);
		String url = MessageFormat.format(getTokenAccessURL, corpid, corpsecret);
		HttpClientUtil htc = HttpClientUtil.getInstance();
		String rtStr = htc.sendHttpGet(url);
		AsscessToken ass = JsonUtil.getObjectByJSON(rtStr, AsscessToken.class);
		accessToken = ass.getAccess_token();

		// jssdk 签名获取
		String jsapi_ticket_url = MessageFormat
				.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.jssdk_get_jsapi_ticketURL), accessToken);
		HttpClientUtil jsapi_ticket_htc = HttpClientUtil.getInstance();
		String jsapi_ticket_rtStr = jsapi_ticket_htc.sendHttpGet(jsapi_ticket_url);
		JSSDKTicket ticket = JsonUtil.getObjectByJSON(jsapi_ticket_rtStr, JSSDKTicket.class);
		jsapi_ticket = ticket.getTicket();
		// 企业号管理组权限验证
		String group_ticket_url = MessageFormat
				.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.getGroup_ticketURL), accessToken);
		HttpClientUtil group_ticket_htc = HttpClientUtil.getInstance();
		String group_ticket_rtStr = group_ticket_htc.sendHttpGet(group_ticket_url);
		group = JsonUtil.getObjectByJSON(group_ticket_rtStr, GroupTicket.class);
	}

	public static String getJsapi_ticket() {
		return jsapi_ticket;
	}

	public static String getAccessToken() {
		return accessToken;
	}

	public static GroupTicket getGroup() {
		return group;
	}

}
