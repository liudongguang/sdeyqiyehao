package com.weixin.vo.in;

import com.weixin.constant.WeixinConstant;
import com.weixin.util.WX_Util;
import com.weixin.vo.send.Text;
import org.apache.commons.lang3.time.DateFormatUtils;

public class WXEventInBasicMsg {
	private String ToUserName; // 开发者微信号
	private String FromUserName; // 发送方帐号（一个OpenID）
	private Long CreateTime; // 消息创建时间
								// （整型）表示1970年1月1日0时0分0秒至消息创建时所间隔的秒数,注意是间隔的秒数,不是毫秒数!
	private String MsgType;// text
	private Integer AgentID; // 应用id，64位整型
	private String Event;

	private String CreateTimeFormat;

	public WXEventInBasicMsg() {
	}

	public WXEventInBasicMsg(String text) {
		super();
		this.ToUserName = WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMM_ToUserName);
		this.FromUserName = WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMM_FromUserName);
		this.CreateTime = Long.valueOf(WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMM_CreateTime));
		this.MsgType = WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMM_MsgType);
		this.AgentID = Integer.valueOf(WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMM_AgentID));
		this.Event = WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMM_Event);
		this.CreateTimeFormat = DateFormatUtils.format(CreateTime * 1000, WeixinConstant.YYYY_MM_DD_hh_mm_ss);
	}

	public Text getToUserMSG(String content) {
		Text tx = new Text();
		tx.setAgentid(getAgentID());
		tx.setTouser(getFromUserName());
		tx.getText().setContent(content);
		return tx;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public Integer getAgentID() {
		return AgentID;
	}

	public void setAgentID(Integer agentID) {
		AgentID = agentID;
	}

	public String getEvent() {
		return Event;
	}

	public String getCreateTimeFormat() {
		return CreateTimeFormat;
	}

	public void setCreateTimeFormat(String createTimeFormat) {
		CreateTimeFormat = createTimeFormat;
	}

	public void setEvent(String event) {
		Event = event;
	}

	@Override
	public String toString() {
		return "WXEventInBasicMsg [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime="
				+ CreateTime + ", MsgType=" + MsgType + ", AgentID=" + AgentID + ", Event=" + Event
				+ ", CreateTimeFormat=" + CreateTimeFormat + "]";
	}

}
