package com.weixin.vo.wxhandlermsg;

import java.util.List;

public class WXServerIpsVo {
	private List<String> ip_list;

	public List<String> getIp_list() {
		return ip_list;
	}

	public void setIp_list(List<String> ip_list) {
		this.ip_list = ip_list;
	}

	@Override
	public String toString() {
		return "WXServerIps [ip_list=" + ip_list + ", getIp_list()=" + getIp_list() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
