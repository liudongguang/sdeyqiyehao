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
import com.yzcx.api.vo.parsejson.*;
import com.yzcx.impl.mapper.YzcxHandleImportdateMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoDayMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMonthMapper;
import com.yzcx.impl.service.handler.YzcxHandleInfoFactory;
import com.yzcx.impl.service.handler.YzcxHttpRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
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


    private List<YzcxHandleInfo> handlerMenzhenRiGuiDang(YZCXSearchParam param) {
        Map<String, String> requestparam = new HashMap();
        requestparam.put("starte", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getStart()));
        requestparam.put("end", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getEnd()));
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
        List<YzcxHandleInfo> rs=new ArrayList<>();
        rs.addAll(YZCXscheduleMapToListHandler.handlerMap(menzhenGroup, YZCXConstant.menzhen, YZCXConstant.menzhenStr));
        rs.addAll(YZCXscheduleMapToListHandler.handlerMap(yuyueGroup, YZCXConstant.yueyue, YZCXConstant.yueyueStr));
        rs.addAll(YZCXscheduleMapToListHandler.handlerMap(jbzdGroup, YZCXConstant.jbzd, YZCXConstant.jbzdStr));
        rs.addAll(YZCXscheduleMapToListHandler.handlerMap2(ksmc, YZCXConstant.menzhen_ks));
        rs.addAll(YZCXscheduleMapToListHandler.handlerMap2(mzgroupByys, YZCXConstant.menzhen_ys));
        rs.addAll(YZCXscheduleMapToListHandler.handlerMap2(mzgroupByjizhen, YZCXConstant.menzhen_sfjz));
        rs.addAll(YZCXscheduleMapToListHandler.handlerMap2(yuyuegroupByKS, YZCXConstant.yuyue_ks));
        rs.addAll(YZCXscheduleMapToListHandler.handlerMap2(yuyuegroupByYS, YZCXConstant.yuyue_ys));
        rs.addAll(YZCXscheduleMapToListHandler.handlerMap2(jbzdGroupByJB, YZCXConstant.jbzd_jb));
        rs.addAll(YZCXscheduleMapToListHandler.handlerKsMenzhen_jizhenMenzhen(dateksmenjizhenMap));
        return rs;
    }

    private List<YzcxHandleInfo> handlerFeiYongRiGuiDang(YZCXSearchParam param) throws ParseException {
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
        return feiyongList;
    }

    @Override
    public void saveYZCXMenzhenData(YZCXSearchParam param) throws ParseException {
        //获取时间段里 每天的时间区间
        List<YZCXSearchParam> yzcxSearchParamByBetween = LdgDateUtil.getYZCXSearchParamByBetween(param.getStart(), param.getEnd());
        yzcxSearchParamByBetween.forEach(item -> {
            item.setHandletype(Arrays.asList(YZCXConstant.importType_menzhen));
            int menzhenImportcount = yzcxHandleImportdateMapper.selectImportState(item);
            if (menzhenImportcount == 0) {
                List<YzcxHandleInfo> yzcxHandleInfos = handlerMenzhenRiGuiDang(item);
                yzcxHandleInfoMapper.batchInsert(yzcxHandleInfos);
                //保存处理日期
                YzcxHandleImportdate yzcxHandleImportdate = new YzcxHandleImportdate(item.getStart(), YZCXConstant.importType_menzhen);
                yzcxHandleImportdateMapper.insertOneInfo(yzcxHandleImportdate);
            } else {
                System.out.println(item.getStart() + "门诊信息已处理");
                return;
            }
        });
    }

    /**
     * 处理费用
     *
     * @param param
     * @return
     * @throws ParseException
     */
    @Override
    public void handlerFeiyonginfo(YZCXSearchParam param) throws ParseException {
        //获取时间段里 每天的时间区间
        List<YZCXSearchParam> yzcxSearchParamByBetween = LdgDateUtil.getYZCXSearchParamByBetween(param.getStart(), param.getEnd());
        yzcxSearchParamByBetween.forEach(item -> {
            item.setHandletype(Arrays.asList(YZCXConstant.importType_feiyong));
            int feiyongImportcount = yzcxHandleImportdateMapper.selectImportState(item);
            if (feiyongImportcount == 0) {
                System.out.println("费用处理：" + item);
                List<YzcxHandleInfo> yzcxHandleInfos = null;
                try {
                    yzcxHandleInfos = handlerFeiYongRiGuiDang(item);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                yzcxHandleInfoMapper.batchInsert(yzcxHandleInfos);
                //保存处理日期
                yzcxHandleImportdateMapper.insertOneInfo(new YzcxHandleImportdate(item.getStart(),YZCXConstant.importType_feiyong));
            } else {
                System.out.println(item.getStart() + "费用信息已处理......");
                return;
            }
        });
    }

    @Override
    public List<YZCXSearchParam> getExistDaysFromGuiDangDays(YZCXSearchParam searchParam) {
        List<YZCXSearchParam> existsDays = yzcxHandleImportdateMapper.getExistDaysFromGuiDangDays(searchParam);
        return existsDays;
    }


    private List<YzcxHandleInfo> handleYZCXZYXXInfo(YZCXSearchParam param) {
        List<YzcxHandleInfo> rsList = new ArrayList<>();
        Map<String, String> requestparam = new HashMap();
        final Date start = param.getStart();
        String date00 = LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(start);
        String date23 = LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getEnd());
        requestparam.put("starte", date00);
        requestparam.put("end", date23);
        ZYXXModle data = YzcxHttpRequest.getZYXX(requestparam);
        /////////////
        final List<ZYXXzhuyuanbr> bingren = data.getBingren();//病人
        final List<ZYXXchuangwei> chuangwei = data.getChuangwei();//床位
        final List<ZYXXzhuanke> zhuangke = data.getZhuangke();//转科
        //1.病人情况
        Map<String, Long> brqkMap = bingren.stream().filter(item -> StringUtils.isNotBlank(item.getBrqk())).collect(Collectors.groupingBy(ZYXXzhuyuanbr::getBrqk, Collectors.counting()));
        //2.出院情况
        Map<String, Long> cyfsMap = bingren.stream().filter(item -> StringUtils.isNotBlank(item.getCyfs())).collect(Collectors.groupingBy(ZYXXzhuyuanbr::getCyfs, Collectors.counting()));
        //3.出院总数
        final Long chuyuanRenshu = bingren.stream().filter(item -> item.getCyrq() != null).collect(Collectors.counting());
        //4.入院总数
        final Integer ruyuanrenshu = bingren.size();
        //5.科室入院情况
        final Map<String, Long> keshiruyuanMap = bingren.stream().filter(item -> item.getRyrq() != null).collect(Collectors.groupingBy(ZYXXzhuyuanbr::getBrks, Collectors.counting()));
        //6.转出
        final Map<String, Long> zhuanchuKS = zhuangke.stream().collect(Collectors.groupingBy(ZYXXzhuanke::getZhuangchukeshi, Collectors.counting()));
        //7.转入
        final Map<String, Long> zhuanruKS = zhuangke.stream().collect(Collectors.groupingBy(ZYXXzhuanke::getZhuangrukeshi, Collectors.counting()));
        //8.科室床位-实占
        final Map<String, Integer> keshichuangwei_shizhan = chuangwei.stream().filter(item -> 1 == item.getKslb()).collect(Collectors.groupingBy(ZYXXchuangwei::getKs, Collectors.summingInt(ZYXXchuangwei::getShizhanshu)));
        //9.科室床位-开放
        final Map<String, Integer> keshichuangwei_kaifang = chuangwei.stream().filter(item -> 1 == item.getKslb()).collect(Collectors.groupingBy(ZYXXchuangwei::getKs, Collectors.summingInt(ZYXXchuangwei::getKaifangshu)));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(brqkMap, start, YZCXConstant.zhuyuan_brqk));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(cyfsMap, start, YZCXConstant.zhuyuan_cyfs));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.zhuyuan_chuyuanRenshuStr, YZCXConstant.zhuyuan_chuyuanRenshu, start, chuyuanRenshu.doubleValue()));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.zhuyuan_ruyuanrenshuStr, YZCXConstant.zhuyuan_ruyuanrenshu, start, ruyuanrenshu.doubleValue()));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(keshiruyuanMap, start, YZCXConstant.zhuyuan_keshiruyuan));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(zhuanchuKS, start, YZCXConstant.zhuyuan_zhuanchuKS));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(zhuanruKS, start, YZCXConstant.zhuyuan_zhuanruKS));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(keshichuangwei_shizhan, start, YZCXConstant.zhuyuan_keshishizhan));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(keshichuangwei_kaifang, start, YZCXConstant.zhuyuan_keshikaifang));
        //////////////
        return rsList;
    }

    @Override
    public void handlerZhuYuanXinxiRiGuiDang(YZCXSearchParam param) throws ParseException {
        //获取时间段里 每天的时间区间
        List<YZCXSearchParam> yzcxSearchParamByBetween = LdgDateUtil.getYZCXSearchParamByBetween(param.getStart(), param.getEnd());
        yzcxSearchParamByBetween.forEach(item -> {
            item.setHandletype(Arrays.asList(YZCXConstant.importType_zhuyuan));
            int zhuyuanImportcount = yzcxHandleImportdateMapper.selectImportState(item);
            if (zhuyuanImportcount == 0) {
                System.out.println(item + "住院处理.....");
                final List<YzcxHandleInfo> savelists = handleYZCXZYXXInfo(item);
                yzcxHandleInfoMapper.batchInsert(savelists);
                //保存处理日期
                yzcxHandleImportdateMapper.insertOneInfo(new YzcxHandleImportdate(item.getStart(),YZCXConstant.importType_zhuyuan));
            } else {
                System.out.println(item + "住院信息已处理！");
                return;
            }
        });
    }

    /**
     * 会诊信息
     * @param param
     * @return
     */
    private List<YzcxHandleInfo> handleYZCXHZXXInfo(YZCXSearchParam param){
        List<YzcxHandleInfo> rsList = new ArrayList<>();
        Map<String, String> requestparam = new HashMap();
        final Date start = param.getStart();
        String date00 = LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(start);
        String date23 = LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getEnd());
        requestparam.put("starte", date00);
        requestparam.put("end", date23);
        final HzxxModle huiZhenxx = YzcxHttpRequest.getHuiZhenxx(requestparam);
        Map<String, Long> jieshouKSAndSum = huiZhenxx.getJieshou().stream().collect(Collectors.groupingBy(HzxxInfo::getKs, Collectors.counting()));
        Map<String, Long> shenqingKSAndSum = huiZhenxx.getShenqing().stream().collect(Collectors.groupingBy(HzxxInfo::getSqks, Collectors.counting()));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(jieshouKSAndSum, start, YZCXConstant.huizhen_jieshou));
        rsList.addAll(YZCXscheduleMapToListHandler.handlerCommonData(shenqingKSAndSum, start, YZCXConstant.huizhen_shenqing));
        return rsList;
    }
    @Override
    public void handlerHuizhenRiGuiDang(YZCXSearchParam param) throws ParseException {
        //获取时间段里 每天的时间区间
        List<YZCXSearchParam> yzcxSearchParamByBetween = LdgDateUtil.getYZCXSearchParamByBetween(param.getStart(), param.getEnd());
        yzcxSearchParamByBetween.forEach(item -> {
            item.setHandletype(Arrays.asList(YZCXConstant.importType_huizhen));
            int zhuyuanImportcount = yzcxHandleImportdateMapper.selectImportState(item);
            if (zhuyuanImportcount == 0) {
                final List<YzcxHandleInfo> savelists = handleYZCXHZXXInfo(item);
                System.out.println(item + "会诊处理....."+savelists.size());
                yzcxHandleInfoMapper.batchInsert(savelists);
                //保存处理日期
                yzcxHandleImportdateMapper.insertOneInfo(new YzcxHandleImportdate(item.getStart(),YZCXConstant.importType_huizhen));
            } else {
                System.out.println(item + "会诊信息已处理！");
                return;
            }
        });
    }
    /**
     * 会诊信息
     * @param param
     * @return
     */
    private List<YzcxHandleInfo> handleYZCXYIJIXXInfo(YZCXSearchParam param){
        List<YzcxHandleInfo> rsList = new ArrayList<>();
        Map<String, String> requestparam = new HashMap();
        final Date start = param.getStart();
        String date00 = LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(start);
        String date23 = LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(param.getEnd());
        requestparam.put("starte", date00);
        requestparam.put("end", date23);
        final YIJIModle yiji = YzcxHttpRequest.getYIJI(requestparam);
        List<YiJiInfo> mzyiji=yiji.getMzyiji();
        List<YiJiInfo> zyyiji=yiji.getMzyiji();
        Integer menzhenCount = yiji.getMzyiji().size();
        Integer zhuyuanCount = yiji.getZyyiji().size();
        /////
        final BigDecimal menzhenHeji = mzyiji.stream().map(YiJiInfo::getHjje)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        final BigDecimal zhuyuanHeji = zyyiji.stream().map(YiJiInfo::getHjje)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        ////
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.yiji_menzhenStr, YZCXConstant.yiji_menzhen, start, menzhenCount.doubleValue()));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.yiji_zhuyuanStr, YZCXConstant.yiji_zhuyuan, start, zhuyuanCount.doubleValue()));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.yiji_menzhen_hejiStr, YZCXConstant.yiji_menzhen_heji, start, menzhenHeji.doubleValue()));
        rsList.add(YzcxHandleInfoFactory.createYzcxHandleInfo(YZCXConstant.yiji_zhuyuan_hejiStr, YZCXConstant.yiji_zhuyuan_heji, start, zhuyuanHeji.doubleValue()));
        return rsList;
    }
    @Override
    public void handlerYijiRiGuiDang(YZCXSearchParam param) throws ParseException {
        //获取时间段里 每天的时间区间
        List<YZCXSearchParam> yzcxSearchParamByBetween = LdgDateUtil.getYZCXSearchParamByBetween(param.getStart(), param.getEnd());
        yzcxSearchParamByBetween.forEach(item -> {
            item.setHandletype(Arrays.asList(YZCXConstant.importType_yiji));
            int zhuyuanImportcount = yzcxHandleImportdateMapper.selectImportState(item);
            if (zhuyuanImportcount == 0) {
                final List<YzcxHandleInfo> savelists = handleYZCXYIJIXXInfo(item);
                System.out.println(item + "医技处理....."+savelists.size());
                yzcxHandleInfoMapper.batchInsert(savelists);
                //保存处理日期
                yzcxHandleImportdateMapper.insertOneInfo(new YzcxHandleImportdate(item.getStart(),YZCXConstant.importType_yiji));
            } else {
                System.out.println(item + "医技信息已处理！");
                return;
            }
        });
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
}
