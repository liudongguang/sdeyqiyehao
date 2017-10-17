package com.weixin.vo;

public class ServerSendMSG {
	private String msg_signature;// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	private String timestamp;// 时间戳
	private String nonce;// 随机数

	public String getMsg_signature() {
		return msg_signature;
	}

	public void setMsg_signature(String msg_signature) {
		this.msg_signature = msg_signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	

	@Override
	public String toString() {
		return "ServerSendMSG [msg_signature=" + msg_signature + ", timestamp=" + timestamp + ", nonce=" + nonce + "]";
	}

}
