package com.weixin.vo.in;

import com.weixin.constant.WeixinConstant;
import com.weixin.util.WX_Util;

public class WXEvent_LOCATION extends WXEventInBasicMsg {
	private String Latitude;
	private String Longitude;
	private String Precision;

	public WXEvent_LOCATION(String message) {
		super(message);
		this.Latitude = WX_Util.getXMLCDATA(message, WeixinConstant.IN_COMM_EVENT_LOCATION_Latitude);
		this.Longitude = WX_Util.getXMLCDATA(message, WeixinConstant.IN_COMM_EVENT_LOCATION_Longitude);
		this.Precision = WX_Util.getXMLCDATA(message, WeixinConstant.IN_COMM_EVENT_LOCATION_Precision);
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}

	@Override
	public String toString() {
		return super.toString() + "WXEvent_LOCATION [Latitude=" + Latitude + ", Longitude=" + Longitude + ", Precision="
				+ Precision + "]";
	}

}
