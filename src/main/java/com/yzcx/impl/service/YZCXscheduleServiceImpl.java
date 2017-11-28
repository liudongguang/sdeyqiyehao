package com.yzcx.impl.service;

import com.ldg.api.util.JsonUtil;
import com.ldg.api.vo.ResultMsg2;
import com.weixin.util.HttpClientUtil;
import com.yzcx.api.po.YzcxHandleImportdate;
import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.service.YZCXscheduleService;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXProperties;
import com.yzcx.api.util.YZCXscheduleMapToListHandler;
import com.yzcx.api.vo.*;
import com.yzcx.api.vo.parsejson.Json_FeiYong;
import com.yzcx.api.vo.parsejson.Json_Jbzd;
import com.yzcx.api.vo.parsejson.Json_Menzhen;
import com.yzcx.api.vo.parsejson.Json_Yuyue;
import com.yzcx.impl.mapper.YzcxHandleImportdateMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoDayMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMonthMapper;
import com.yzcx.impl.service.handler.YzcxHandleInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
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

    private void handlerMenzhenRiGuiDang(YZCXHandlerData yzcxHandlerData, Map<String, String> requestparam) {
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
        /////按时间，科室，照普通急诊分组
        Map<String, Map<String, Map<Integer, Long>>> dateksmenjizhenMap = menzhenRs.getData().stream().map(item -> {
            item.setGhrqStr(LdgDateUtil.getYyyy_mm_ddString(item.getGhrq()));
            return item;
        }).collect(Collectors.groupingBy(MenZhenLiang::getGhrqStr, Collectors.groupingBy(MenZhenLiang::getKsmc, Collectors.groupingBy(MenZhenLiang::getSfjz, Collectors.counting()))));

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
        yzcxHandlerData.setMenzhenlist(YZCXscheduleMapToListHandler.handlerMap(menzhenGroup, YZCXConstant.menzhen, YZCXConstant.menzhenStr));
        yzcxHandlerData.setYuyuelist(YZCXscheduleMapToListHandler.handlerMap(yuyueGroup, YZCXConstant.yueyue, YZCXConstant.yueyueStr));
        yzcxHandlerData.setJbzdlist(YZCXscheduleMapToListHandler.handlerMap(jbzdGroup, YZCXConstant.jbzd, YZCXConstant.jbzdStr));
        yzcxHandlerData.setMenzhen_kslist(YZCXscheduleMapToListHandler.handlerMap2(ksmc, YZCXConstant.menzhen_ks));
        yzcxHandlerData.setMenzhen_yslist(YZCXscheduleMapToListHandler.handlerMap2(mzgroupByys, YZCXConstant.menzhen_ys));
        yzcxHandlerData.setMenzhen_sfjzlist(YZCXscheduleMapToListHandler.handlerMap2(mzgroupByjizhen, YZCXConstant.menzhen_sfjz));
        yzcxHandlerData.setYuyue_kslist(YZCXscheduleMapToListHandler.handlerMap2(yuyuegroupByKS, YZCXConstant.yuyue_ks));
        yzcxHandlerData.setYuyue_yslist(YZCXscheduleMapToListHandler.handlerMap2(yuyuegroupByYS, YZCXConstant.yuyue_ys));
        yzcxHandlerData.setJbzd_jblist(YZCXscheduleMapToListHandler.handlerMap2(jbzdGroupByJB, YZCXConstant.jbzd_jb));
        yzcxHandlerData.setKs_menzhen_putong_jizhenList(YZCXscheduleMapToListHandler.handlerKsMenzhen_jizhenMenzhen(dateksmenjizhenMap));
    }

    private void handlerFeiYongRiGuiDang(YZCXHandlerData yzcxHandlerData, YZCXSearchParam param) {
        Map<String, String> requestparam = new HashMap();
        requestparam.put("starte", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getStart()));
        requestparam.put("end", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getEnd()));
        String menzhenurl = YZCXProperties.getRequestPropertiesVal("fyxx");//获取门诊信息
        HttpClientUtil hc = HttpClientUtil.getInstance();
        final String s = hc.sendHttpPost(menzhenurl, requestparam);
        Json_FeiYong fyxx = JsonUtil.getObjectByJSON(s, Json_FeiYong.class);
        FYXXModle data = fyxx.getData();
        final List<FYXXzhuyuan> zhuyuanfy = data.getZhuyuanfy();
        final List<FYXXmenzhenchufang> menzhenyaofei = data.getMenzhenfycf();
        final List<FYXXmenzhenyiji> menzhenfyyj = data.getMenzhenfyyj();
        ///住院药费
        List<FYXXzhuyuan> zhuyuanYaofeiList = zhuyuanfy.stream().filter(item -> {
            if (YZCXConstant.zhuyuan_caoyaofei.equals(item.getFyxm()) || YZCXConstant.zhuyuan_xiyaofei.equals(item.getFyxm()) || YZCXConstant.zhuyuan_chenghaofei.equals(item.getFyxm())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        Double zhuyuanYaoFei = zhuyuanYaofeiList.stream().collect(Collectors.summingDouble(FYXXzhuyuan::getZjje));
        //住院其他费
        List<FYXXzhuyuan> zhuyuanQitaList = zhuyuanfy.stream().filter(item -> {
            if (YZCXConstant.qitafei.equals(item.getFyxm())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        Double zhuyuanQiTaFei = zhuyuanQitaList.stream().collect(Collectors.summingDouble(FYXXzhuyuan::getZjje));
        //住院医疗费
        List<FYXXzhuyuan> zhuyuanYiLiaoFeiList = zhuyuanfy.stream().filter(item -> {
            if (YZCXConstant.zhuyuan_caoyaofei.equals(item.getFyxm()) || YZCXConstant.zhuyuan_xiyaofei.equals(item.getFyxm()) || YZCXConstant.zhuyuan_chenghaofei.equals(item.getFyxm()) || YZCXConstant.qitafei.equals(item.getFyxm())) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());
        Double zhuyuanyiliaoFei = zhuyuanYiLiaoFeiList.stream().collect(Collectors.summingDouble(FYXXzhuyuan::getZjje));
        ///////////////////////////////获取科室下的住院费用
        //////////////////////////////
        //门诊药费
        List<FYXXmenzhenchufang> menzhenYaofeiList = menzhenyaofei.stream().collect(Collectors.toList());
        Double menzhenYaoFei = menzhenYaofeiList.stream().collect(Collectors.summingDouble(FYXXmenzhenchufang::getHjje));
        //门诊其他费
        List<FYXXmenzhenyiji> menzhenQiTaList = menzhenfyyj.stream().filter(item -> {
            if (YZCXConstant.qitafei.equals(item.getFygb())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        Double menzhenQitaiFei = menzhenQiTaList.stream().collect(Collectors.summingDouble(FYXXmenzhenyiji::getHjje));
        //门诊医疗费
        List<FYXXmenzhenyiji> menzhenYiLiaoFei = menzhenfyyj.stream().filter(item -> {
            if (YZCXConstant.qitafei.equals(item.getFygb())) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());
        Double menzhenyiliaoFei = menzhenYiLiaoFei.stream().collect(Collectors.summingDouble(FYXXmenzhenyiji::getHjje));
        List<YzcxHandleInfo> feiyongList = new ArrayList<>();
        feiyongList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.zhuyuan_yiliao, YZCXConstant.feiyong, param.getStart(), zhuyuanyiliaoFei));
        feiyongList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.zhuyuan_qita, YZCXConstant.feiyong, param.getStart(), zhuyuanQiTaFei));
        feiyongList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.zhuyuan_yaofei, YZCXConstant.feiyong, param.getStart(), zhuyuanYaoFei));
        //////////////
        feiyongList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.menzhen_yiliao, YZCXConstant.feiyong, param.getStart(), menzhenyiliaoFei));
        feiyongList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.menzhen_qita, YZCXConstant.feiyong, param.getStart(), menzhenQitaiFei));
        feiyongList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.menzhen_yaofei, YZCXConstant.feiyong, param.getStart(), menzhenYaoFei));
        ///
        Map<String, Double> zhuyuanKS_yaofei = zhuyuanYaofeiList.stream().collect(Collectors.groupingBy(FYXXzhuyuan::getBrks, Collectors.summingDouble(FYXXzhuyuan::getZjje)));
        Map<String, Double> zhuyuanKS_qitafei = zhuyuanQitaList.stream().collect(Collectors.groupingBy(FYXXzhuyuan::getBrks, Collectors.summingDouble(FYXXzhuyuan::getZjje)));
        Map<String, Double> zhuyuanKS_yiliaofei = zhuyuanYiLiaoFeiList.stream().collect(Collectors.groupingBy(FYXXzhuyuan::getBrks, Collectors.summingDouble(FYXXzhuyuan::getZjje)));
        ///
        Map<String, Double> menzhenKS_yaofei = menzhenYaofeiList.stream().collect(Collectors.groupingBy(FYXXmenzhenchufang::getKs, Collectors.summingDouble(FYXXmenzhenchufang::getHjje)));
        Map<String, Double> menzhenKS_qitafei = menzhenQiTaList.stream().collect(Collectors.groupingBy(FYXXmenzhenyiji::getKdks, Collectors.summingDouble(FYXXmenzhenyiji::getHjje)));
        Map<String, Double> menzhenKS_yiliaofei = menzhenYiLiaoFei.stream().collect(Collectors.groupingBy(FYXXmenzhenyiji::getKdks, Collectors.summingDouble(FYXXmenzhenyiji::getHjje)));
        //
        feiyongList.addAll(YZCXscheduleMapToListHandler.handlerKsFeiyong(zhuyuanKS_yaofei, param.getStart(), YZCXConstant.feiyong_zhuyuan_yaofei));
        feiyongList.addAll(YZCXscheduleMapToListHandler.handlerKsFeiyong(zhuyuanKS_qitafei, param.getStart(), YZCXConstant.feiyong_zhuyuan_qitafei));
        feiyongList.addAll(YZCXscheduleMapToListHandler.handlerKsFeiyong(zhuyuanKS_yiliaofei, param.getStart(), YZCXConstant.feiyong_zhuyuan_yiliaofei));
        feiyongList.addAll(YZCXscheduleMapToListHandler.handlerKsFeiyong(menzhenKS_yaofei, param.getStart(), YZCXConstant.feiyong_menzhen_yaofei));
        feiyongList.addAll(YZCXscheduleMapToListHandler.handlerKsFeiyong(menzhenKS_qitafei, param.getStart(), YZCXConstant.feiyong_menzhen_qitafei));
        feiyongList.addAll(YZCXscheduleMapToListHandler.handlerKsFeiyong(menzhenKS_yiliaofei, param.getStart(), YZCXConstant.feiyong_menzhen_yiliaofei));
        yzcxHandlerData.setFeiyongList(feiyongList);
    }

    @Override
    public YZCXHandlerData getmzinfo(YZCXSearchParam param) throws IOException, ParseException {
        /////
        Map<String, String> requestparam = new HashMap();
        requestparam.put("starte", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getStart()));
        requestparam.put("end", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getEnd()));
        /////
        YZCXHandlerData yzcxHandlerData = new YZCXHandlerData();
        param.setHandletype(Arrays.asList(YZCXConstant.importType_menzhen));
        int menzhenImportcount = yzcxHandleImportdateMapper.selectImportState(param);
        if (menzhenImportcount == 0) {
            handlerMenzhenRiGuiDang(yzcxHandlerData, requestparam);
        } else {
            return null;
        }
        return yzcxHandlerData;
    }


    @Override
    public void saveYZCXMenzhenData(YZCXHandlerData handlerData, YZCXSearchParam param) {
        if (handlerData.getMenzhenlist() != null) {
            yzcxHandleInfoMapper.batchInsert(handlerData.getMenzhenlist());
        }
        if (handlerData.getYuyuelist() != null) {
            yzcxHandleInfoMapper.batchInsert(handlerData.getYuyuelist());
        }
        if (handlerData.getJbzdlist() != null) {
            yzcxHandleInfoMapper.batchInsert(handlerData.getJbzdlist());
        }

        ///
        if (handlerData.getMenzhen_kslist() != null) {
            yzcxHandleInfoMapper.batchInsert(handlerData.getMenzhen_kslist());
        }
        if (handlerData.getMenzhen_yslist() != null) {
            yzcxHandleInfoMapper.batchInsert(handlerData.getMenzhen_yslist());
        }
        if (handlerData.getMenzhen_sfjzlist() != null) {
            yzcxHandleInfoMapper.batchInsert(handlerData.getMenzhen_sfjzlist());
        }
        if (handlerData.getYuyue_kslist() != null) {
            yzcxHandleInfoMapper.batchInsert(handlerData.getYuyue_kslist());
        }
        if (handlerData.getYuyue_yslist() != null) {
            yzcxHandleInfoMapper.batchInsert(handlerData.getYuyue_yslist());
        }
        if (handlerData.getJbzd_jblist() != null) {
            yzcxHandleInfoMapper.batchInsert(handlerData.getJbzd_jblist());
        }
        if (handlerData.getKs_menzhen_putong_jizhenList() != null) {
            yzcxHandleInfoMapper.batchInsert(handlerData.getKs_menzhen_putong_jizhenList());
        }
        ///保存处理的日期
        final List<YzcxHandleImportdate> dateByBetween = LdgDateUtil.getDateByBetween(param, YZCXConstant.importType_menzhen);
        yzcxHandleImportdateMapper.batchInsert(dateByBetween);
    }

    /**
     * 处理费用
     * @param param
     * @return
     * @throws ParseException
     */
    @Override
    public YZCXHandlerData handlerFeiyonginfo(YZCXSearchParam param) throws ParseException {
        /////
        YZCXHandlerData yzcxHandlerData = new YZCXHandlerData();
        //获取时间段里 每天的时间区间
        List<YZCXSearchParam> yzcxSearchParamByBetween = LdgDateUtil.getYZCXSearchParamByBetween(param.getStart(), param.getEnd());
        yzcxSearchParamByBetween.forEach(item -> {
            item.setHandletype(Arrays.asList(YZCXConstant.importType_feiyong));
            int feiyongImportcount = yzcxHandleImportdateMapper.selectImportState(item);
            if(feiyongImportcount==0){
                System.out.println("费用处理："+item);
                handlerFeiYongRiGuiDang(yzcxHandlerData, item);
                saveYZCXFeiyongData(yzcxHandlerData, item);//保存费用记录
            }else {
                System.out.println(item.getStart()+"已处理......");
            }
        });
        return yzcxHandlerData;
    }

    @Override
    public void saveYZCXFeiyongData(YZCXHandlerData handlerFeiYongData, YZCXSearchParam param) {
        if (handlerFeiYongData.getFeiyongList() != null) {
            yzcxHandleInfoMapper.batchInsert(handlerFeiYongData.getFeiyongList());
        }
        ///保存处理的日期
        final List<YzcxHandleImportdate> dateByBetween = LdgDateUtil.getDateByBetween(param, YZCXConstant.importType_feiyong);
        yzcxHandleImportdateMapper.batchInsert(dateByBetween);
    }

    ////////////
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

    /**
     * 门诊5分钟一更新
     *
     * @throws ParseException
     */
    @Override
    public void menzhenDayHandler() throws ParseException {
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

        ////
        //1.获取当前日期的记录  门诊情况
        param.setHandletype(Arrays.asList(YZCXConstant.menzhen_sfjz));
        int count = yzcxHandleInfoDayMapper.getDayTypeCount(param);
        //2.如果有值那么删除近2个小时的，重置获取数据的时间
        Map<String, String> requestparam = new HashMap();
        if (count != 0) {
            LocalDateTime beforeOneHource = nowTime.minus(1, ChronoUnit.HOURS);
            String startTime = beforeOneHource.format(LdgDateUtil.newDateFormat_yyyy_mm_dd_HH_00_00);
            requestparam.put("starte", startTime);
            requestparam.put("end", nowDateTime_Str);
            YZCXSearchParam param2 = new YZCXSearchParam();
            param2.setStart(LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate(startTime));
            param2.setEnd(nowDateTime);
            //删除前一个小时到现在的 门诊情况
            param2.setHandletype(Arrays.asList(YZCXConstant.menzhen_sfjz));
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
            List<YzcxHandleInfo> yzcxHandleInfos = YZCXscheduleMapToListHandler.handlerMap3(collect, YZCXConstant.menzhen_sfjz);
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
        //int delNum = yzcxHandleInfoDayMapper.deleteByTimeForType(param);//删除当天所有数据，下面重新插入
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
            List<YzcxHandleInfo> yuyueyks = YZCXscheduleMapToListHandler.handlerMap3(yuyuegroupByKS, YZCXConstant.yuyue_ks);
            yzcxHandleInfoDayMapper.batchInsert(yuyueyks);
        }
        ////////////////////////////////////////////////////疾病////////////////////////////////////////////////////////////////
        String jibingurl = YZCXProperties.getRequestPropertiesVal("jbzd");//获取预约信息
        requestparam.put("starte", date00);
        requestparam.put("end", date23);
        HttpClientUtil jibinghc = HttpClientUtil.getInstance();
        final String jibing = jibinghc.sendHttpPost(jibingurl, requestparam);
        Json_Jbzd jibingRs = JsonUtil.getObjectByJSON(jibing, Json_Jbzd.class);
        Map<String, Map<String, Long>> jbzdData = jibingRs.getData().stream().map(item -> {
            item.setRqStr(LdgDateUtil.getyyyy_mm_dd_hhString(item.getRq()));
            return item;
        }).collect(Collectors.groupingBy(JBZDLiang::getRqStr, Collectors.groupingBy(JBZDLiang::getJbmc, Collectors.counting())));
        if (jbzdData.size() > 0) {
            List<YzcxHandleInfo> jbzdList = YZCXscheduleMapToListHandler.handlerMap3(jbzdData, YZCXConstant.jbzd_jb);
            param.setHandletype(Arrays.asList(YZCXConstant.jbzd_jb));
            int delNum = yzcxHandleInfoDayMapper.deleteByTimeForType(param);
            yzcxHandleInfoDayMapper.batchInsert(jbzdList);
        }
    }

    @Override
    public List<YZCXSearchParam> getExistDaysFromGuiDangDays(YZCXSearchParam searchParam) {
        List<YZCXSearchParam> existsDays=yzcxHandleImportdateMapper.getExistDaysFromGuiDangDays(searchParam);
        return existsDays;
    }

    @Override
    public void handlerZhuYuanXinxiRiGuiDang(YZCXSearchParam param) {
        String zhuyuanurl = YZCXProperties.getRequestPropertiesVal("zyxx");//获取预约信息
        Map<String, String> requestparam = new HashMap();
        String date00 = LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getStart());
        String date23 = LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getEnd());
        requestparam.put("starte", date00);
        requestparam.put("end", date23);
        HttpClientUtil zhuyuanhtc = HttpClientUtil.getInstance();
        final String zhuyuanStr = zhuyuanhtc.sendHttpPost(zhuyuanurl, requestparam);
        System.out.println(zhuyuanStr);
    }
}
