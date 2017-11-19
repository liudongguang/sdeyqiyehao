package com.ldg.api.sms;

public class SendMessage {
    private String userid;
    private String account;
    private String password;
    private String mobile;
    private String content;
    private String sendTime;
    private String action = "send";
    private String extno;

    public String getUrlParam(String requestURL) {
        StringBuilder sbd = new StringBuilder();
        sbd.append(requestURL).append("?action=send&userid=&account=").append(account).append("&password=").append(password).append("&mobile=").append(mobile).append("&content=").append(content).append("&sendTime=&extno=");
        return sbd.toString();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getExtno() {
        return extno;
    }

    public void setExtno(String extno) {
        this.extno = extno;
    }

    @Override
    public String toString() {
        return "SendMessage{" +
                "userid='" + userid + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", content='" + content + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", action='" + action + '\'' +
                ", extno='" + extno + '\'' +
                '}';
    }
}
