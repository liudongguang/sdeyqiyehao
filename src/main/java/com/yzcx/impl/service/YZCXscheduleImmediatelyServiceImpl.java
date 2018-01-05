package com.yzcx.impl.service;

import com.ldg.api.util.JsonUtil;
import com.weixin.util.HttpClientUtil;
import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.service.YZCXscheduleImmediatelyService;
import com.yzcx.api.util.*;
import com.yzcx.api.vo.*;
import com.yzcx.api.vo.parsejson.Json_Jbzd;
import com.yzcx.api.vo.parsejson.Json_Menzhen;
import com.yzcx.api.vo.parsejson.Json_Yuyue;
import com.yzcx.impl.mapper.YzcxHandleInfoDayMapper;
import com.yzcx.impl.service.handler.YzcxHandleInfoFactory;
import com.yzcx.impl.service.handler.YzcxHttpRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class YZCXscheduleImmediatelyServiceImpl implements YZCXscheduleImmediatelyService {
    @Autowired
    private YzcxHandleInfoDayMapper yzcxHandleInfoDayMapper;

    @Override
    public int deleteMenzhenDayHandler(YZCXSearchParam param) {
        return yzcxHandleInfoDayMapper.deleteByTimeForType(param);
    }

    private void menzhenDayHandler_menzhen(YZCXSearchParam param, String date00, String date23, String beforeOneHourceStr, String nowDateTime_Str, Date nowDateTime, Date zhengshiTime_Date, String zhengshiTime) throws ParseException {
        //1.获取当前日期的记录  门诊情况
        param.setHandletype(Arrays.asList(YZCXConstant.menzhen_sfjz));
        int count = yzcxHandleInfoDayMapper.getDayTypeCount(param);
        Map<String, String> requestparam = new HashMap();
        if (count != 0) {
            //2.如果有值那么删除近2个小时的，重置获取数据的时间
            requestparam.put("starte", beforeOneHourceStr);
            requestparam.put("end", nowDateTime_Str);
            YZCXSearchParam param2 = new YZCXSearchParam();
            param2.setStart(LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate(beforeOneHourceStr));
            param2.setEnd(nowDateTime);
            //删除前一个小时到现在的 门诊情况
            param2.setHandletype(Arrays.asList(YZCXConstant.menzhen_sfjz, YZCXConstant.menzhen_xingbieAge_nan, YZCXConstant.menzhen_xingbieAge_nv));
            int delNum = yzcxHandleInfoDayMapper.deleteByTimeForType(param2);//删除上一个小时到当前时间的记录，下面重新插入
        } else {
            requestparam.put("starte", date00);
            requestparam.put("end", date23);
        }
        /////门诊
        String menzhenurl = YZCXProperties.getRequestPropertiesVal("menzhen");//获取门诊信息
        HttpClientUtil hc = HttpClientUtil.getInstance();
        final String s = hc.sendHttpPost(menzhenurl, requestparam);
        Json_Menzhen menzhenRs = JsonUtil.getObjectByJSON(s, Json_Menzhen.class);
        if (menzhenRs == null || menzhenRs.getData() == null) {
            return;
        }
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
            List<YzcxHandleInfo> yzcxHandleInfos = YZCXscheduleMapToListHandler.handlerMapForHH(collect, YZCXConstant.menzhen_sfjz);
            yzcxHandleInfoDayMapper.batchInsert(yzcxHandleInfos);
        }
        /////////////////////////////////////////////////////////////////////////////////年龄，性别分组
        Map<String, Map<String, Map<String, Long>>> xingbieMap = menzhenRs.getData().stream().map(item -> {
            Date ghrq = item.getGhrq();
            String ghrqStr = LdgDateUtil.getyyyy_mm_dd_hhString(ghrq);
            item.setGhrqStr(ghrqStr);
            ////
            Date birthday = item.getCsny();
            long age = LdgDateUtil.getAgeByDate(birthday);
            if (age >= 0 && age <= 6) {
                item.setAgeString(YZCXConstant.age_0_6);
            } else if (age > 6 && age <= 17) {
                item.setAgeString(YZCXConstant.age_7_17);
            } else if (age > 17 && age <= 40) {
                item.setAgeString(YZCXConstant.age_18_40);
            } else if (age > 40 && age <= 65) {
                item.setAgeString(YZCXConstant.age_41_65);
            } else if (age > 65) {
                item.setAgeString(YZCXConstant.age_65after);
            }
            ///
            return item;
        }).collect(Collectors.groupingBy(MenZhenLiang::getGhrqStr, Collectors.groupingBy(MenZhenLiang::getBrxb, Collectors.groupingBy(MenZhenLiang::getAgeString, Collectors.counting()))));
        if (xingbieMap.size() > 0) {
            List<YzcxHandleInfo> yzcxHandleInfos = YZCXscheduleMapToListHandler.handlerMapForHH_bingren(xingbieMap);
            yzcxHandleInfoDayMapper.batchInsert(yzcxHandleInfos);
        }
        /////////////////////////////////////////////预约，如果没有数据获取全部的预约信息，如果存在则取当前时间到本日结束的时间，删除当前时间到日结束时间的数据
        //1.获取当前日期的记录  门诊情况
        param.setHandletype(Arrays.asList(YZCXConstant.yuyue_ks));
        //如果有数据
        int yuyuecount = yzcxHandleInfoDayMapper.getDayTypeCount(param);
        if (yuyuecount != 0) {
            YZCXSearchParam param2 = new YZCXSearchParam();
            param2.setStart(zhengshiTime_Date);
            param2.setEnd(param.getEnd());
            param2.setHandletype(Arrays.asList(YZCXConstant.yuyue_ks));
            int delNum = yzcxHandleInfoDayMapper.deleteByTimeForType(param2);//删除当前时间到本日末，下面重新插入
            requestparam.put("starte", zhengshiTime);
            requestparam.put("end", date23);
        } else {
            requestparam.put("starte", date00);
            requestparam.put("end", date23);
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
            List<YzcxHandleInfo> yuyueyks = YZCXscheduleMapToListHandler.handlerMapForHH(yuyuegroupByKS, YZCXConstant.yuyue_ks);
            yzcxHandleInfoDayMapper.batchInsert(yuyueyks);
        }
    }

    private void menzhenDayHandler_jibing(YZCXSearchParam param, String date00, String date23) {
        Map<String, String> requestparam = new HashMap();
        String jibingurl = YZCXProperties.getRequestPropertiesVal("jbzd");//获取预约信息
        requestparam.put("starte", date00);
        requestparam.put("end", date23);
        HttpClientUtil jibinghc = HttpClientUtil.getInstance();
        final String jibing = jibinghc.sendHttpPost(jibingurl, requestparam);
        Json_Jbzd jibingRs = JsonUtil.getObjectByJSON(jibing, Json_Jbzd.class);
        if (jibingRs == null || jibingRs.getData() == null) {
            return;
        }
        Map<String, Map<String, Long>> jbzdData = jibingRs.getData().stream().map(item -> {
            item.setRqStr(LdgDateUtil.getyyyy_mm_dd_hhString(item.getRq()));
            return item;
        }).collect(Collectors.groupingBy(JBZDLiang::getRqStr, Collectors.groupingBy(JBZDLiang::getJbmc, Collectors.counting())));
        if (jbzdData.size() > 0) {
            List<YzcxHandleInfo> jbzdList = YZCXscheduleMapToListHandler.handlerMapForHH(jbzdData, YZCXConstant.jbzd_jb);
            param.setHandletype(Arrays.asList(YZCXConstant.jbzd_jb));
            int delNum = yzcxHandleInfoDayMapper.deleteByTimeForType(param);
            yzcxHandleInfoDayMapper.batchInsert(jbzdList);
        }
    }

    private void menzhenDayHandler_zhuyuan(YZCXSearchParam param, String date00, String date23) {
        //入院情况设置
        List<Integer> zyxxRuYuanType = Arrays.asList(YZCXConstant.zhuyuan_keshiruyuan);
        param.setHandletype(zyxxRuYuanType);
        Map<String, String> requestparam = new HashMap();
        requestparam.put("starte", date00);
        requestparam.put("end", date23);
        yzcxHandleInfoDayMapper.deleteByTimeForType(param);//删除科室入院信息
        ZYXXModle fullDaydata = YzcxHttpRequest.getZYXX(requestparam);
        if (fullDaydata == null || fullDaydata.getBingren() == null) {
            return;
        }
        List<ZYXXzhuyuanbr> bingren = fullDaydata.getBingren();
        final Map<String, Map<String, Long>> dateTime_ryks = bingren.stream().filter(item -> item.getCyrq() == null).map(item -> {
            item.setRyrqStr(LdgDateUtil.getyyyy_mm_dd_hhString(item.getRyrq()));
            return item;
        }).collect(Collectors.groupingBy(ZYXXzhuyuanbr::getRyrqStr, Collectors.groupingBy(ZYXXzhuyuanbr::getBrks, Collectors.counting())));
        List<YzcxHandleInfo> yzcxHandleInfos = YZCXscheduleMapToListHandler.handlerMapForHH(dateTime_ryks, YZCXConstant.zhuyuan_keshiruyuan);
        if (yzcxHandleInfos.size() != 0) {
            yzcxHandleInfoDayMapper.batchInsert(yzcxHandleInfos);//保存入院信息
        }
        List<Integer> zyxxType = Arrays.asList(YZCXConstant.zhuyuan_brqk, YZCXConstant.zhuyuan_cyfs,
                YZCXConstant.zhuyuan_chuyuanRenshu, YZCXConstant.zhuyuan_ruyuanrenshu,
                YZCXConstant.zhuyuan_zhuanchuKS, YZCXConstant.zhuyuan_zhuanruKS, YZCXConstant.zhuyuan_zaiyuan);
        param.setHandletype(zyxxType);
        yzcxHandleInfoDayMapper.deleteByTimeForType(param);//删除除了入院信息的其他信息
        List<ZYXXzhuyuanbr> fullDaybingren = fullDaydata.getBingren();
        List<ZYXXzhuanke> fullDayzhuangke = fullDaydata.getZhuangke();
        final Integer zaiyuanNum = fullDaydata.getZaiyuanNum();
        //1.病人情况
        Map<String, Long> brqkMap = fullDaybingren.stream().filter(item -> StringUtils.isNotBlank(item.getBrqk())).collect(Collectors.groupingBy(ZYXXzhuyuanbr::getBrqk, Collectors.counting()));
        //2.出院情况
        Map<String, Long> cyfsMap = fullDaybingren.stream().filter(item -> StringUtils.isNotBlank(item.getCyfs())).collect(Collectors.groupingBy(ZYXXzhuyuanbr::getCyfs, Collectors.counting()));
        //3.出院总数
        final Long chuyuanRenshu = fullDaybingren.stream().filter(item -> item.getCyrq() != null).collect(Collectors.counting());
        //4.入院总数
        final Integer ruyuanrenshu = fullDaybingren.size();
        //5.转出
        final Map<String, Long> zhuanchuKS = fullDayzhuangke.stream().collect(Collectors.groupingBy(ZYXXzhuanke::getZhuangchukeshi, Collectors.counting()));
        //6.转入
        final Map<String, Long> zhuanruKS = fullDayzhuangke.stream().collect(Collectors.groupingBy(ZYXXzhuanke::getZhuangrukeshi, Collectors.counting()));
        List<YzcxHandleInfo> rsList = new ArrayList<>();
        final Date start = param.getStart();
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(brqkMap, start, YZCXConstant.zhuyuan_brqk));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(cyfsMap, start, YZCXConstant.zhuyuan_cyfs));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.zhuyuan_chuyuanRenshuStr, YZCXConstant.zhuyuan_chuyuanRenshu, start, chuyuanRenshu.doubleValue()));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.zhuyuan_ruyuanrenshuStr, YZCXConstant.zhuyuan_ruyuanrenshu, start, ruyuanrenshu.doubleValue()));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(zhuanchuKS, start, YZCXConstant.zhuyuan_zhuanchuKS));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(zhuanruKS, start, YZCXConstant.zhuyuan_zhuanruKS));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.zhuyuan_zaiyuanStr, YZCXConstant.zhuyuan_zaiyuan, start, zaiyuanNum.doubleValue()));
        if (rsList.size() != 0) {
            yzcxHandleInfoDayMapper.batchInsert(rsList);//保存入院信息
        }
    }

    private void menzhenDayHandler_yiji(YZCXSearchParam param, String date00, String date23) {
        Map<String, String> requestparam = new HashMap();
        requestparam.put("starte", date00);
        requestparam.put("end", date23);
        YIJIModle yijiModle = YzcxHttpRequest.getYIJI(requestparam);
        if (yijiModle == null) {
            return;
        }
        final List<YiJiInfo> mzyiji = yijiModle.getMzyiji();
        final List<YiJiInfo> zyyiji = yijiModle.getZyyiji();
        Integer menzhenCount = mzyiji.size();
        Integer zhuyuanCount = zyyiji.size();
        List<YiJiInfo> zongList = new ArrayList<>();
        zongList.addAll(mzyiji);
        zongList.addAll(zyyiji);
        /////
        final BigDecimal menzhenHeji = mzyiji.stream().map(YiJiInfo::getHjje)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        final BigDecimal zhuyuanHeji = zyyiji.stream().map(YiJiInfo::getHjje)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        /////
        final Map<String, Long> yijiFYType = zongList.stream().collect(Collectors.groupingBy(YiJiInfo::getFygb, Collectors.counting()));
        List<YzcxHandleInfo> rsList = new ArrayList<>();
        final Date start = param.getStart();
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.yiji_menzhenStr, YZCXConstant.yiji_menzhen, start, menzhenCount.doubleValue()));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.yiji_zhuyuanStr, YZCXConstant.yiji_zhuyuan, start, zhuyuanCount.doubleValue()));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.yiji_menzhen_hejiStr, YZCXConstant.yiji_menzhen_heji, start, menzhenHeji.doubleValue()));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.yiji_zhuyuan_hejiStr, YZCXConstant.yiji_zhuyuan_heji, start, zhuyuanHeji.doubleValue()));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(yijiFYType, start, YZCXConstant.yiji_type));
        param.setHandletype(Arrays.asList(YZCXConstant.yiji_menzhen, YZCXConstant.yiji_zhuyuan, YZCXConstant.yiji_type));
        if (rsList.size() != 0) {
            yzcxHandleInfoDayMapper.deleteByTimeForType(param);
            yzcxHandleInfoDayMapper.batchInsert(rsList);//保存医技信息
        }
    }

    private void menzhenDayHandler_shoushuxx(YZCXSearchParam param, String date00, String date23) throws ParseException {
        Map<String, String> requestparam = new HashMap();
        requestparam.put("starte", date00);
        requestparam.put("end", date23);
        SSXXModle shoushuxx = YzcxHttpRequest.getShoushuxx(requestparam);
        if (shoushuxx == null) {
            return;
        }
        //////////////////////////////////获取明天的手术安排
        YZCXSearchParam nextOneDay = YZCXControllerUtil.getNextOneDay();
        requestparam.put("starte", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(nextOneDay.getStart()));
        requestparam.put("end", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(nextOneDay.getEnd()));
        SSXXModle nextDayshoushuxx = YzcxHttpRequest.getShoushuxx(requestparam);
        if (nextDayshoushuxx == null) {
            return;
        }
        /////////////////////////////////
        final List<SSXX_anpai> ssap = shoushuxx.getSsap();
        final List<SSXX_info> ss = shoushuxx.getSs();
        //手术分级统计
        final Map<String, Map<String, Long>> ssfenji = ssap.stream().map(item -> {
            item.setSsrqStr(LdgDateUtil.getyyyy_mm_dd_hhString(item.getSsrq()));
            return item;
        }).collect(Collectors.groupingBy(SSXX_anpai::getSsrqStr, Collectors.groupingBy(SSXX_anpai::getSsfj, Collectors.counting())));
        //
        final Date start = param.getStart();
        Integer anpaiShu = ssap.size();
        Integer ssShu = ss.size();
        Integer nextDayanpaiShu = nextDayshoushuxx.getSsap().size();
        List<YzcxHandleInfo> rsList = new ArrayList<>();
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.shoushu_anpaiStr, YZCXConstant.shoushu_anpai, start, anpaiShu.doubleValue()));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.shoushu_anpaiStr, YZCXConstant.shoushu_anpai_nextDay, start, nextDayanpaiShu.doubleValue()));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.shoushu_infoStr, YZCXConstant.shoushu_info, start, ssShu.doubleValue()));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerMapForHH(ssfenji, YZCXConstant.shoushu_fenji));
        param.setHandletype(Arrays.asList(YZCXConstant.shoushu_anpai, YZCXConstant.shoushu_info, YZCXConstant.shoushu_fenji, YZCXConstant.shoushu_anpai_nextDay));
        if (rsList.size() != 0) {
            yzcxHandleInfoDayMapper.deleteByTimeForType(param);
            yzcxHandleInfoDayMapper.batchInsert(rsList);//保存手术
        }
    }

    private void menzhenDayHandler_huizhen(YZCXSearchParam param, String date00, String date23) {
        Map<String, String> requestparam = new HashMap();
        requestparam.put("starte", date00);
        requestparam.put("end", date23);
        HzxxModle huiZhenxx = YzcxHttpRequest.getHuiZhenxx(requestparam);
        if (huiZhenxx == null) {
            return;
        }
        Map<String, Long> jieshouKSAndSum = huiZhenxx.getJieshou().stream().collect(Collectors.groupingBy(HzxxInfo::getKs, Collectors.counting()));
        Map<String, Long> shenqingKSAndSum = huiZhenxx.getShenqing().stream().collect(Collectors.groupingBy(HzxxInfo::getSqks, Collectors.counting()));
        final Date start = param.getStart();
        List<YzcxHandleInfo> rsList = new ArrayList<>();
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(jieshouKSAndSum, start, YZCXConstant.huizhen_jieshou));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(shenqingKSAndSum, start, YZCXConstant.huizhen_shenqing));
        param.setHandletype(Arrays.asList(YZCXConstant.huizhen_jieshou, YZCXConstant.huizhen_shenqing));
        if (rsList.size() != 0) {
            yzcxHandleInfoDayMapper.deleteByTimeForType(param);
            yzcxHandleInfoDayMapper.batchInsert(rsList);//保存会诊
        }
    }

    private void menzhenDayHandler_chufang(YZCXSearchParam param, String date00, String date23) {
        Map<String, String> requestparam = new HashMap();
        requestparam.put("starte", date00);
        requestparam.put("end", date23);
        ChuFangModle chuFang = YzcxHttpRequest.getChuFang(requestparam);
        if (chuFang == null || chuFang.getData() == null) {
            return;
        }
        DoubleSummaryStatistics summaryStatistics = chuFang.getData().stream().filter(item ->
                item.getHjje() >= 0
        ).collect(Collectors.summarizingDouble(FYXXmenzhenchufang::getHjje));//最小，最大，平均金额,总额
        long chufangshu = summaryStatistics.getCount();//处方数
        double pjchufang = summaryStatistics.getAverage();//平均处方金额
        double maxchufang = summaryStatistics.getMax();//最大处方金额
        double minchufang = summaryStatistics.getMin();//最小处方金额
        double sumchufang = summaryStatistics.getSum();//处方金额总和
        String menzhenurl = YZCXProperties.getRequestPropertiesVal("menzhen");//获取门诊信息
        HttpClientUtil hc = HttpClientUtil.getInstance();
        final String s = hc.sendHttpPost(menzhenurl, requestparam);
        Json_Menzhen menzhenRs = JsonUtil.getObjectByJSON(s, Json_Menzhen.class);
        Set<String> ysName = new HashSet<>();
        int[] menzhensum = {0};
        int[] jizhensum = {0};
        menzhenRs.getData().forEach(item -> {
            Integer sfjz = item.getSfjz();
            String ysname = item.getYsmc();
            //0  普通  1 急诊
            if (sfjz == 0) {
                menzhensum[0]++;
            } else {
                jizhensum[0]++;
            }
            ysName.add(ysname.trim());
        });
        int ysgs = ysName.size();//医生个数
        int jzsum = jizhensum[0];
        int mzsum = menzhensum[0];
        List<YzcxHandleInfo> rsList = new ArrayList<>();
        final Date start = param.getStart();
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo("处方", YZCXConstant.chufang_chufangshu, start, Double.valueOf(chufangshu)));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo("处方", YZCXConstant.chufang_pjchufang, start, pjchufang));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo("处方", YZCXConstant.chufang_maxchufang, start, maxchufang));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo("处方", YZCXConstant.chufang_minchufang, start, minchufang));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo("处方", YZCXConstant.chufang_sumchufang, start, sumchufang));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo("处方", YZCXConstant.chufang_yssum, start, Double.valueOf(ysgs)));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo("处方", YZCXConstant.chufang_menzhen, start, Double.valueOf(mzsum)));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo("处方", YZCXConstant.chufang_jizhen, start, Double.valueOf(jzsum)));
        param.setHandletype(Arrays.asList(YZCXConstant.chufang_chufangshu, YZCXConstant.chufang_pjchufang, YZCXConstant.chufang_maxchufang
                , YZCXConstant.chufang_minchufang, YZCXConstant.chufang_sumchufang, YZCXConstant.chufang_yssum, YZCXConstant.chufang_menzhen, YZCXConstant.chufang_jizhen));
        if (rsList.size() != 0) {
            yzcxHandleInfoDayMapper.deleteByTimeForType(param);
            yzcxHandleInfoDayMapper.batchInsert(rsList);//保存处方
        }
    }

    /**
     * 门诊5分钟一更新
     *
     * @throws ParseException
     */
    @Override
    public void ImmediatelyHandler() throws ParseException {
        LocalDateTime nowTime = LocalDateTime.now();
        YZCXSearchParam param = new YZCXSearchParam();
        param.setStart(LdgDateUtil.getDayZeroTime(nowTime));
        param.setEnd(LdgDateUtil.getDayLastTime(nowTime));
        String nowDateTime_Str = nowTime.format(LdgDateUtil.newDateFormat_yyyy_mm_dd_HH_mm_ss);  //当前时间字符串形式
        Date nowDateTime = LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate(nowDateTime_Str);//当前时间日期格式
        String date00 = LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getStart());
        String date23 = LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getEnd());
        String zhengshiTime = nowTime.format(LdgDateUtil.newDateFormat_yyyy_mm_dd_HH_00_00);//整点时间
        Date zhengshiTime_Date = LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate(zhengshiTime);
        LocalDateTime beforeOneHource = nowTime.minus(1, ChronoUnit.HOURS);
        String beforeOneHourceStr = beforeOneHource.format(LdgDateUtil.newDateFormat_yyyy_mm_dd_HH_00_00);
        /////////////////////////////////////////////////////1.门诊/////////////////////////////////////////////////////////////////
        menzhenDayHandler_menzhen(param, date00, date23, beforeOneHourceStr, nowDateTime_Str, nowDateTime, zhengshiTime_Date, zhengshiTime);
        ////////////////////////////////////////////////////2.疾病////////////////////////////////////////////////////////////////
        menzhenDayHandler_jibing(param, date00, date23);
        //////////////////////////////////////////////////3.住院信息////////////////////////////////////////////////////////////////////
        menzhenDayHandler_zhuyuan(param, date00, date23);
        ////////////////////////////////////////////////////4.医技信息////////////////////////////////////////////////////////////////////
        menzhenDayHandler_yiji(param, date00, date23);
        ////////////////////////////////////////////////////5.手术信息////////////////////////////////////////////////////////
        menzhenDayHandler_shoushuxx(param, date00, date23);
        /////////////////////////////////////////////////6.会诊信息////////////////////////////////////////////
        menzhenDayHandler_huizhen(param, date00, date23);
        ///////////////////////////////////////////7.处方
        menzhenDayHandler_chufang(param, date00, date23);
    }

}
