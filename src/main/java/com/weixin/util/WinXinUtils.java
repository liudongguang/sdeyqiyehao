package com.weixin.util;

import com.weixin.constant.WeixinConstant;

import java.text.MessageFormat;

public class WinXinUtils {
    public final static void sendJsonMsgToUser(String json){
        String sendMSGRUL = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.SENDMSG),
                Access_token.getAccessToken());
        String s = HttpClientUtil.getInstance().sendHttpPostJson(sendMSGRUL, json);
        System.out.println(s);
    }
}
