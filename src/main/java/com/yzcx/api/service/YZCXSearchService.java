package com.yzcx.api.service;

import com.ldg.api.vo.ResultMsg2;
import com.yzcx.api.vo.YZCXHandlerData;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.highchat.HighchartsConfig;
import com.yzcx.api.vo.highchat.HighchartsConfig_arr;
import com.yzcx.api.vo.yzcxdisplay.QyglVo;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by LiuDongguang on 2017/11/3.
 */
public interface YZCXSearchService {

    QyglVo getQygl_ri() throws ParseException;

    HighchartsConfig_arr getQygl_riChart() throws ParseException;
}
