package com.weixin.vo.sq;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SQUserInfo {
	@JsonProperty(value = "UserId")
	private String UserId;
	@JsonProperty(value = "OpenId")
	private String OpenId;
	@JsonProperty(value = "DeviceId")
	private String DeviceId;

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getOpenId() {
		return OpenId;
	}

	public void setOpenId(String openId) {
		OpenId = openId;
	}

	public String getDeviceId() {
		return DeviceId;
	}

	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}

	@Override
	public String toString() {
		return "SQUserInfo [UserId=" + UserId + ", OpenId=" + OpenId + ", DeviceId=" + DeviceId + "]";
	}

}
