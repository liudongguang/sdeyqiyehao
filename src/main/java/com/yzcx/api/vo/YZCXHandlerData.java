package com.yzcx.api.vo;

import com.yzcx.api.po.YzcxHandleInfo;

import java.util.List;

/**
 * Created by LiuDongguang on 2017/11/7.
 */
public class YZCXHandlerData {
    private List<YzcxHandleInfo> menzhenlist; //日门诊量
    private List<YzcxHandleInfo> yuyuelist;//日预约量
    private List<YzcxHandleInfo> jbzdlist;//日诊断量

    private List<YzcxHandleInfo> menzhen_kslist;//科室信息
    private List<YzcxHandleInfo> menzhen_yslist;//医生信息
    private List<YzcxHandleInfo> menzhen_sfjzlist;//普通与急诊门诊
    private List<YzcxHandleInfo> yuyue_kslist;//预约 科室
    private List<YzcxHandleInfo> yuyue_yslist;//预约医生
    private List<YzcxHandleInfo> jbzd_jblist;// 疾病
    //////
    private  List<YzcxHandleInfo> ks_menzhen_putong_jizhenList;
    ///费用
    private List<YzcxHandleInfo> feiyongList;


    public List<YzcxHandleInfo> getFeiyongList() {
        return feiyongList;
    }

    public void setFeiyongList(List<YzcxHandleInfo> feiyongList) {
        this.feiyongList = feiyongList;
    }

    public List<YzcxHandleInfo> getKs_menzhen_putong_jizhenList() {
        return ks_menzhen_putong_jizhenList;
    }

    public void setKs_menzhen_putong_jizhenList(List<YzcxHandleInfo> ks_menzhen_putong_jizhenList) {
        this.ks_menzhen_putong_jizhenList = ks_menzhen_putong_jizhenList;
    }

    public List<YzcxHandleInfo> getMenzhenlist() {
        return menzhenlist;
    }

    public void setMenzhenlist(List<YzcxHandleInfo> menzhenlist) {
        this.menzhenlist = menzhenlist;
    }

    public List<YzcxHandleInfo> getYuyuelist() {
        return yuyuelist;
    }

    public void setYuyuelist(List<YzcxHandleInfo> yuyuelist) {
        this.yuyuelist = yuyuelist;
    }

    public List<YzcxHandleInfo> getJbzdlist() {
        return jbzdlist;
    }

    public void setJbzdlist(List<YzcxHandleInfo> jbzdlist) {
        this.jbzdlist = jbzdlist;
    }

    public List<YzcxHandleInfo> getMenzhen_kslist() {
        return menzhen_kslist;
    }

    public void setMenzhen_kslist(List<YzcxHandleInfo> menzhen_kslist) {
        this.menzhen_kslist = menzhen_kslist;
    }

    public List<YzcxHandleInfo> getMenzhen_yslist() {
        return menzhen_yslist;
    }

    public void setMenzhen_yslist(List<YzcxHandleInfo> menzhen_yslist) {
        this.menzhen_yslist = menzhen_yslist;
    }

    public List<YzcxHandleInfo> getMenzhen_sfjzlist() {
        return menzhen_sfjzlist;
    }

    public void setMenzhen_sfjzlist(List<YzcxHandleInfo> menzhen_sfjzlist) {
        this.menzhen_sfjzlist = menzhen_sfjzlist;
    }

    public List<YzcxHandleInfo> getYuyue_kslist() {
        return yuyue_kslist;
    }

    public void setYuyue_kslist(List<YzcxHandleInfo> yuyue_kslist) {
        this.yuyue_kslist = yuyue_kslist;
    }

    public List<YzcxHandleInfo> getYuyue_yslist() {
        return yuyue_yslist;
    }

    public void setYuyue_yslist(List<YzcxHandleInfo> yuyue_yslist) {
        this.yuyue_yslist = yuyue_yslist;
    }

    public List<YzcxHandleInfo> getJbzd_jblist() {
        return jbzd_jblist;
    }

    public void setJbzd_jblist(List<YzcxHandleInfo> jbzd_jblist) {
        this.jbzd_jblist = jbzd_jblist;
    }
}
