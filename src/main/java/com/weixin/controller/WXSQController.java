package com.weixin.controller;

import com.ldg.api.util.JsonUtil;
import com.ldg.api.vo.ResultMsg;
import com.weixin.constant.WeixinConstant;
import com.weixin.util.Access_token;
import com.weixin.util.HttpClientUtil;
import com.weixin.util.PropertiesUtil;
import com.weixin.vo.httprs.QY_MemberInfo;
import com.weixin.vo.sq.SQUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

@Controller
@RequestMapping(value = "/wxsq")
public class WXSQController {
	
	/**
	 * 获取授权连接
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jumpPageForOAuth")
	public String jumpPageForOAuth(String page) {
		StringBuilder redirectUri = new StringBuilder(PropertiesUtil.weixinPropertiesVal(WeixinConstant.SERVER));
		redirectUri.append(page);
		String appid = PropertiesUtil.weixinPropertiesVal(WeixinConstant.WX_CORPID);
		String state = "abc";
		String url = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.SQ_getCodeURL), appid,
				redirectUri, state);
		return "redirect:" + url;
	}

	/**
	 * 身份认证
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/getUserInfo")
	public @ResponseBody
    ResultMsg getUserInfo(HttpServletRequest request, String code) {
		ResultMsg msg = new ResultMsg();
		if (StringUtils.isBlank(code)) {
			msg.setErrorCode(500);
			msg.setErrorMsg("无code信息！");
			return msg;
		}
		String accessToken = Access_token.getAccessToken();
		String url = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.SQ_getUserInfoURL),
				accessToken, code);
		String result = HttpClientUtil.getInstance().sendHttpGet(url);
		// 根据code获取 userid
		SQUserInfo us = JsonUtil.getObjectByJSON(result, SQUserInfo.class);
		String userid = us.getUserId();
		if (userid != null) {
			String getudurl = MessageFormat.format(
					PropertiesUtil.weixinPropertiesVal(WeixinConstant.SQ_getDetailUserInfoURL), accessToken, userid);
			result = HttpClientUtil.getInstance().sendHttpGet(getudurl);
			QY_MemberInfo member = JsonUtil.getObjectByJSON(result, QY_MemberInfo.class);
			request.getSession().setAttribute(WeixinConstant.session_wxUSER, member);
			msg.setData(member);
			return msg;
		} else {
			String openId = us.getOpenId();// 不是内部人员打开会是这个值
			msg.setErrorCode(500);
			msg.setErrorMsg("非企业号人员！");
		}
		return msg;
	}

}
