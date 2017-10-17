package com.weixin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ldg.api.util.JsonUtil;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.weixin.constant.WeixinConstant;
import com.weixin.util.Access_token;
import com.weixin.util.HttpClientUtil;
import com.weixin.util.PropertiesUtil;
import com.weixin.util.WX_Util;
import com.weixin.util.single.WXKFBizMsgCryptSingleton;
import com.weixin.vo.ServerSendMSG;
import com.weixin.vo.kf.TextSend;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

@Controller
@RequestMapping(value = "/wxkf")
public class WXKFController {

	@RequestMapping(value = "/service")
	public void kf(ServerSendMSG ssdMSG, HttpServletRequest request, HttpServletResponse response)
			throws AesException, IOException {
		System.out.println("kf..............");
		String message = WX_Util.getStrInputStream(request);
		String encrypt = WX_Util.getXMLCDATA(message, WeixinConstant.IN_Encrypt);
		WXBizMsgCrypt wmc = WXKFBizMsgCryptSingleton.getWXBizMsgCryptInstance();
		message = wmc.decrypt(encrypt);// 解密
		System.out.println(message);
		String PackageId = WX_Util.getXMLCDATA(message, WeixinConstant.KF_PACKAGEID);
		System.out.println("PackageId:" + PackageId);
		WX_Util.print(PackageId, response);
	}

	@RequestMapping(value = "/sendToKF")
	public @ResponseBody
    String sendToKF() throws JsonProcessingException {
		String sendToKF = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.WXKF_TOKF),
				Access_token.getAccessToken());
		String result = HttpClientUtil.getInstance().sendHttpPostJson(sendToKF.toString(), getSendTextJSon());
		return result;
	}

	private static String getSendTextJSon() throws JsonProcessingException {
		TextSend ts = new TextSend();
		ts.getText().setContent("发送给客服人员的话......");
		ts.getSender().setType("userid");
		ts.getSender().setId("zhaoyang");

		ts.getReceiver().setType("kf");
		ts.getReceiver().setId("lxm");
		return JsonUtil.parseToJson(ts).toString();
	}

	@RequestMapping(value = "/getKFAccessToken")
	public @ResponseBody
    String getKFAccessToken() {
		return Access_token.getAccessToken();
	}

	public static void main(String[] args) throws JsonProcessingException {
		System.out.println(getSendTextJSon());
	}
}
