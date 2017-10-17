package com.weixin.vo.assets.qyappmanager;

public class Allow_userinfos_User {
	private String userid;
	private Integer status;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Allow_userinfos_User [userid=" + userid + ", status=" + status + "]";
	}

}
