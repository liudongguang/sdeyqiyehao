package com.yzcx.api.util;


import com.yzcx.api.vo.highchat.HighchartsConfig;
import com.yzcx.api.vo.highchat.HighchartsConfig_arr;
import com.yzcx.api.vo.highchat.YAxis;

/**
 * Created by LiuDongguang on 2017/5/25.
 */
public class HighChartUtils {
    /**
     *
     * @param title 标题
     * @param yAxisTitle y轴标题
     * @return
     */
    public final static HighchartsConfig createBasicChat(String title, String yAxisTitle){
        HighchartsConfig config = new HighchartsConfig();
        config.getTitle().setText(title);
        //config.getSubtitle().setText("数据来源: WorldClimate.com");
        YAxis yAxis=config.getyAxis();
        yAxis.getTitle().setText(yAxisTitle);
        return config;
    }
    public final static HighchartsConfig createBasicChat(String title,String yAxisTitle,boolean inverted){
        HighchartsConfig config = createBasicChat(title,yAxisTitle);
        config.getChart().setInverted(inverted);
        return config;
    }


    public final static HighchartsConfig_arr createArrBasicChat(String title, String yAxisTitle){
        HighchartsConfig_arr config = new HighchartsConfig_arr();
        config.getTitle().setText(title);
        //config.getSubtitle().setText("数据来源: WorldClimate.com");
        YAxis yAxis=config.getyAxis();
        yAxis.getTitle().setText(yAxisTitle);
        return config;
    }
}
