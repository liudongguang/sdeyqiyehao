package com.yzcx.impl.service.handler;

import com.ldg.api.util.JsonUtil;
import com.weixin.util.HttpClientUtil;
import com.yzcx.api.util.YZCXProperties;
import com.yzcx.api.vo.SSXXModle;
import com.yzcx.api.vo.YIJIModle;
import com.yzcx.api.vo.ZYXXModle;
import com.yzcx.api.vo.parsejson.Json_ShouShu;
import com.yzcx.api.vo.parsejson.Json_YiJi;
import com.yzcx.api.vo.parsejson.Json_ZhuYuanxx;

import java.util.Map;

public class YzcxHttpRequest {
    public final static ZYXXModle getZYXX(Map<String, String> requestparam){
        String zhuyuanurl = YZCXProperties.getRequestPropertiesVal("zyxx");//获取预约信息
        HttpClientUtil zhuyuanhtc = HttpClientUtil.getInstance();
        final String zhuyuanStr = zhuyuanhtc.sendHttpPost(zhuyuanurl, requestparam);
        Json_ZhuYuanxx zhuYuanxx= JsonUtil.getObjectByJSON(zhuyuanStr,Json_ZhuYuanxx.class);
        ZYXXModle data = zhuYuanxx.getData();
        return data;
    }

    public static YIJIModle getYIJI(Map<String, String> requestparam) {
        String zhuyuanurl = YZCXProperties.getRequestPropertiesVal("yijixx");//
        HttpClientUtil zhuyuanhtc = HttpClientUtil.getInstance();
        final String zhuyuanStr = zhuyuanhtc.sendHttpPost(zhuyuanurl, requestparam);
        Json_YiJi zhuYuanxx= JsonUtil.getObjectByJSON(zhuyuanStr,Json_YiJi.class);
        YIJIModle data = zhuYuanxx.getData();
        return data;
    }
    public static SSXXModle getShoushuxx(Map<String, String> requestparam) {
        String zhuyuanurl = YZCXProperties.getRequestPropertiesVal("shoushuxx");//
        HttpClientUtil zhuyuanhtc = HttpClientUtil.getInstance();
        final String shoushuStr = zhuyuanhtc.sendHttpPost(zhuyuanurl, requestparam);
        System.out.println(shoushuStr);
        Json_ShouShu shoushu= JsonUtil.getObjectByJSON(shoushuStr,Json_ShouShu.class);
        SSXXModle data = shoushu.getData();
        return data;
    }

}
