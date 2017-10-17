package com.weixin.controller;

import com.baidumap.MapUtils;
import com.baidumap.vo.JingWeiToAddress;
import com.baidumap.vo.JingWeiToAddress_Result_pois;
import com.ldg.api.util.JsonUtil;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.weixin.constant.WeixinConstant;
import com.weixin.util.Access_token;
import com.weixin.util.HttpClientUtil;
import com.weixin.util.PropertiesUtil;
import com.weixin.util.WX_Util;
import com.weixin.util.single.WXBizMsgCryptSingleton;
import com.weixin.vo.ServerSendMSG;
import com.weixin.vo.ValidateMSG;
import com.weixin.vo.in.WXEvent_Enter;
import com.weixin.vo.in.WXEvent_LOCATION;
import com.weixin.vo.in.WX_Text;
import com.weixin.vo.send.InNews;
import com.weixin.vo.send.News;
import com.weixin.vo.send.Text;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.List;

@Controller
@RequestMapping(value = "/wx")
public class WXController {
	/**
	 * 接入验证
	 * 
	 * @param validateMSG
	 * @param response
	 * @throws IOException
	 * @throws AesException
	 */
	@RequestMapping(value = "/handler", method = RequestMethod.GET)
	public void validate(ValidateMSG validateMSG, HttpServletRequest request, HttpServletResponse response)
			throws IOException, AesException {
		String vstr = WX_Util.validate(validateMSG);
		WX_Util.print(vstr, response);
	}

	/**
	 * 接受微信服务端发过来的信息
	 * 
	 * @param ssdMSG
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws AesException
	 */
	@RequestMapping(value = "/handler", method = RequestMethod.POST)
	public void message(ServerSendMSG ssdMSG, HttpServletRequest request, HttpServletResponse response)
			throws IOException, AesException {
		String message = WX_Util.getStrInputStream(request);
		System.out.println(message);
		String encrypt = WX_Util.getXMLCDATA(message, WeixinConstant.IN_Encrypt);
		WXBizMsgCrypt wmc = WXBizMsgCryptSingleton.getWXBizMsgCryptInstance();
		message = wmc.decrypt(encrypt);// 解密
		String msgType = WX_Util.getXMLCDATA(message, WeixinConstant.IN_COMM_MsgType);
		String sendMSGRUL = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.SENDMSG),
				Access_token.getAccessToken());
		switch (msgType) {
		case WeixinConstant.WX_MESSAGETYPE_TEXT:
			// 接受信息
			WX_Text wxtext = new WX_Text(message);
			System.out.println(wxtext);
			if ("news".equals(wxtext.getContent())) {
				String jsonStr = JsonUtil.parseToJson(getNews());
				String result = HttpClientUtil.getInstance().sendHttpPostJson(sendMSGRUL, jsonStr);
				System.out.println(result);
				return;
			}
			if ("成员登陆".equals(wxtext.getContent())) {
				String redirectUri = "http://c0d1c61f.ngrok.io/guyueqiyehao/OAuthLoginTest.jsp";
				String appid = PropertiesUtil.weixinPropertiesVal(WeixinConstant.WX_CORPID);
				String state = "abc";
				String url = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.SQ_sq_loginpage),
						appid, URLEncoder.encode(redirectUri, WeixinConstant.WX_UTF8), state);
				/// 发送给企业号里的人
				String jsonStr = JsonUtil.parseToJson(getText(url));
				String result = HttpClientUtil.getInstance().sendHttpPostJson(sendMSGRUL, jsonStr);
				System.out.println(result);
				return;
			}
			HttpClientUtil.getInstance().sendHttpPostJson(sendMSGRUL, "");
			break;
		case WeixinConstant.WX_MESSAGETYPE_EVENT:// 各种事件
			String eventName = WX_Util.getXMLCDATA(message, WeixinConstant.IN_COMM_Event);
			switch (eventName) {
			case WeixinConstant.IN_COMM_Event_enter_agent:
				WXEvent_Enter enter_event = new WXEvent_Enter(message);
				System.out.println("EVENT enter_agent:  " + enter_event);
				// Text tx_enter = enter_event.getToUserMSG("进入应用...");
				// String jsonStr_enter = JsonUtil.parseToJson(tx_enter);
				HttpClientUtil.getInstance().sendHttpPostJson(sendMSGRUL, "");
				break;
			case WeixinConstant.IN_COMM_Event_LOCATION:
				WXEvent_LOCATION location = new WXEvent_LOCATION(message);
				System.out.println("EVENT LOCATION:  " + location);
				JingWeiToAddress jwaddress = MapUtils.getAddressNameByJingWeiDu(location.getLongitude(),
						location.getLatitude(), true);
				String address = jwaddress.getResult().getFormatted_address();
				List<JingWeiToAddress_Result_pois> list = jwaddress.getResult().getPois();
				StringBuilder sbd = new StringBuilder("您的位置：");
				sbd.append(address).append(WeixinConstant.HuanHang).append("您的附近有：").append(WeixinConstant.HuanHang);
				for (JingWeiToAddress_Result_pois poi : list) {
					String name = poi.getName();
					String addr = poi.getAddr();
					String type = poi.getPoiType();
					sbd.append("名称：").append(name).append(WeixinConstant.HuanHang).append(" 地址：").append(addr)
							.append(WeixinConstant.HuanHang).append(" 类型：").append(type).append(WeixinConstant.HuanHang)
							.append(".........................").append(WeixinConstant.HuanHang);
				}
				Text tx = location.getToUserMSG(sbd.toString());
				String jsonStr = JsonUtil.parseToJson(tx);
				HttpClientUtil.getInstance().sendHttpPostJson(sendMSGRUL, "");
				new Thread(new Runnable() {
					@Override
					public void run() {
						String result = HttpClientUtil.getInstance().sendHttpPostJson(sendMSGRUL, jsonStr);
						System.out.println(result);
					}
				}).start();
				break;
			default:
				System.out.println("未知事件..." + eventName);
				HttpClientUtil.getInstance().sendHttpPostJson(sendMSGRUL, "");
				break;
			}
			break;
		default:
			System.out.println("default   " + message);
			HttpClientUtil.getInstance().sendHttpPostJson(sendMSGRUL, "");
			break;
		}
	}

	private static News getNews() {
		News n = new News();
		n.setAgentid(1);
		n.setTouser("a200731589");
		InNews in1 = new InNews();
		in1.setTitle("测试标题");
		in1.setDescription("我是测试新闻呐");
		n.getNews().getArticles().add(in1);
		return n;
	}

	private static Text getText(String content) {
		Text tx = new Text();
		tx.setAgentid(1);
		tx.setTouser("a200731589");
		tx.getText().setContent(content);
		return tx;
	}

	@RequestMapping(value = "/AccessToken")
	public @ResponseBody
    String AccessToken() {
		return Access_token.getAccessToken();
	}

}
