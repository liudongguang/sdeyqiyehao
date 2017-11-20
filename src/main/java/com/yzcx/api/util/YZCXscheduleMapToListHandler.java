package com.yzcx.api.util;

import com.yzcx.api.po.YzcxHandleInfo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YZCXscheduleMapToListHandler {
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
}
