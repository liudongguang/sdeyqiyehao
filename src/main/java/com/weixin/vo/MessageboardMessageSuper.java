package com.weixin.vo;

import com.weixin.api.po.MessageboardHuifu;
import com.weixin.api.po.MessageboardMessage;

import java.util.List;

public class MessageboardMessageSuper extends MessageboardMessage {
	private Integer pinglunCount;
   private List<MessageboardHuifu> pingLunByLY;

	public List<MessageboardHuifu> getPingLunByLY() {
		return pingLunByLY;
	}

	public void setPingLunByLY(List<MessageboardHuifu> pingLunByLY) {
		this.pingLunByLY = pingLunByLY;
	}

	public Integer getPinglunCount() {
		return pinglunCount;
	}

	public void setPinglunCount(Integer pinglunCount) {
		this.pinglunCount = pinglunCount;
	}

	@Override
	public String toString() {
		return super.toString()+"    MessageboardMessageSuper{" +
				"pinglunCount=" + pinglunCount +
				", pingLunByLY=" + pingLunByLY +
				'}';
	}
}
