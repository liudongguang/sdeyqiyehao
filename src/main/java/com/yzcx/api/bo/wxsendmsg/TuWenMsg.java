package com.yzcx.api.bo.wxsendmsg;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TuWenMsg {
    private String touser;
    private String toparty;
    private String totag;
    private String msgtype="news";
    private Integer agentid;
    private TuWenMsg_news news;

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

    public TuWenMsg_news getNews() {
        return news;
    }

    public void setNews(TuWenMsg_news news) {
        this.news = news;
    }
}
