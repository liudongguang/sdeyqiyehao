package com.weixin.controller;

import com.weixin.constant.WeixinConstant;
import com.weixin.util.Access_token;
import com.weixin.util.PropertiesUtil;
import com.weixin.util.jssdk.Sign;
import com.weixin.vo.group.GroupTicket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping(value = "/jssdk")
public class WXJSSDKController {
	/**
	 * Ajax请求
	 * 
	 * @param page
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getJsapiTicket")
	public @ResponseBody
    Map<String, String> getJsapiTicket(String url) throws IOException {
		String ticket = Access_token.getJsapi_ticket();
		Map<String, String> ret = Sign.sign(ticket, url);
		ret.put("appId", PropertiesUtil.weixinPropertiesVal(WeixinConstant.WX_CORPID));
		return ret;
	}

	/**
	 * Ajax请求
	 * 
	 * @param page
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getGroupTicket")
	public @ResponseBody
    Map<String, String> getGroupTicket(String url) throws IOException {
		GroupTicket groupTicket = Access_token.getGroup();
		Map<String, String> ret = Sign.signGroupTicket(groupTicket.getTicket(), url);
		ret.put("groupId", groupTicket.getGroup_id());
		return ret;
	}

	/**
	 * http://weixin.chinamobiz.com/guyueqiyehao/jssdk/getJsapiTicketAndJumpPage?page=jssdkdv2.jsp
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getJsapiTicketAndJumpPage")
	public String getJsapiTicketAndJumpPage(HttpServletRequest request, String page) throws IOException {
		/// 签名地址
		StringBuilder jssdkUri = new StringBuilder(PropertiesUtil.weixinPropertiesVal(WeixinConstant.SERVER));
		jssdkUri.append(page);
		//// 跳转页面
		StringBuilder redirectUri = new StringBuilder("/");
		redirectUri.append(page);
		//
		String ticket = Access_token.getJsapi_ticket();
		Map<String, String> ret = Sign.sign(ticket, jssdkUri.toString());
		ret.put("appId", PropertiesUtil.weixinPropertiesVal(WeixinConstant.WX_CORPID));
		request.getSession().setAttribute("sign", ret);
		return "redirect:" + jssdkUri.toString();
	}

	/**
	 * http://weixin.chinamobiz.com/guyueqiyehao/jssdk/getJsapiTicketAndJumpPage2?page=jssdkdv2.jsp
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getJsapiTicketAndJumpPage2")
	public String getJsapiTicketAndJumpPage2(HttpServletRequest request, String page) throws IOException {
		/// 签名地址
		StringBuilder jssdkUri = new StringBuilder(PropertiesUtil.weixinPropertiesVal(WeixinConstant.SERVER));
		jssdkUri.append("jssdk").append("/getJsapiTicketAndJumpPage2?page=").append(page);
		String ticket = Access_token.getJsapi_ticket();
		Map<String, String> ret = Sign.sign(ticket, jssdkUri.toString());
		ret.put("appId", PropertiesUtil.weixinPropertiesVal(WeixinConstant.WX_CORPID));
		request.getSession().setAttribute("sign", ret);
		//// 跳转页面
		StringBuilder redirectUri = new StringBuilder("/");
		redirectUri.append(page);
		//
		return redirectUri.toString();
	}
}
