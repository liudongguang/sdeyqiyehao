package com.weixin.vo;

public class ValidateMSG {
	private String msg_signature;// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	private String timestamp;// 时间戳
	private String nonce;// 随机数
	private String echostr;// 随机字符串

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

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	@Override
	public String toString() {
		return "ValidateMSG [msg_signature=" + msg_signature + ", timestamp=" + timestamp + ", nonce=" + nonce
				+ ", echostr=" + echostr + "]";
	}

}
