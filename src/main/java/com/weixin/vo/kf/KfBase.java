package com.weixin.vo.kf;

public class KfBase {
	private Sender sender;
	private Receiver receiver;
	private String msgtype;

	public KfBase(String msgtype) {
		sender = new Sender();
		receiver = new Receiver();
		this.msgtype = msgtype;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

}
