package com.weixin.util;

import com.weixin.constant.WeixinConstant;

import java.text.MessageFormat;

public class WinXinUtils {
    public final static String sendJsonMsgToUser(String json){
        String sendMSGRUL = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.SENDMSG),
                Access_token.getSendmsgaccessToken());
        String s = HttpClientUtil.getInstance().sendHttpPostJson(sendMSGRUL, json);
        return s;
    }
}
