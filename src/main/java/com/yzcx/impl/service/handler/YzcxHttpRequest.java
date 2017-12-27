package com.yzcx.impl.service.handler;

import com.ldg.api.util.JsonUtil;
import com.weixin.util.HttpClientUtil;
import com.yzcx.api.bo.PageParam;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXProperties;
import com.yzcx.api.vo.*;
import com.yzcx.api.vo.pageinfo.SSXXDisplayModle;
import com.yzcx.api.vo.pageinfo.YJHLXXDisplayModle;
import com.yzcx.api.vo.parsejson.*;

import java.util.HashMap;
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

    /**
     * 获取医技信息
     * @param requestparam
     * @return
     */
    public static YIJIModle getYIJI(Map<String, String> requestparam) {
        String zhuyuanurl = YZCXProperties.getRequestPropertiesVal("yijixx");//
        HttpClientUtil zhuyuanhtc = HttpClientUtil.getInstance();
        final String zhuyuanStr = zhuyuanhtc.sendHttpPost(zhuyuanurl, requestparam);
        Json_YiJi zhuYuanxx= JsonUtil.getObjectByJSON(zhuyuanStr,Json_YiJi.class);
        YIJIModle data = zhuYuanxx.getData();
        return data;
    }

    /**
     * 获取一级护理
     * @param requestparam
     * @return
     */
    public static YIJIHuLiModle getYIJIHuLi(Map<String, String> requestparam) {
        String zhuyuanurl = YZCXProperties.getRequestPropertiesVal("yijihuli");//
        HttpClientUtil zhuyuanhtc = HttpClientUtil.getInstance();
        final String zhuyuanStr = zhuyuanhtc.sendHttpPost(zhuyuanurl, requestparam);
        Json_Yijihuli zhuYuanxx= JsonUtil.getObjectByJSON(zhuyuanStr,Json_Yijihuli.class);
        YIJIHuLiModle data = zhuYuanxx.getData();
        return data;
    }

    public static SSXXModle getShoushuxx(Map<String, String> requestparam) {
        String zhuyuanurl = YZCXProperties.getRequestPropertiesVal("shoushuxx");//
        HttpClientUtil zhuyuanhtc = HttpClientUtil.getInstance();
        final String shoushuStr = zhuyuanhtc.sendHttpPost(zhuyuanurl, requestparam);
        Json_ShouShu shoushu= JsonUtil.getObjectByJSON(shoushuStr,Json_ShouShu.class);
        SSXXModle data = shoushu.getData();
        return data;
    }
    public static SSXXDisplayModle getShoushuxx_One(YZCXSearchParam param, PageParam pageParam,String ksName) {
        Map<String, String> requestparam=new HashMap<>();
        requestparam.put(YZCXConstant.remoteParam_starte, LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getStart()));
        requestparam.put(YZCXConstant.remoteParam_end, LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getEnd()));
        requestparam.put(YZCXConstant.remoteParam_pageNum, pageParam.getPageNum().toString());
        requestparam.put(YZCXConstant.remoteParam_pageSize, pageParam.getPageSize().toString());
        requestparam.put(YZCXConstant.remoteParam_ksName, ksName);
        String zhuyuanurl = YZCXProperties.getRequestPropertiesVal("getShouShuXX_One");//
        HttpClientUtil zhuyuanhtc = HttpClientUtil.getInstance();
        final String shoushuStr = zhuyuanhtc.sendHttpPost(zhuyuanurl, requestparam);
        SSXXDisplayModle data= JsonUtil.getObjectByJSON(shoushuStr,SSXXDisplayModle.class);
        return data;
    }

    public static HzxxModle getHuiZhenxx(Map<String, String> requestparam) {
        String zhuyuanurl = YZCXProperties.getRequestPropertiesVal("huizhenxx");//
        HttpClientUtil zhuyuanhtc = HttpClientUtil.getInstance();
        final String shoushuStr = zhuyuanhtc.sendHttpPost(zhuyuanurl, requestparam);
        Json_HuiZhen shoushu= JsonUtil.getObjectByJSON(shoushuStr,Json_HuiZhen.class);
        HzxxModle data = shoushu.getData();
        return data;
    }

    public static YJHLXXDisplayModle getYJHLPageInfo(YZCXSearchParam param, PageParam pageParam,String ksName) {
        Map<String, String> requestparam=new HashMap<>();
        requestparam.put(YZCXConstant.remoteParam_starte, LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getStart()));
        requestparam.put(YZCXConstant.remoteParam_end, LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getEnd()));
        requestparam.put(YZCXConstant.remoteParam_pageNum, pageParam.getPageNum().toString());
        requestparam.put(YZCXConstant.remoteParam_pageSize, pageParam.getPageSize().toString());
        requestparam.put(YZCXConstant.remoteParam_ksName, ksName);
        String zhuyuanurl = YZCXProperties.getRequestPropertiesVal("getYJHLPageInfo");//
        HttpClientUtil zhuyuanhtc = HttpClientUtil.getInstance();
        final String yijihuliStr = zhuyuanhtc.sendHttpPost(zhuyuanurl, requestparam);
        YJHLXXDisplayModle data= JsonUtil.getObjectByJSON(yijihuliStr,YJHLXXDisplayModle.class);
        return data;
    }

    public static ChuFangModle getChuFang(Map<String, String> requestparam) {
        String zhuyuanurl = YZCXProperties.getRequestPropertiesVal("getchufang");//
        HttpClientUtil zhuyuanhtc = HttpClientUtil.getInstance();
        final String shoushuStr = zhuyuanhtc.sendHttpPost(zhuyuanurl, requestparam);
        ChuFangModle shoushu= JsonUtil.getObjectByJSON(shoushuStr,ChuFangModle.class);
        return shoushu;
    }
}
