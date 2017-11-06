package com.yzcx.impl.service;

import com.ldg.api.util.JsonUtil;
import com.ldg.api.vo.ResultMsg2;
import com.ldg.api.vo.ResultMsg3;
import com.weixin.util.HttpClientUtil;
import com.yzcx.api.service.YZCXscheduleService;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXProperties;
import com.yzcx.api.vo.JBZDLiang;
import com.yzcx.api.vo.MenZhenLiang;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.YuYueLiang;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ResultMsg3<List<MenZhenLiang>> messageRs = JsonUtil.getObjectByJSON(s, ResultMsg3.class);
        System.out.println(messageRs.getData().size());
        String yuyueurl = YZCXProperties.getRequestPropertiesVal("yuyue");//获取预约信息
        HttpClientUtil yuyuehc = HttpClientUtil.getInstance();
        final String yuyue = yuyuehc.sendHttpPost(yuyueurl,requestparam);
        ResultMsg3<List<YuYueLiang>> yuyueRs = JsonUtil.getObjectByJSON(yuyue, ResultMsg3.class);
        System.out.println(yuyueRs.getData().size());
        String jbzdurl = YZCXProperties.getRequestPropertiesVal("jbzd");//获取疾病信息
        HttpClientUtil jbzdhc = HttpClientUtil.getInstance();
        final String jbzd = jbzdhc.sendHttpPost(jbzdurl,requestparam);
        ResultMsg3<List<YuYueLiang>> jbzdRs = JsonUtil.getObjectByJSON(jbzd, ResultMsg3.class);
        System.out.println(jbzdRs.getData().size());
    }
}
