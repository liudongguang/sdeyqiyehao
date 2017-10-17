package com.weixin.vo.in;

import com.weixin.constant.WeixinConstant;
import com.weixin.util.WX_Util;
import org.apache.commons.lang3.time.DateFormatUtils;

public class WXInBasicMsg {
	private String ToUserName; // 开发者微信号
	private String FromUserName; // 发送方帐号（一个OpenID）
	private Long CreateTime; // 消息创建时间 （整型）表示1970年1月1日0时0分0秒至消息创建时所间隔的秒数,注意是间隔的秒数,不是毫秒数!
	private String MsgType;// text
	private Long MsgId; // 消息id，64位整型
	private String CreateTimeFormat;

	public WXInBasicMsg() {
	}

	public WXInBasicMsg(String text) {
		super();
		this.ToUserName = WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMM_ToUserName);
		this.FromUserName = WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMM_FromUserName);
		this.CreateTime = Long.valueOf(WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMM_CreateTime));
		this.MsgType = WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMM_MsgType);
		this.MsgId = Long.valueOf(WX_Util.getXMLCDATA(text, WeixinConstant.IN_COMM_MsgId));
		this.CreateTimeFormat = DateFormatUtils.format(CreateTime*1000, WeixinConstant.YYYY_MM_DD_hh_mm_ss);
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

	public Long getMsgId() {
		return MsgId;
	}

	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}

	public String getCreateTimeFormat() {
		return CreateTimeFormat;
	}

	public void setCreateTimeFormat(String createTimeFormat) {
		CreateTimeFormat = createTimeFormat;
	}

	@Override
	public String toString() {
		return "WXInBasicMsg [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime="
				+ CreateTime + ", MsgType=" + MsgType + ", MsgId=" + MsgId + ", CreateTimeFormat=" + CreateTimeFormat
				+ "]";
	}

}
