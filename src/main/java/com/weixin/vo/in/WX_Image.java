package com.weixin.vo.in;

import com.weixin.constant.WeixinConstant;
import com.weixin.util.WX_Util;

public class WX_Image extends WXInBasicMsg {
	private String PicUrl; // 图片链接（由系统生成）
	private String MediaId; // 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。

	public WX_Image() {
		super();
	}

	public WX_Image(String text) {
		super(text);
		this.PicUrl = WX_Util.getXMLCDATA(text, WeixinConstant.IN_IMAGE_PICURL);
		this.MediaId = WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMMON_MEDIAID);
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	@Override
	public String toString() {
		return "WX_Image [PicUrl=" + PicUrl + ", MediaId=" + MediaId + "]";
	}

}
