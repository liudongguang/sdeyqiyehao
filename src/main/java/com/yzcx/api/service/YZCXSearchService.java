package com.yzcx.api.service;

import com.ldg.api.vo.ResultMsg2;
import com.yzcx.api.vo.YZCXHandlerData;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.highchat.HighchartsConfig;
import com.yzcx.api.vo.highchat.HighchartsConfig_arr;
import com.yzcx.api.vo.highchat.bar.HighchartsConfig_bar;
import com.yzcx.api.vo.highchat.column.HighchartsConfig_column;
import com.yzcx.api.vo.highchat.pie.HighchartsConfig_pie;
import com.yzcx.api.vo.yzcxdisplay.Menzhen_Month_Yuyue;
import com.yzcx.api.vo.yzcxdisplay.QyglVo;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by LiuDongguang on 2017/11/3.
 */
public interface YZCXSearchService {
    /**
     * 全院概览数据
     * @return
     * @throws ParseException
     */
    QyglVo getQygl_ri() throws ParseException;

    /**
     * 全院概览图表数据
     * @param chartType
     * @return
     * @throws ParseException
     */
    HighchartsConfig_arr getQygl_riChart(int chartType) throws ParseException;

    /**
     * 一天预约情况图
     * @return
     * @throws ParseException
     */
    HighchartsConfig getYuyue_riChart() throws ParseException;

    /**
     * 一天疾病情况图
     * @return
     * @throws ParseException
     */
    HighchartsConfig getJiBing_riChart() throws ParseException;

    /**
     * 获取一月的门诊，急诊数据
     * @param cparam
     * @return
     * @throws ParseException
     */
    QyglVo getQygl_month(YZCXSearchParam cparam) throws ParseException;

    /**
     *  获取一月的门诊，急诊图表数据
     * @param param
     * @return
     * @throws ParseException
     */
    HighchartsConfig getQygl_yueChart(YZCXSearchParam param) throws ParseException;
    /**
     * 获取一月的总门诊，预约数
     * @param cparam
     * @return
     * @throws ParseException
     */
    Menzhen_Month_Yuyue getMenzhen_Month_Yuyue_month(YZCXSearchParam cparam) throws ParseException;


    /**
     * 一月 的同期  门诊，急诊 图表数据
     * @param param
     * @return
     * @throws ParseException
     */
    HighchartsConfig getQygl_yueChart_tongqimenzhen(YZCXSearchParam param) throws ParseException;

    /**
     * 一月的疾病  图标信息
     * @param param
     * @return
     * @throws ParseException
     */
    HighchartsConfig getQygl_yueChart_jibing(YZCXSearchParam param) throws ParseException;

    /**
     * 一月的预约占门诊的占比图表
     * @param menzhen_month_yuyue
     * @return
     */
    HighchartsConfig_pie getMenzhenYuyueZhanbi_yueChart(Menzhen_Month_Yuyue menzhen_month_yuyue);

    /**
     * 一月的预约科室 图表
     * @param yzcxSearchParam
     * @return
     */
    HighchartsConfig getMenzhenYuyue_yueChart(YZCXSearchParam yzcxSearchParam);

    /**
     * 获取月份预约同期图表
     * @param yzcxSearchParam
     * @return
     */
    HighchartsConfig getTongqiyuyueChart(YZCXSearchParam yzcxSearchParam) throws ParseException;

    /**
     * 获取一年的门诊，急诊图表
     * @param param
     * @return
     */
    HighchartsConfig_column getMenzhen_year_chart(YZCXSearchParam param);

    /**
     * 获取同期  一年中的对比图
     * @param yzcxSearchParam
     * @return
     */
    HighchartsConfig_column getMenzhenTongqi_year_chart(YZCXSearchParam yzcxSearchParam) throws ParseException;
}
