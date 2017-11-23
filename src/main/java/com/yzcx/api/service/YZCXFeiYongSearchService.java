package com.yzcx.api.service;

import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.highchat.HighchartsConfig;
import com.yzcx.api.vo.highchat.HighchartsConfig_arr;
import com.yzcx.api.vo.highchat.bar.HighchartsConfig_bar;
import com.yzcx.api.vo.highchat.column.HighchartsConfig_column;
import com.yzcx.api.vo.highchat.pie.HighchartsConfig_pie;
import com.yzcx.api.vo.yzcxdisplay.FeiYongHuiZong;
import com.yzcx.api.vo.yzcxdisplay.Menzhen_Month_Yuyue;
import com.yzcx.api.vo.yzcxdisplay.QyglVo;

import java.text.ParseException;
import java.util.Map;

public interface YZCXFeiYongSearchService {
    /**
     * 获取index费用
     * @param param
     * @return
     */
    Map<String,Object> getIndexChart(YZCXSearchParam param);

    /**
     * 获取总费
     * @param param
     * @return
     */
    FeiYongHuiZong getIndexFeiYongZong(YZCXSearchParam param) throws ParseException;
}
