package com.yzcx.impl.service;

import com.ldg.api.util.JsonUtil;
import com.ldg.api.vo.ResultMsg2;
import com.weixin.util.HttpClientUtil;
import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.service.YZCXscheduleService;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXProperties;
import com.yzcx.api.vo.*;
import com.yzcx.api.vo.parsejson.Json_Jbzd;
import com.yzcx.api.vo.parsejson.Json_Menzhen;
import com.yzcx.api.vo.parsejson.Json_Yuyue;
import com.yzcx.impl.mapper.YzcxHandleImportdateMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoDayMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMonthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by LiuDongguang on 2017/11/3.
 */
@Service
public class YZCXscheduleServiceImpl implements YZCXscheduleService {
    @Autowired
    private YzcxHandleInfoMapper yzcxHandleInfoMapper;
    @Autowired
    private YzcxHandleImportdateMapper yzcxHandleImportdateMapper;
    @Autowired
    private YzcxHandleInfoMonthMapper yzcxHandleInfoMonthMapper;
    @Autowired
    private YzcxHandleInfoDayMapper yzcxHandleInfoDayMapper;


    @Override
    public YZCXHandlerData getmzinfo(YZCXSearchParam param) throws IOException {
        /////
        Map<String, String> requestparam = new HashMap();
        requestparam.put("starte", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getStart()));
        requestparam.put("end", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getEnd()));
        /////
        int count = yzcxHandleImportdateMapper.selectImportState(param);
        if (count != 0) {
            return null;
        }
        String menzhenurl = YZCXProperties.getRequestPropertiesVal("menzhen");//获取门诊信息
        HttpClientUtil hc = HttpClientUtil.getInstance();
        final String s = hc.sendHttpPost(menzhenurl, requestparam);
        Json_Menzhen menzhenRs = JsonUtil.getObjectByJSON(s, Json_Menzhen.class);
        //
        String yuyueurl = YZCXProperties.getRequestPropertiesVal("yuyue");//获取预约信息
        HttpClientUtil yuyuehc = HttpClientUtil.getInstance();
        final String yuyue = yuyuehc.sendHttpPost(yuyueurl, requestparam);
        Json_Yuyue yuyueRs = JsonUtil.getObjectByJSON(yuyue, Json_Yuyue.class);
        //
        String jbzdurl = YZCXProperties.getRequestPropertiesVal("jbzd");//获取疾病信息
        HttpClientUtil jbzdhc = HttpClientUtil.getInstance();
        final String jbzd = jbzdhc.sendHttpPost(jbzdurl, requestparam);
        Json_Jbzd jbzdRs = JsonUtil.getObjectByJSON(jbzd, Json_Jbzd.class);
        //////////////////////////////////////////////////////
        Map<String, Long> menzhenGroup = menzhenRs.getData().stream().map(item -> {
            item.setGhrqStr(LdgDateUtil.getYyyy_mm_ddString(item.getGhrq()));
            return item;
        }).collect(Collectors.groupingBy(MenZhenLiang::getGhrqStr, Collectors.counting()));
        //按日期，科室名称分组
        Map<String, Map<String, Long>> ksmc = menzhenRs.getData().stream().map(item -> {
            item.setGhrqStr(LdgDateUtil.getYyyy_mm_ddString(item.getGhrq()));
            return item;
        }).collect(Collectors.groupingBy(MenZhenLiang::getGhrqStr, Collectors.groupingBy(MenZhenLiang::getKsmc, Collectors.counting())));
        //按日期，医生分组
        Map<String, Map<String, Long>> mzgroupByys = menzhenRs.getData().stream().map(item -> {
            item.setGhrqStr(LdgDateUtil.getYyyy_mm_ddString(item.getGhrq()));
            return item;
        }).collect(Collectors.groupingBy(MenZhenLiang::getGhrqStr, Collectors.groupingBy(MenZhenLiang::getYsmc, Collectors.counting())));
        //按日期，急诊分组   0 普通  1 急诊
        Map<String, Map<String, Long>> mzgroupByjizhen = menzhenRs.getData().stream().map(item -> {
            item.setGhrqStr(LdgDateUtil.getYyyy_mm_ddString(item.getGhrq()));
            //0  普通  1 急诊
            String sfjz = item.getSfjz().intValue() == 0 ? YZCXConstant.putong : YZCXConstant.jizhen;
            item.setSfjzStr(sfjz);
            return item;
        }).collect(Collectors.groupingBy(MenZhenLiang::getGhrqStr, Collectors.groupingBy(MenZhenLiang::getSfjzStr, Collectors.counting())));
