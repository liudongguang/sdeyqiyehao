package com.yzcx.api.util;

/**
 * Created by LiuDongguang on 2017/11/7.
 */
public interface YZCXConstant {
    String[] months={"01月","02月","03月","04月","05月","06月","07月","08月","09月","10月","11月","12月"};

    String jizhen = "急诊";
    String putong = "普通门诊";
    int menzhen = 1;
    String menzhenStr = "门诊诊断";
    int yueyue = 2;
    String yueyueStr = "预约信息";
    int jbzd = 3;
    String jbzdStr = "疾病诊断";
    int menzhen_ks = 4;//门诊科室
    int menzhen_ys = 5;//门诊医生
    int menzhen_sfjz = 6;//急诊普通
    int yuyue_ks = 7;//预约科室
    int yuyue_ys = 8;//预约医生
    int jbzd_jb = 9;//疾病诊断
    int jbzd_ks_jizhen = 10;//科室急诊
    int jbzd_ks_menzhen = 11;//科室门诊
    String obj = "obj";
}
