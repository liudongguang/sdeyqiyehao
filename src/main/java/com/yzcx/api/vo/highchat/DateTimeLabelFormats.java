package com.yzcx.api.vo.highchat;

/**
 * Created by LiuDongguang on 2017/6/15.
 */
public class DateTimeLabelFormats {
    private String hour="%H:%M";
    private String day="%m/%e %H:%M";

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
