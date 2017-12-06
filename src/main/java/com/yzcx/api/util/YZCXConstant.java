package com.yzcx.api.util;

import java.util.Arrays;
import java.util.List;

/**
 * Created by LiuDongguang on 2017/11/7.
 */
public interface YZCXConstant {
    int importType_menzhen=1; //导入类型，门诊
    int importType_feiyong=2;//导入类型，费用
    int importType_zhuyuan=3; //导入类型，住院信息
    String[] months={"01月","02月","03月","04月","05月","06月","07月","08月","09月","10月","11月","12月"};
    List<String> hoursList= Arrays.asList("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
    String age_0_6="0-6岁";
    String age_7_17="7-17岁";
    String age_18_40="18-40岁";
    String age_41_65="41-65岁";
    String age_65after="65岁以后";
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
    String zhuyuan_qita ="住院其他费";
    String menzhen_yiliao="门诊医疗费";
    String menzhen_yaofei="门诊药费";
    String menzhen_qita ="门诊其他费";
    int feiyong_zhuyuan_yaofei = 13;//住院药费
    int feiyong_zhuyuan_qitafei = 14;//住院其他费
    int feiyong_zhuyuan_yiliaofei = 15;//住院医疗费用
    int feiyong_menzhen_yaofei = 16;//门诊药费
    int feiyong_menzhen_qitafei = 17;//门诊其他费
    int feiyong_menzhen_yiliaofei = 18;//门诊医疗费
    /////////////////////////住院信息
    int zhuyuan_brqk = 19;//病人情况
    int zhuyuan_cyfs = 20;//出院情况
    int zhuyuan_chuyuanRenshu = 21;//出院总人数
    String zhuyuan_chuyuanRenshuStr="出院总数";
    int zhuyuan_ruyuanrenshu = 22;//入院总人数
    String zhuyuan_ruyuanrenshuStr="入院总数";
    int zhuyuan_keshiruyuan = 23;//科室入院情况
    int zhuyuan_zhuanchuKS = 24;//科室转出
    int zhuyuan_zhuanruKS = 25;//科室转入
    int zhuyuan_keshishizhan = 26;//科室实占
    int zhuyuan_keshikaifang = 27;//科室开放
    String zhuyuan_brqk_bingwei="病危";
    String zhuyuan_brqk_bingzhong="病重";
    String zhuyuan_cyfs_siwang="死亡";
    int zhuyuan_zaiyuan = 28;//在院人数
    String zhuyuan_zaiyuanStr="在院人数";
    String obj = "obj";
    int menzhen_xingbieAge_nan = 29;//性别年龄分类--男
    int menzhen_xingbieAge_nv = 30;//性别年龄分类--女
    String sex_nan="男";
    String sex_nv="女";

}
