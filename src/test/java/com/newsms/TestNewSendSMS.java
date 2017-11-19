package com.newsms;

import com.ldg.api.sms.LdgMD5Util;
import com.ldg.api.sms.SendMessage;
import com.weixin.util.HttpClientUtil;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestNewSendSMS {
    @Test
    public void test1() throws Exception {
        String requestURL="http://123.58.1.111:81/smsJson.aspx";
        HttpClientUtil htc = HttpClientUtil.getInstance();
        SendMessage sendMessage=new SendMessage();
        sendMessage.setAccount("abc");
        sendMessage.setPassword(LdgMD5Util.MD5("abc01",32).toUpperCase());
        sendMessage.setMobile("17615827028");
        sendMessage.setContent("ddddd地方【邦尼云】");
        String url=sendMessage.getUrlParam(requestURL);
       // System.out.println(url);
        String s = htc.sendHttpsGet(url);
        System.out.println(s);
    }
    @Test
    public void test2() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String str="123";
        //确定计算方法
        MessageDigest md5= MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        System.out.println(newstr);
    }
    @Test
    public void test3() throws Exception {
        String str="abc123";
        System.out.println(LdgMD5Util.MD5(str,32));
    }
}
