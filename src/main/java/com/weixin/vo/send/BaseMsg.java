package com.weixin.vo.send;

public class BaseMsg {
	private String touser; // 不必须，
	// 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送
	private String toparty;// 不必须，
	// 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
	private String totag;// 不必须，标签ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
	private String msgtype; // 消息类型
	private Integer agentid; // 企业应用的id
	private Integer safe;// 表示是否是保密消息，0表示否，1表示是，默认0

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getToparty() {
		return toparty;
	}

	public void setToparty(String toparty) {
		this.toparty = toparty;
	}

	public String getTotag() {
		return totag;
	}

	public void setTotag(String totag) {
		this.totag = totag;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public Integer getSafe() {
		return safe;
	}

	public void setSafe(Integer safe) {
		this.safe = safe;
	}

	@Override
	public String toString() {
		return "BaseMsg [touser=" + touser + ", toparty=" + toparty + ", totag=" + totag + ", msgtype=" + msgtype
				+ ", agentid=" + agentid + ", safe=" + safe + "]";
	}

}
