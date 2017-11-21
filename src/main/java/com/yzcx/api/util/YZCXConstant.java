package com.yzcx.api.util;

/**
 * Created by LiuDongguang on 2017/11/7.
 */
public interface YZCXConstant {
    int importType_menzhen=1; //导入类型，门诊
    int importType_feiyong=2;//导入类型，费用
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
    /////////////////费用
    int feiyong = 12;//费用
    String zhuyuan_chenghaofei="成药费";
    String zhuyuan_xiyaofei="西药费";
    String zhuyuan_caoyaofei="草药费";
    String qitafei="其他费";
    String zhuyuan_yiliao="住院医疗费";
    String zhuyuan_yaofei="住院药费";
    String zhuyuan_qitai="住院其他费";
    String menzhen_yiliao="门诊医疗费";
    String menzhen_yaofei="门诊药费";
    String menzhen_qitai="门诊其他费";
    int feiyong_zhuyuan_yaofei = 13;//住院药费
    int feiyong_zhuyuan_qitafei = 14;//住院其他费
    int feiyong_zhuyuan_yiliaofei = 15;//住院医疗费用
    int feiyong_menzhen_yaofei = 16;//门诊药费
    int feiyong_menzhen_qitafei = 17;//门诊其他费
    int feiyong_menzhen_yiliaofei = 18;//门诊医疗费

    String obj = "obj";
}
