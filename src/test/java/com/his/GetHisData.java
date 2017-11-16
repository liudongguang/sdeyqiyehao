package com.his;

import com.ldg.api.util.JsonUtil;
import com.weixin.util.HttpClientUtil;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXProperties;
import com.yzcx.api.vo.YuYueLiang;
import com.yzcx.api.vo.parsejson.Json_Yuyue;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GetHisData {
    @Test
    public void test1(){
        Date nowDate = new Date();
        Map<String, String> requestparam = new HashMap();
        LocalDateTime localDate = LdgDateUtil.parseDateToLocalDateTime(nowDate);
        localDate = localDate.minus(1, ChronoUnit.HOURS);
        String formatStr = localDate.format(LdgDateUtil.newDateFormat_yyyy_mm_dd_HH_00_00);

        requestparam.put("starte", formatStr);
        requestparam.put("end", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(nowDate));

        String yuyueurl = YZCXProperties.getRequestPropertiesVal("yuyue");//获取预约信息
        HttpClientUtil yuyuehc = HttpClientUtil.getInstance();
        final String yuyue = yuyuehc.sendHttpPost(yuyueurl, requestparam);
        Json_Yuyue yuyueRs = JsonUtil.getObjectByJSON(yuyue, Json_Yuyue.class);

        Map<String, Map<String, Long>> yuyuegroupByKS = yuyueRs.getData().stream().map(item -> {
            System.out.println(item.getYyrq());
            item.setYyrqStr(LdgDateUtil.getyyyy_mm_dd_hhString(item.getYyrq()));
            return item;
        }).collect(Collectors.groupingBy(YuYueLiang::getYyrqStr, Collectors.groupingBy(YuYueLiang::getKs, Collectors.counting())));
    }
}
