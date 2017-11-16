package com.yzcx.api.vo.yzcxdisplay;

import com.yzcx.api.vo.YZCXSearchParam;

/**
 * Created by LiuDongguang on 2017/11/9.
 * 全院概览
 */
public class QyglVo {
    private Double putong;
    private Double jizhen;
    private Double yuyueshu;
    //////
    private YZCXSearchParam param;


    public YZCXSearchParam getParam() {
        return param;
    }

    public void setParam(YZCXSearchParam param) {
        this.param = param;
    }

    public Double getYuyueshu() {
        return yuyueshu;
    }

    public void setYuyueshu(Double yuyueshu) {
        this.yuyueshu = yuyueshu;
    }

    public Double getPutong() {
        return putong;
    }

    public void setPutong(Double putong) {
        this.putong = putong;
    }

    public Double getJizhen() {
        return jizhen;
    }

    public void setJizhen(Double jizhen) {
        this.jizhen = jizhen;
    }
}
