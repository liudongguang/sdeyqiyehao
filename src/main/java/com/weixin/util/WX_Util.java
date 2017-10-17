package com.weixin.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ldg.api.util.JsonUtil;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.weixin.constant.WeixinConstant;
import com.weixin.util.single.WXBizMsgCryptSingleton;
import com.weixin.util.single.WXHuiHuanBizMsgCryptSingleton;
import com.weixin.vo.ValidateMSG;
import com.weixin.vo.send.InNews;
import com.weixin.vo.send.News;
import com.weixin.vo.wxhandlermsg.SuCai;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.MessageFormat;

public class WX_Util {
	/**
	 * 验证后台URL有效性
	 * 
	 * @param validateMSG
	 * @return
	 * @throws AesException
	 */
	public static String validate(ValidateMSG validateMSG) throws AesException {
		String signature = validateMSG.getMsg_signature();
		String timestamp = validateMSG.getTimestamp();
		String nonce = validateMSG.getNonce();
		String echostr = validateMSG.getEchostr();
		if (signature != null) {
			WXBizMsgCrypt wmc = WXBizMsgCryptSingleton.getWXBizMsgCryptInstance();
			String rtStr = wmc.VerifyURL(signature, timestamp, nonce, echostr);
			return rtStr;
		}
		return "";
	}

	/**
	 * 验证后台URL有效性
	 * 
	 * @param validateMSG
	 * @return
	 * @throws AesException
	 */
	public static String validateHuiHua(ValidateMSG validateMSG) throws AesException {
		String signature = validateMSG.getMsg_signature();
		String timestamp = validateMSG.getTimestamp();
		String nonce = validateMSG.getNonce();
		String echostr = validateMSG.getEchostr();
		if (signature != null) {
			WXBizMsgCrypt wmc = WXHuiHuanBizMsgCryptSingleton.getWXBizMsgCryptInstance();
			String rtStr = wmc.VerifyURL(signature, timestamp, nonce, echostr);
			return rtStr;
		}
		return "";
	}

	/**
	 * 从request得到报文
	 * 
	 * @param request
	 * @return
	 */
	public static String getStrInputStream(HttpServletRequest request) {
		InputStreamReader reader = null;
		BufferedReader breader = null;
		StringBuilder strb = new StringBuilder();
		try {
			reader = new InputStreamReader(request.getInputStream(), WeixinConstant.WX_UTF8);
			breader = new BufferedReader(reader);
			String str = null;
			while (null != (str = breader.readLine())) {
				strb.append(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != breader) {
				try {
					breader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return strb.toString();
	}

	public static String getXMLCDATA(String reqeustStr, String tagName) {
		if (reqeustStr.indexOf(tagName) == -1) {
			System.out.println("无此标签！" + tagName);
			return null;
		}
		boolean needCDATAFlag = true;
		if (WeixinConstant.NOTNEED_CDATA_1.equals(tagName) || WeixinConstant.NOTNEED_CDATA_2.equals(tagName)
				|| WeixinConstant.NOTNEED_CDATA_3.equals(tagName) || WeixinConstant.NOTNEED_CDATA_4.equals(tagName)
				|| WeixinConstant.NOTNEED_CDATA_5.equals(tagName) || WeixinConstant.NOTNEED_CDATA_6.equals(tagName)
				|| WeixinConstant.IN_COMM_AgentID.equals(tagName)
				|| WeixinConstant.IN_COMM_EVENT_LOCATION_Latitude.equals(tagName)
				|| WeixinConstant.IN_COMM_EVENT_LOCATION_Longitude.equals(tagName)
				|| WeixinConstant.IN_COMM_EVENT_LOCATION_Precision.equals(tagName)) {
			needCDATAFlag = false;// 不需要cdata
		}
		// 开始标签
		StringBuilder beginTageName = new StringBuilder("<");
		// 结束标签
		StringBuilder endTagName = new StringBuilder();
		beginTageName.append(tagName);
		if (needCDATAFlag) {
			beginTageName.append("><![CDATA[");
			endTagName.append("]]></");
		} else {
			beginTageName.append(">");
			endTagName.append("</");
		}
		endTagName.append(tagName).append(">");
		int beginIndex = reqeustStr.indexOf(beginTageName.toString()) + beginTageName.length();
		int endIndex = reqeustStr.indexOf(endTagName.toString());
		String rtStr = reqeustStr.substring(beginIndex, endIndex);
		return rtStr;
	}

	// out输出
	public static void print(String source, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		// response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(source);
		out.flush();
		out.close();
	}

	public static String getSuCai() throws JsonProcessingException {
		String url = MessageFormat.format(PropertiesUtil.weixinPropertiesVal("getSuCai"),
				Access_token.getAccessToken());
		HttpClientUtil htc = HttpClientUtil.getInstance();
		SuCai sc = new SuCai();
		sc.setType("image");
		sc.setOffset(0);
		sc.setCount(20);
		String rtStr = htc.sendHttpPostJson(url, JsonUtil.parseToJson(sc));
		System.out.println(rtStr);
		return rtStr;
	}

	public final static News getNews(Integer agentid, String toUser, String title, String description) {
		News n = new News();
		n.setAgentid(agentid);
		n.setTouser(toUser);
		InNews in1 = new InNews();
		in1.setTitle(title);
		in1.setDescription(description);
		n.getNews().getArticles().add(in1);
		return n;
	}
}
