package com.newsms;

import com.ldg.api.sms.LdgMD5Util;
import com.ldg.api.sms.SendMessage;
import com.weixin.util.HttpClientUtil;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestNewSendSMS {
    @Test
    public void test1() throws Exception {
        sendMSG("18364104688");
        sendMSG("18678881931");
        sendMSG("15335311320");
        sendMSG("17615827028");
    }
    private static void sendMSG(String phone) throws Exception {
        String requestURL="http://123.58.1.111:81/smsJson.aspx";
        HttpClientUtil htc = HttpClientUtil.getInstance();
        SendMessage sendMessage=new SendMessage();
        sendMessage.setAccount("sdyy01");
        sendMessage.setPassword(LdgMD5Util.MD5("sdyy01",32).toUpperCase());
        sendMessage.setMobile(phone);
        sendMessage.setContent(URLEncoder.encode("您好，请回复数字评价本次住院情况：1、满意 2、不满意。可在数字后附上说明或致电95020120。祝您早日康复。【山大二院】", "UTF-8"));
        String url=sendMessage.getUrlParam(requestURL);
        // System.out.println(url);
        String s = htc.sendHttpGet(url);
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
