package com.weixin.vo.in;

import com.weixin.constant.WeixinConstant;
import com.weixin.util.WX_Util;

public class WX_Voice extends WXInBasicMsg {
	private String Format; // 语音格式，如amr，speex等
	private String MediaId; // 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String Recognition;//Recognition为语音识别结果，使用UTF8编码

	public WX_Voice() {
		super();
	}

	public WX_Voice(String text) {
		super(text);
		this.Format = WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMMON_FORMAT);
		this.MediaId = WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMMON_MEDIAID);
		this.Recognition = WX_Util.getXMLCDATA(text, WeixinConstant.IN_VOICE_RECOGNITION);
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	@Override
	public String toString() {
		return "WX_Voice [Format=" + Format + ", MediaId=" + MediaId + ", Recognition=" + Recognition + "]";
	}

}
