package com.yzcx.api.service;

import com.ldg.api.vo.ResultMsg2;
import com.yzcx.api.vo.YZCXHandlerData;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.highchat.HighchartsConfig;
import com.yzcx.api.vo.highchat.HighchartsConfig_arr;
import com.yzcx.api.vo.highchat.pie.HighchartsConfig_pie;
import com.yzcx.api.vo.yzcxdisplay.Menzhen_Month_Yuyue;
import com.yzcx.api.vo.yzcxdisplay.QyglVo;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by LiuDongguang on 2017/11/3.
 */
public interface YZCXSearchService {

    QyglVo getQygl_ri() throws ParseException;

    HighchartsConfig_arr getQygl_riChart(int chartType) throws ParseException;

    HighchartsConfig getYuyue_riChart() throws ParseException;

    HighchartsConfig getJiBing_riChart() throws ParseException;

    QyglVo getQygl_month(YZCXSearchParam cparam) throws ParseException;

    Menzhen_Month_Yuyue getMenzhen_Month_Yuyue_month(YZCXSearchParam cparam) throws ParseException;

    HighchartsConfig getQygl_yueChart(YZCXSearchParam param) throws ParseException;

    HighchartsConfig getQygl_yueChart_tongqimenzhen(YZCXSearchParam param) throws ParseException;

    HighchartsConfig getQygl_yueChart_jibing(YZCXSearchParam param) throws ParseException;


    HighchartsConfig_pie getMenzhenYuyue_yueChart(Menzhen_Month_Yuyue menzhen_month_yuyue);
}
