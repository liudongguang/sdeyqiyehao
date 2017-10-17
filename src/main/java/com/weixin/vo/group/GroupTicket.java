package com.weixin.vo.group;

public class GroupTicket {
	private Integer errcode;
	private String errmsg;
	private String ticket;
	private String group_id;
	private Integer expires_in;

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

	@Override
	public String toString() {
		return "GroupTicket [errcode=" + errcode + ", errmsg=" + errmsg + ", ticket=" + ticket + ", group_id="
				+ group_id + ", expires_in=" + expires_in + "]";
	}

}
