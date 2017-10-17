package com.weixin.controller;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.weixin.constant.WeixinConstant;
import com.weixin.util.WX_Util;
import com.weixin.util.single.WXHuiHuanBizMsgCryptSingleton;
import com.weixin.vo.ServerSendMSG;
import com.weixin.vo.ValidateMSG;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/wxhuihua")
public class WXHuiHuaController {

	@RequestMapping(value = "/service", method = RequestMethod.GET)
	public void kf(HttpServletRequest request, HttpServletResponse response, ValidateMSG validateMSG)
			throws AesException, IOException {
		String vstr = WX_Util.validateHuiHua(validateMSG);
		WX_Util.print(vstr, response);
	}

	@RequestMapping(value = "/service", method = RequestMethod.POST)
	public void kf(ServerSendMSG ssdMSG,HttpServletRequest request, HttpServletResponse response) throws AesException, IOException {
		String message = WX_Util.getStrInputStream(request);
		System.out.println("wxhuihua..............");
		String encrypt = WX_Util.getXMLCDATA(message, WeixinConstant.IN_Encrypt);
		WXBizMsgCrypt wmc = WXHuiHuanBizMsgCryptSingleton.getWXBizMsgCryptInstance();
		message = wmc.decrypt(encrypt);// 解密
		System.out.println(message);
		String PackageId = WX_Util.getXMLCDATA(message, WeixinConstant.KF_PACKAGEID);
		System.out.println("PackageId:" + PackageId);
		WX_Util.print(PackageId, response);
	}
}