/////////////////////////////////////////////////////////////////
        Map<String, Long> yuyueGroup = yuyueRs.getData().stream().map(item -> {
            item.setYyrqStr(LdgDateUtil.getYyyy_mm_ddString(item.getYyrq()));
            return item;
        }).collect(Collectors.groupingBy(YuYueLiang::getYyrqStr, Collectors.counting()));
        //日期，科室，分组
        Map<String, Map<String, Long>> yuyuegroupByKS = yuyueRs.getData().stream().map(item -> {
            item.setYyrqStr(LdgDateUtil.getYyyy_mm_ddString(item.getYyrq()));
            return item;
        }).collect(Collectors.groupingBy(YuYueLiang::getYyrqStr, Collectors.groupingBy(YuYueLiang::getKs, Collectors.counting())));
        //预约日期，医生 分组
        Map<String, Map<String, Long>> yuyuegroupByYS = yuyueRs.getData().stream().map(item -> {
            item.setYyrqStr(LdgDateUtil.getYyyy_mm_ddString(item.getYyrq()));
            return item;
        }).collect(Collectors.groupingBy(YuYueLiang::getYyrqStr, Collectors.groupingBy(YuYueLiang::getYs, Collectors.counting())));
        /////////////////////////////////////////////////////////////////////////
        Map<String, Long> jbzdGroup = jbzdRs.getData().stream().map(item -> {
            item.setRqStr(LdgDateUtil.getYyyy_mm_ddString(item.getRq()));
            return item;
        }).collect(Collectors.groupingBy(JBZDLiang::getRqStr, Collectors.counting()));
        //预约日期，疾病分组
        Map<String, Map<String, Long>> jbzdGroupByJB = jbzdRs.getData().stream().map(item -> {
            item.setRqStr(LdgDateUtil.getYyyy_mm_ddString(item.getRq()));
            return item;
        }).collect(Collectors.groupingBy(JBZDLiang::getRqStr, Collectors.groupingBy(JBZDLiang::getJbmc, Collectors.counting())));
        YZCXHandlerData yzcxHandlerData = new YZCXHandlerData();
        yzcxHandlerData.setMenzhenlist(handlerMap(menzhenGroup, YZCXConstant.menzhen, YZCXConstant.menzhenStr));
        yzcxHandlerData.setYuyuelist(handlerMap(yuyueGroup, YZCXConstant.yueyue, YZCXConstant.yueyueStr));
        yzcxHandlerData.setJbzdlist(handlerMap(jbzdGroup, YZCXConstant.jbzd, YZCXConstant.jbzdStr));
        yzcxHandlerData.setMenzhen_kslist(handlerMap2(ksmc, YZCXConstant.menzhen_ks));
        yzcxHandlerData.setMenzhen_yslist(handlerMap2(mzgroupByys, YZCXConstant.menzhen_ys));
        yzcxHandlerData.setMenzhen_sfjzlist(handlerMap2(mzgroupByjizhen, YZCXConstant.menzhen_sfjz));
        yzcxHandlerData.setYuyue_kslist(handlerMap2(yuyuegroupByKS, YZCXConstant.yuyue_ks));
        yzcxHandlerData.setYuyue_yslist(handlerMap2(yuyuegroupByYS, YZCXConstant.yuyue_ys));
        yzcxHandlerData.setJbzd_jblist(handlerMap2(jbzdGroupByJB, YZCXConstant.jbzd_jb));
        return yzcxHandlerData;
    }

    private List<YzcxHandleInfo> handlerMap(Map<String, Long> data, int type, String typeStr) {
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
    private List<YzcxHandleInfo> handlerMap2(Map<String, Map<String, Long>> data, int type) {
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
    private List<YzcxHandleInfo> handlerMap3(Map<String, Map<String, Long>> data, int type) {
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

    @Override
    public void saveYZCXData(YZCXHandlerData handlerData, YZCXSearchParam param) {
        yzcxHandleInfoMapper.batchInsert(handlerData.getMenzhenlist());
        yzcxHandleInfoMapper.batchInsert(handlerData.getYuyuelist());
        yzcxHandleInfoMapper.batchInsert(handlerData.getJbzdlist());
        ///
        yzcxHandleInfoMapper.batchInsert(handlerData.getMenzhen_kslist());
        yzcxHandleInfoMapper.batchInsert(handlerData.getMenzhen_yslist());
        yzcxHandleInfoMapper.batchInsert(handlerData.getMenzhen_sfjzlist());
        yzcxHandleInfoMapper.batchInsert(handlerData.getYuyue_kslist());
        yzcxHandleInfoMapper.batchInsert(handlerData.getYuyue_yslist());
        yzcxHandleInfoMapper.batchInsert(handlerData.getJbzd_jblist());
        ///保存处理的日期
        final List<Date> dateByBetween = LdgDateUtil.getDateByBetween(param.getStart(), param.getEnd());
        yzcxHandleImportdateMapper.batchInsert(dateByBetween);
    }
    //////////////////////////////////

    @Override
    public ResultMsg2 montho_mzinfo(YZCXSearchParam param) {
        ResultMsg2 msg = new ResultMsg2();
        int count = yzcxHandleInfoMonthMapper.selectImportState(param);
        if (count != 0) {
            msg.setErrmsg("已导入！");
            return msg;
        }
        List<YzcxHandleInfo> list = yzcxHandleInfoMapper.montho_mzinfo(param);
        Map<Integer, Map<String, Double>> collect = list.stream().collect(Collectors.groupingBy(YzcxHandleInfo::getHandletype, Collectors.groupingBy(YzcxHandleInfo::getName, Collectors.summingDouble(YzcxHandleInfo::getCount))));
        List<YzcxHandleInfo> menzhen = new ArrayList<>();
        collect.forEach((v, k) -> {
            Integer handletype = v;
            k.forEach((v2, k2) -> {
                String name = v2;
                Double sum = k2;
                YzcxHandleInfo yzcxHandleInfo = new YzcxHandleInfo();
                yzcxHandleInfo.setName(name);
                yzcxHandleInfo.setCount(sum);
                yzcxHandleInfo.setHandletype(handletype);
                yzcxHandleInfo.setHandledate(param.getStart());
                menzhen.add(yzcxHandleInfo);
            });
        });
        yzcxHandleInfoMonthMapper.batchInsert(menzhen);
        return msg;
    }


    @Override
    public void menzhenDayHandler() throws ParseException {
        LocalDate now=LocalDate.now();
        LocalDateTime nowTime=LocalDateTime.now();
        YZCXSearchParam param = new YZCXSearchParam();
        param.setStart(LdgDateUtil.getDayZeroTime(nowTime));
        param.setEnd(LdgDateUtil.getDayLastTime(nowTime));
        String nowDateTime_Str= nowTime.format(LdgDateUtil.newDateFormat_yyyy_mm_dd_HH_mm_ss);  //当前时间字符串形式
        Date nowDateTime=LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate(nowDateTime_Str);//当前时间日期格式
        String date00= LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getStart());
        String date23=LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getEnd());
        ////
        //1.获取当前日期的记录  门诊情况
        param.setHandletype(YZCXConstant.menzhen_sfjz);
        int count = yzcxHandleInfoDayMapper.getDayTypeCount(param);
        //2.如果有值那么删除近2个小时的，重置获取数据的时间
        Map<String, String> requestparam = new HashMap();
        if (count != 0) {
            LocalDateTime beforeOneHource = nowTime.minus(1, ChronoUnit.HOURS);
            String startTime = beforeOneHource.format(LdgDateUtil.newDateFormat_yyyy_mm_dd_HH_00_00);
            requestparam.put("starte", startTime);
            requestparam.put("end",nowDateTime_Str);
            YZCXSearchParam param2 = new YZCXSearchParam();
            param2.setStart(LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate(startTime));
            param2.setEnd(nowDateTime);
            //删除前一个小时到现在的 门诊情况
            param2.setHandletype(YZCXConstant.menzhen_sfjz);
            int delNum = yzcxHandleInfoDayMapper.deleteByTimeForType(param2);//删除上一个小时到当前时间的记录，下面重新插入
        } else {
            requestparam.put("starte",date00);
            requestparam.put("end", date23);
        }
        /////门诊
        String menzhenurl = YZCXProperties.getRequestPropertiesVal("menzhen");//获取门诊信息
        HttpClientUtil hc = HttpClientUtil.getInstance();
        final String s = hc.sendHttpPost(menzhenurl, requestparam);
        Json_Menzhen menzhenRs = JsonUtil.getObjectByJSON(s, Json_Menzhen.class);
        Map<String, Map<String, Long>> collect = menzhenRs.getData().stream().map(item -> {
            Date ghrq = item.getGhrq();
            String ghrqStr = LdgDateUtil.getyyyy_mm_dd_hhString(ghrq);
            item.setGhrqStr(ghrqStr);
            //0  普通  1 急诊
            String sfjz = item.getSfjz().intValue() == 0 ? YZCXConstant.putong : YZCXConstant.jizhen;
            item.setSfjzStr(sfjz);
            return item;
        }).collect(Collectors.groupingBy(MenZhenLiang::getGhrqStr, Collectors.groupingBy(MenZhenLiang::getSfjzStr, Collectors.counting())));
        if (collect.size() > 0) {
            List<YzcxHandleInfo> yzcxHandleInfos = handlerMap3(collect, YZCXConstant.menzhen_sfjz);
            yzcxHandleInfoDayMapper.batchInsert(yzcxHandleInfos);
        }
        /////////////////////////////////////////////预约，如果没有数据获取全部的预约信息，如果存在则取当前时间到本日结束的时间，删除当前时间到日结束时间的数据
        //1.获取当前日期的记录  门诊情况
        param.setHandletype(YZCXConstant.yuyue_ks);
        int yuyueCount = yzcxHandleInfoDayMapper.getDayTypeCount(param);//查询一天全时段数据
        //如果有数据，删除当前时间到日结束的时间
        if(yuyueCount!=0){
            requestparam.put("starte",nowDateTime_Str);
            requestparam.put("end",date23);
            YZCXSearchParam param2 = new YZCXSearchParam();
            param2.setStart(nowDateTime);
            param2.setEnd(param.getEnd());
            param2.setHandletype(YZCXConstant.yuyue_ks);
            int delNum = yzcxHandleInfoDayMapper.deleteByTimeForType(param2);//删除当前时间到本日结束的数据，下面重新插入
        }else{
            requestparam.put("starte",date00);
            requestparam.put("end",date23);
        }
        String yuyueurl = YZCXProperties.getRequestPropertiesVal("yuyue");//获取预约信息
        HttpClientUtil yuyuehc = HttpClientUtil.getInstance();
        final String yuyue = yuyuehc.sendHttpPost(yuyueurl, requestparam);
        Json_Yuyue yuyueRs = JsonUtil.getObjectByJSON(yuyue, Json_Yuyue.class);
        //日期，科室，分组
        Map<String, Map<String, Long>> yuyuegroupByKS = yuyueRs.getData().stream().map(item -> {
            item.setYyrqStr(LdgDateUtil.getyyyy_mm_dd_hhString(item.getYyrq()));
            return item;
        }).collect(Collectors.groupingBy(YuYueLiang::getYyrqStr, Collectors.groupingBy(YuYueLiang::getKs, Collectors.counting())));
        if (yuyuegroupByKS.size() > 0) {
            List<YzcxHandleInfo> yuyueyks = handlerMap3(yuyuegroupByKS, YZCXConstant.yuyue_ks);
            yzcxHandleInfoDayMapper.batchInsert(yuyueyks);
        }
    }

    public static void main(String[] args) {
        LocalDateTime localDate = LocalDateTime.now();
        localDate = localDate.minus(1, ChronoUnit.HOURS);
        String format = localDate.format(LdgDateUtil.newDateFormat_yyyy_mm_dd_HH_00_00);
        System.out.println(format);
    }
}
