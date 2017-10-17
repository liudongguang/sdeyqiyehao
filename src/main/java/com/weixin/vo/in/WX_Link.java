package com.weixin.vo.in;

import com.weixin.constant.WeixinConstant;
import com.weixin.util.WX_Util;

public class WX_Link extends WXInBasicMsg {
	private String Title; // 消息标题
	private String Description;// 消息描述
	private String Url;// 消息链接

	public WX_Link() {
		super();
	}

	public WX_Link(String text) {
		super(text);
		this.Title = WX_Util.getXMLCDATA(text, WeixinConstant.IN_LINK_TITLE);
		this.Description = WX_Util.getXMLCDATA(text, WeixinConstant.IN_LINK_DESCRIPTION);
		this.Url = WX_Util.getXMLCDATA(text, WeixinConstant.IN_LINK_URL);
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	@Override
	public String toString() {
		return "WX_Link [Title=" + Title + ", Description=" + Description + ", Url=" + Url + "]";
	}
	
}
