package com.yzcx.api.util;

import com.yzcx.api.bo.YzcxHandleInfo_FeiYong;
import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.impl.service.handler.YzcxHandleInfoFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class YZCXscheduleMapToListHandler {


    public static List<YzcxHandleInfo> handlerCommonData(Map<String,? extends Number> nameAndNumber,Date handlerDate,int handlerType){
        List<YzcxHandleInfo> rsList=new ArrayList<>();
        nameAndNumber.forEach((name,number)->{
            rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(name,handlerType,handlerDate,number.doubleValue()));
        });
        return rsList;
    }


    /**
     * 科室费用
     * @param ksAndSumJine
     * @param hejiDate
     * @param handlerType
     * @return
     */
    public static List<YzcxHandleInfo> handlerKsFeiyong(Map<String,Double> ksAndSumJine,Date hejiDate,int handlerType){
        List<YzcxHandleInfo> rsList=new ArrayList<>();
        ksAndSumJine.forEach((ks,sumJine)->{
            rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(ks,handlerType,hejiDate,sumJine));
        });
        return rsList;
    }

    /**
     * 科室有关的门诊，急诊
     * @param map
     * @return
     */
    public static List<YzcxHandleInfo> handlerKsMenzhen_jizhenMenzhen(Map<String, Map<String, Map<Integer, Long>>> map){
        List<YzcxHandleInfo> ksmenzhenmenjizhen = new ArrayList<>();
        map.forEach((date,v)->{
            v.forEach((ksname,menjizhenCount)->{
                //   0 普通  1 急诊
                menjizhenCount.forEach((zhenduanType,count)->{
                    YzcxHandleInfo yzcxHandleInfo = new YzcxHandleInfo();
                    yzcxHandleInfo.setCount(Double.valueOf(count.toString()));
                    try {
                        yzcxHandleInfo.setHandledate(LdgDateUtil.getYyyy_mm_ddDate(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    yzcxHandleInfo.setHandletype(zhenduanType==0?YZCXConstant.jbzd_ks_menzhen:YZCXConstant.jbzd_ks_jizhen);
                    yzcxHandleInfo.setName(ksname);
                    ksmenzhenmenjizhen.add(yzcxHandleInfo);
                });
            });
        });
        return  ksmenzhenmenjizhen;
    }
    /**
     * yyyymmdd日期
     * @param data
     * @param type
     * @param typeStr
     * @return
     */
    public static List<YzcxHandleInfo> handlerMap(Map<String, Long> data, int type, String typeStr) {
        List<YzcxHandleInfo> menzhen = new ArrayList<>();
        data.forEach((k, v) -> {
            YzcxHandleInfo yzcxHandleInfo = new YzcxHandleInfo();
            yzcxHandleInfo.setHandletype(type);
            yzcxHandleInfo.setCount(Double.valueOf(v.toString()));
            yzcxHandleInfo.setName(typeStr);
            try {
                yzcxHandleInfo.setHandledate(LdgDateUtil.getYyyy_mm_ddDate(k));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            menzhen.add(yzcxHandleInfo);
        });
        return menzhen;
    }

    /**
     * 处理日期格式化为YYYY-MM-DD
     *
     * @param data
     * @param type
     * @return
     */
    public static List<YzcxHandleInfo> handlerMap2(Map<String, Map<String, Long>> data, int type) {
        List<YzcxHandleInfo> rtList = new ArrayList<>();
        data.forEach((k, v) -> {
            String date = k;
            v.forEach((k2, v2) -> {
                String name = k2;
                Long count = v2;
                YzcxHandleInfo yzcxHandleInfo = new YzcxHandleInfo();
                try {
                    yzcxHandleInfo.setHandledate(LdgDateUtil.getYyyy_mm_ddDate(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                yzcxHandleInfo.setHandletype(type);
                yzcxHandleInfo.setCount(Double.valueOf(count.toString()));
                yzcxHandleInfo.setName(name);
                rtList.add(yzcxHandleInfo);
            });
        });
        return rtList;
    }
    /**
     * 处理日期格式为yyyy-mm-dd hh
     *
     * @param data
     * @param type
     * @return
     */
    public static List<YzcxHandleInfo> handlerMap3(Map<String, Map<String, Long>> data, int type) {
        List<YzcxHandleInfo> rtList = new ArrayList<>();
        data.forEach((k, v) -> {
            String date = k;
            v.forEach((k2, v2) -> {
                String name = k2;
                Long count = v2;
                YzcxHandleInfo yzcxHandleInfo = new YzcxHandleInfo();
                try {
                    yzcxHandleInfo.setHandledate(LdgDateUtil.getyyyy_mm_dd_hhDate(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                yzcxHandleInfo.setHandletype(type);
                yzcxHandleInfo.setCount(Double.valueOf(count.toString()));
                yzcxHandleInfo.setName(name);
                rtList.add(yzcxHandleInfo);
            });
        });
        return rtList;
    }


    public  static List<YzcxHandleInfo_FeiYong> getKSFeiyong(Map<String, Map<Integer, Double>> ksTypeNum) {
        List<YzcxHandleInfo_FeiYong> ksFeiYongInfoList = new ArrayList<>();
        ksTypeNum.forEach((ksName, map) -> {
            YzcxHandleInfo_FeiYong yzcxHandleInfo_feiYong = new YzcxHandleInfo_FeiYong();
            yzcxHandleInfo_feiYong.setKsname(ksName);
            map.forEach((type, zongjine) -> {
                switch (type) {
                    case YZCXConstant.feiyong_zhuyuan_yiliaofei:
                        yzcxHandleInfo_feiYong.setZhuyuanyiliaofei(zongjine);
                        break;
                    case YZCXConstant.feiyong_zhuyuan_yaofei:
                        yzcxHandleInfo_feiYong.setZhuyuanyaofei(zongjine);
                        break;
                    case YZCXConstant.feiyong_zhuyuan_qitafei:
                        yzcxHandleInfo_feiYong.setZhuyuanqitafei(zongjine);
                        break;
                    case YZCXConstant.feiyong_menzhen_yiliaofei:
                        yzcxHandleInfo_feiYong.setMenzhenyiliaofei(zongjine);
                        break;
                    case YZCXConstant.feiyong_menzhen_yaofei:
                        yzcxHandleInfo_feiYong.setMenzhenyaofei(zongjine);
                        break;
                    case YZCXConstant.feiyong_menzhen_qitafei:
                        yzcxHandleInfo_feiYong.setMenzhenqitafei(zongjine);
                        break;
                }
            });
            ksFeiYongInfoList.add(yzcxHandleInfo_feiYong);
        });
        return ksFeiYongInfoList;
    }
}
