package com.weixin.vo.kf;

public class TextSend extends KfBase {
	private TextConent text;

	public TextSend() {
		super("text");
		text=new TextConent();
	}

	public TextConent getText() {
		return text;
	}

	public void setText(TextConent text) {
		this.text = text;
	}

}
