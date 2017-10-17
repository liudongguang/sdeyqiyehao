package com.weixin.vo.send;

public class Text extends BaseMsg {
	public Text() {
		this.setMsgtype("text");
	}

	private TextContent text = new TextContent();

	public TextContent getText() {
		return text;
	}

	public void setText(TextContent text) {
		this.text = text;
	}

}
