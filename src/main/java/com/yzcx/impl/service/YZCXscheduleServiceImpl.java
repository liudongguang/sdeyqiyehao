package com.yzcx.impl.service;

import com.ldg.api.util.JsonUtil;
import com.weixin.util.HttpClientUtil;
import com.yzcx.api.service.YZCXscheduleService;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXProperties;
import com.yzcx.api.vo.JBZDLiang;
import com.yzcx.api.vo.MenZhenLiang;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.YuYueLiang;
import com.yzcx.api.vo.parsejson.Json_Jbzd;
import com.yzcx.api.vo.parsejson.Json_Menzhen;
import com.yzcx.api.vo.parsejson.Json_Yuyue;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by LiuDongguang on 2017/11/3.
 */
@Service
public class YZCXscheduleServiceImpl implements YZCXscheduleService {
    @Override
    public void getmzinfo(YZCXSearchParam param) throws IOException {
        /////
        Map<String, String> requestparam = new HashMap();
        requestparam.put("starte", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getStart()));
        requestparam.put("end", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getEnd()));
        /////
        String menzhenurl = YZCXProperties.getRequestPropertiesVal("menzhen");//获取门诊信息
        HttpClientUtil hc = HttpClientUtil.getInstance();
        final String s = hc.sendHttpPost(menzhenurl, requestparam);
        Json_Menzhen menzhenRs = JsonUtil.getObjectByJSON(s, Json_Menzhen.class);
        //
        String yuyueurl = YZCXProperties.getRequestPropertiesVal("yuyue");//获取预约信息
        HttpClientUtil yuyuehc = HttpClientUtil.getInstance();
        final String yuyue = yuyuehc.sendHttpPost(yuyueurl,requestparam);
        Json_Yuyue yuyueRs = JsonUtil.getObjectByJSON(yuyue, Json_Yuyue.class);
        //
        String jbzdurl = YZCXProperties.getRequestPropertiesVal("jbzd");//获取疾病信息
        HttpClientUtil jbzdhc = HttpClientUtil.getInstance();
        final String jbzd = jbzdhc.sendHttpPost(jbzdurl,requestparam);
        Json_Jbzd jbzdRs = JsonUtil.getObjectByJSON(jbzd, Json_Jbzd.class);
        //////////////////////////////////////////////////////
        Map<String, Long> menzhenGroup = menzhenRs.getData().stream().map(item -> {
            item.setGhrqStr(LdgDateUtil.getYyyy_mm_ddString(item.getGhrq()));
            return item;
        }).collect(Collectors.groupingBy(MenZhenLiang::getGhrqStr, Collectors.counting()));
        System.out.println(menzhenGroup);
        Map<String, Long> yuyueGroup = yuyueRs.getData().stream().map(item -> {
            item.setYyrqStr(LdgDateUtil.getYyyy_mm_ddString(item.getYyrq()));
            return item;
        }).collect(Collectors.groupingBy(YuYueLiang::getYyrqStr, Collectors.counting()));
        System.out.println(yuyueGroup);
        Map<String, Long> jbzdGroup = jbzdRs.getData().stream().map(item -> {
            item.setRqStr(LdgDateUtil.getYyyy_mm_ddString(item.getRq()));
            return item;
        }).collect(Collectors.groupingBy(JBZDLiang::getRqStr, Collectors.counting()));
        System.out.println(jbzdGroup);
    }

    public static void main(String[] args) throws ParseException {
        List<MenZhenLiang>  menzhenHandler=new ArrayList<>();
        MenZhenLiang mz1=new MenZhenLiang();
        mz1.setGhrq(new Date());
        MenZhenLiang mz2=new MenZhenLiang();
        mz2.setGhrq(LdgDateUtil.getYyyy_mm_ddDate("2017-11-05"));
        MenZhenLiang mz3=new MenZhenLiang();
        mz3.setGhrq(LdgDateUtil.getYyyy_mm_ddDate("2017-11-05"));
        menzhenHandler.add(mz1);
        menzhenHandler.add(mz2);
        menzhenHandler.add(mz3);
        Map<String, Long> collect = menzhenHandler.stream().map(item -> {
            item.setGhrqStr(LdgDateUtil.getYyyy_mm_ddString(item.getGhrq()));
            return item;
        }).collect(Collectors.groupingBy(MenZhenLiang::getGhrqStr, Collectors.counting()));
        System.out.println(collect);

    }
}
