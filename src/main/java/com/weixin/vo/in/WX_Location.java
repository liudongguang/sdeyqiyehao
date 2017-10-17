package com.weixin.vo.in;

import com.weixin.constant.WeixinConstant;
import com.weixin.util.WX_Util;

public class WX_Location extends WXInBasicMsg {
	private String Location_X; // 地理位置维度
	private String Location_Y;// 地理位置经度
	private String Scale;// 地图缩放大小
	private String Label;// 地理位置信息

	public WX_Location() {
		super();
	}

	public WX_Location(String text) {
		super(text);
		this.Location_X = WX_Util.getXMLCDATA(text, WeixinConstant.NOTNEED_CDATA_3);
		this.Location_Y = WX_Util.getXMLCDATA(text, WeixinConstant.NOTNEED_CDATA_4);
		this.Scale = WX_Util.getXMLCDATA(text, WeixinConstant.NOTNEED_CDATA_5);
		this.Label = WX_Util.getXMLCDATA(text, WeixinConstant.IN_LOCATION_LABEL);
	}

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	@Override
	public String toString() {
		return "WX_Location [Location_X=" + Location_X + ", Location_Y=" + Location_Y + ", Scale=" + Scale + ", Label="
				+ Label + "]";
	}

}
