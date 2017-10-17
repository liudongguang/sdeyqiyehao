package com.weixin.vo.in;

import com.weixin.constant.WeixinConstant;
import com.weixin.util.WX_Util;

public class WX_Vidio extends WXInBasicMsg {
	private String MediaId; // 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String ThumbMediaId;// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。

	public WX_Vidio() {
		super();
	}

	public WX_Vidio(String text) {
		super(text);
		this.MediaId = WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMMON_MEDIAID);
		this.ThumbMediaId = WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMMON_THUMBMEDIAID);
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	@Override
	public String toString() {
		return "WX_Vidio [MediaId=" + MediaId + ", ThumbMediaId=" + ThumbMediaId + "]";
	}
}
