package com.weixin.vo.assets.qyappmanager;

public class GetqyApp {
	private Integer errcode;
	private String errmsg;
	private Integer agentid;// 企业应用id
	private String name;// 企业应用名称
	private String square_logo_url;// 企业应用方形头像
	private String round_logo_url;// 企业应用圆形头像
	private String description;// 企业应用详情
	private Allow_userinfos allow_userinfos;// 企业应用可见范围（人员），其中包括userid和关注状态state
	private Allow_partys allow_partys;// 企业应用可见范围（部门）
	private Allow_tags allow_tags;// 企业应用可见范围（标签）
	private Integer close;// 企业应用是否被禁用
	private String redirect_domain;// 企业应用可信域名
	private Integer report_location_flag;// 企业应用是否打开地理位置上报 0：不上报；1：进入会话上报；2：持续上报
	private Integer isreportuser;// 是否接收用户变更通知。0：不接收；1：接收
	private Integer isreportenter;// 是否上报用户进入应用事件。0：不接收；1：接收
	private String chat_extension_url;// 关联会话url
	private Integer type;// 应用类型。1：消息型；2：主页型

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

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSquare_logo_url() {
		return square_logo_url;
	}

	public void setSquare_logo_url(String square_logo_url) {
		this.square_logo_url = square_logo_url;
	}

	public String getRound_logo_url() {
		return round_logo_url;
	}

	public void setRound_logo_url(String round_logo_url) {
		this.round_logo_url = round_logo_url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Allow_userinfos getAllow_userinfos() {
		return allow_userinfos;
	}

	public void setAllow_userinfos(Allow_userinfos allow_userinfos) {
		this.allow_userinfos = allow_userinfos;
	}

	public Allow_partys getAllow_partys() {
		return allow_partys;
	}

	public void setAllow_partys(Allow_partys allow_partys) {
		this.allow_partys = allow_partys;
	}

	public Allow_tags getAllow_tags() {
		return allow_tags;
	}

	public void setAllow_tags(Allow_tags allow_tags) {
		this.allow_tags = allow_tags;
	}

	public Integer getClose() {
		return close;
	}

	public void setClose(Integer close) {
		this.close = close;
	}

	public String getRedirect_domain() {
		return redirect_domain;
	}

	public void setRedirect_domain(String redirect_domain) {
		this.redirect_domain = redirect_domain;
	}

	public Integer getReport_location_flag() {
		return report_location_flag;
	}

	public void setReport_location_flag(Integer report_location_flag) {
		this.report_location_flag = report_location_flag;
	}

	public Integer getIsreportuser() {
		return isreportuser;
	}

	public void setIsreportuser(Integer isreportuser) {
		this.isreportuser = isreportuser;
	}

	public Integer getIsreportenter() {
		return isreportenter;
	}

	public void setIsreportenter(Integer isreportenter) {
		this.isreportenter = isreportenter;
	}

	public String getChat_extension_url() {
		return chat_extension_url;
	}

	public void setChat_extension_url(String chat_extension_url) {
		this.chat_extension_url = chat_extension_url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "GetqyApp [errcode=" + errcode + ", errmsg=" + errmsg + ", agentid=" + agentid + ", name=" + name
				+ ", square_logo_url=" + square_logo_url + ", round_logo_url=" + round_logo_url + ", description="
				+ description + ", allow_userinfos=" + allow_userinfos + ", allow_partys=" + allow_partys
				+ ", allow_tags=" + allow_tags + ", close=" + close + ", redirect_domain=" + redirect_domain
				+ ", report_location_flag=" + report_location_flag + ", isreportuser=" + isreportuser
				+ ", isreportenter=" + isreportenter + ", chat_extension_url=" + chat_extension_url + ", type=" + type
				+ "]";
	}

}
