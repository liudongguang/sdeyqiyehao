package com.yzcx.impl.service;

import com.github.abel533.echarts.json.GsonOption;
import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.po.YzcxHandleInfoDay;
import com.yzcx.api.po.YzcxHandleInfoMonth;
import com.yzcx.api.service.YZCXCommonService;
import com.yzcx.api.service.YZCXZhuYuanSearchService;
import com.yzcx.api.util.EchartsBuilder;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.*;
import com.yzcx.impl.mapper.YzcxHandleInfoDayMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMonthMapper;
import com.yzcx.impl.service.handler.YzcxHandleInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class YZCXZhuYuanSearchServiceImpl implements YZCXZhuYuanSearchService {
    @Autowired
    private YzcxHandleInfoDayMapper yzcxHandleInfoDayMapper;
    @Autowired
    private YzcxHandleInfoMapper yzcxHandleInfoMapper;
    @Autowired
    private YzcxHandleInfoMonthMapper yzcxHandleInfoMonthMapper;
    @Autowired
    private YZCXCommonService yzcxCommonService;

    @Override
    public ZyxxIndex getIndexZhuYuanForDay(YZCXSearchParam param) throws ParseException {
        List<Integer> zyxxType = Arrays.asList(YZCXConstant.zhuyuan_brqk, YZCXConstant.zhuyuan_cyfs,
                YZCXConstant.zhuyuan_chuyuanRenshu, YZCXConstant.zhuyuan_ruyuanrenshu,
                YZCXConstant.zhuyuan_zhuanchuKS, YZCXConstant.zhuyuan_zhuanruKS,YZCXConstant.zhuyuan_zaiyuan);
        param.setHandletype(zyxxType);
        final List<YzcxHandleInfoDay> yzcxHandleInfoDays = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        final Map<Integer, List<YzcxHandleInfoDay>> zhuyuanMap = yzcxHandleInfoDays.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getHandletype));
        List<YzcxHandleInfoDay> brqk = zhuyuanMap.get(YZCXConstant.zhuyuan_brqk);
        List<YzcxHandleInfoDay> cyfs = zhuyuanMap.get(YZCXConstant.zhuyuan_cyfs);
        List<YzcxHandleInfoDay> chuyuanrs = zhuyuanMap.get(YZCXConstant.zhuyuan_chuyuanRenshu);
        List<YzcxHandleInfoDay> ruyuanrs = zhuyuanMap.get(YZCXConstant.zhuyuan_ruyuanrenshu);
        List<YzcxHandleInfoDay> zhuanchuks = zhuyuanMap.get(YZCXConstant.zhuyuan_zhuanchuKS);
        List<YzcxHandleInfoDay> zhuanruks = zhuyuanMap.get(YZCXConstant.zhuyuan_zhuanruKS);
        List<YzcxHandleInfoDay> zaiyuan = zhuyuanMap.get(YZCXConstant.zhuyuan_zaiyuan);
        ZyxxIndex index = new ZyxxIndex();
        index.setParam(param);
        if (chuyuanrs != null) {
            index.setChuyuan(chuyuanrs.stream().collect(Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        }
        if (ruyuanrs != null) {
            index.setRuyuan(ruyuanrs.stream().collect(Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        }
        if (brqk != null) {
            Map<String, Double> brqkMap = brqk.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName,Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
            Double bws = brqkMap.get(YZCXConstant.zhuyuan_brqk_bingwei);//病危
            Double bzs = brqkMap.get(YZCXConstant.zhuyuan_brqk_bingzhong);//病重数
            if (bws == null) {
                bws = 0d;
            }
            if (bzs == null) {
                bzs = 0d;
            }
            index.setWeizhong(bws + bzs);
        }
        if (cyfs != null) {
            Map<String, Double> cyfsMap = cyfs.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName,Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
            Double sws = cyfsMap.get(YZCXConstant.zhuyuan_cyfs_siwang);
            index.setSiwang(sws);
        }
        if (zhuanchuks != null) {
            index.setZhuanchu(zhuanchuks.stream().collect(Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        }
        if (zhuanruks != null) {
            index.setZhuanru(zhuanruks.stream().collect(Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        }
        /////////////////////////////////////////////////////////////////获取前一天的床位信息
        YZCXSearchParam beforeDayparam= YZCXControllerUtil.getBeforeDayByNum(1);
        beforeDayparam.setHandletype(Arrays.asList(YZCXConstant.zhuyuan_keshishizhan,YZCXConstant.zhuyuan_keshikaifang));
        final List<YzcxHandleInfo> chuangweiList = yzcxHandleInfoMapper.selectByDateAndType(beforeDayparam);
        final Map<Integer, Double> keshiChuangweiMap = chuangweiList.stream().collect(Collectors.groupingBy(YzcxHandleInfo::getHandletype, Collectors.summingDouble(YzcxHandleInfo::getCount)));
        Double shizhan = keshiChuangweiMap.get(YZCXConstant.zhuyuan_keshishizhan);
        Double kaifang = keshiChuangweiMap.get(YZCXConstant.zhuyuan_keshikaifang);
        index.setShizhan(shizhan);
        index.setKaifang(kaifang);
        if(shizhan!=null&&shizhan!=0&&kaifang!=null&&kaifang!=0){
            index.setCwshiyonglv(shizhan/kaifang);
        }
        //////////////////////////////在院
        if(zaiyuan!=null){
            YzcxHandleInfoDay yzcxHandleInfoDay = zaiyuan.get(0);
            index.setZaiyuanNum(yzcxHandleInfoDay.getCount());
        }
        return index;
    }

    @Override
    public Map<String, Object> getIndexChart(YZCXSearchParam param) throws ParseException {
        List<Integer> zyxxType = Arrays.asList(YZCXConstant.zhuyuan_keshiruyuan);
        param.setHandletype(zyxxType);
        final List<YzcxHandleInfoDay> keshiruyuanList = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        Map<String, Object> rs = new HashMap<>();
        if (keshiruyuanList != null) {
            Map<String, Double> ruyuanMap = keshiruyuanList.stream().map(item -> {
                YzcxHandleInfoDayExt yzcxHandleInfoDayExt = new YzcxHandleInfoDayExt();
                yzcxHandleInfoDayExt.setHandledateStr(LdgDateUtil.getHourNum(item.getHandledate()).toString());
                yzcxHandleInfoDayExt.setCount(item.getCount());
                return yzcxHandleInfoDayExt;
            }).collect(Collectors.groupingBy(YzcxHandleInfoDayExt::getHandledateStr, Collectors.summingDouble(YzcxHandleInfoDayExt::getCount)));
            final List<String> hoursList = YZCXConstant.hoursList;
            Integer maxHourNum = LdgDateUtil.getHourNum(keshiruyuanList.get(keshiruyuanList.size() - 1).getHandledate());
            List<String> category = new ArrayList<>();
            List<Number> zhexianNum = new ArrayList<>();
            for (int i = 0; i < hoursList.size(); i++) {
                if (i > maxHourNum) {
                    break;
                }
                String hourNum = hoursList.get(i);
                Double sumNum = ruyuanMap.get(hourNum);
                if (sumNum == null) {
                    sumNum = 0d;
                }
                category.add(hourNum);
                zhexianNum.add(sumNum);
            }
            Map<String, List<Number>> nameAndData = new HashMap<>();
            nameAndData.put("入院人数", zhexianNum);
            //////////////////////////////////科室入院排名
            final Map<String, Double> ksAndRyNum = keshiruyuanList.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName, Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
            List<YzcxHandleInfoDay> ksAndSumRenshuList = new ArrayList<>();
            ksAndRyNum.forEach((ksname, sumRenshu) -> {
                YzcxHandleInfoDay newObj = new YzcxHandleInfoDay();
                newObj.setName(ksname);
                newObj.setCount(sumRenshu);
                ksAndSumRenshuList.add(newObj);
            });
            List<YzcxHandleInfoDay> qianshiKsRuYuan = ksAndSumRenshuList.stream().sorted(Comparator.comparing(YzcxHandleInfoDay::getCount).reversed()).limit(10).collect(Collectors.toList());
            List<String> category_ruyuan = new ArrayList<>();
            List<Number> ruyuanData = new ArrayList<>();
            qianshiKsRuYuan.forEach(item -> {
                category_ruyuan.add(item.getName());
                ruyuanData.add(item.getCount());
            });
            Map<String, List<Number>> nameAndData_ksryqs = new HashMap<>();
            nameAndData_ksryqs.put("入院人数", ruyuanData);
            /////////////////////////////////////////////////////////////////////////床位信息
            YZCXSearchParam beforeDayparam= YZCXControllerUtil.getBeforeDayByNum(1);
            beforeDayparam.setHandletype(Arrays.asList(YZCXConstant.zhuyuan_keshishizhan,YZCXConstant.zhuyuan_keshikaifang));
            final List<YzcxHandleInfo> chuangweiList = yzcxHandleInfoMapper.selectByDateAndType(beforeDayparam);
            final Map<String, Map<Integer, Double>> keshiType = chuangweiList.stream().collect(Collectors.groupingBy(YzcxHandleInfo::getName, Collectors.groupingBy(YzcxHandleInfo::getHandletype, Collectors.summingDouble(YzcxHandleInfo::getCount))));
            List<ZyxxKeshiChuanwei> cwList=new ArrayList<>();
            keshiType.forEach((keshiName,typeAndSum)->{
                Double shizhan = typeAndSum.get(YZCXConstant.zhuyuan_keshishizhan);
                Double kaifang=typeAndSum.get(YZCXConstant.zhuyuan_keshikaifang);
                ZyxxKeshiChuanwei zyxxKeshiChuanwei=new ZyxxKeshiChuanwei();
                zyxxKeshiChuanwei.setShizhan(shizhan);
                zyxxKeshiChuanwei.setKaifang(kaifang);
                zyxxKeshiChuanwei.setKsname(keshiName);
                if(shizhan!=null&&shizhan!=0&&kaifang!=null&&kaifang!=0){
                    zyxxKeshiChuanwei.setCwshiyonglv(shizhan/kaifang);
                }
                cwList.add(zyxxKeshiChuanwei);
            });

            List<ZyxxKeshiChuanwei> shiyonglvQianshi = cwList.stream().filter(item -> item.getCwshiyonglv() != null ? true : false).sorted(Comparator.comparing(ZyxxKeshiChuanwei::getCwshiyonglv).reversed()).limit(10).collect(Collectors.toList());
            List<String> category_chuangwei = new ArrayList<>();
            List<Number> ksshizhan = new ArrayList<>();
            List<Number> kskaifang = new ArrayList<>();
            shiyonglvQianshi.forEach(item->{
                category_chuangwei.add(item.getKsname());
                ksshizhan.add(item.getShizhan());
                kskaifang.add(item.getKaifang());
            });

            Map<String, List<Number>> nameAndData_chuangweisyl = new HashMap<>();
            nameAndData_chuangweisyl.put("实占床位", ksshizhan);
            nameAndData_chuangweisyl.put("开放床位", kskaifang);
            ////////////////////
            GsonOption echartOption = EchartsBuilder.buildEchartOption_line(" ", "入院人次波动图", category, nameAndData);
            GsonOption echartOption_ruyuanqianshi = EchartsBuilder.buildEchartOption_bar(" ", " ", category_ruyuan, nameAndData_ksryqs, false);
            GsonOption echartOption_chuangweishu = EchartsBuilder.buildEchartOption_bar(" ", " ", category_chuangwei, nameAndData_chuangweisyl, false);
            rs.put("echartOption", echartOption.toString());
            rs.put("echartOption_ruyuanqianshi", echartOption_ruyuanqianshi.toString());
            rs.put("echartOption_chuangweishu", echartOption_chuangweishu.toString());
        }
        return rs;
    }

    @Override
    public ZyxxIndex getIndexZhuYuanForYue(YZCXSearchParam param) throws ParseException {
        List<Integer> zyxxType = Arrays.asList(YZCXConstant.zhuyuan_brqk, YZCXConstant.zhuyuan_cyfs,
                YZCXConstant.zhuyuan_chuyuanRenshu, YZCXConstant.zhuyuan_ruyuanrenshu,
                YZCXConstant.zhuyuan_zhuanchuKS, YZCXConstant.zhuyuan_zhuanruKS);
        param.setHandletype(zyxxType);
        List<YzcxHandleInfoMonth> ksfeiyongList = yzcxCommonService.getMonthDataByParam(param);
        final Map<Integer, List<YzcxHandleInfoMonth>> zhuyuanMap = ksfeiyongList.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getHandletype));
        List<YzcxHandleInfoMonth> brqk = zhuyuanMap.get(YZCXConstant.zhuyuan_brqk);
        List<YzcxHandleInfoMonth> cyfs = zhuyuanMap.get(YZCXConstant.zhuyuan_cyfs);
        List<YzcxHandleInfoMonth> chuyuanrs = zhuyuanMap.get(YZCXConstant.zhuyuan_chuyuanRenshu);
        List<YzcxHandleInfoMonth> ruyuanrs = zhuyuanMap.get(YZCXConstant.zhuyuan_ruyuanrenshu);
        List<YzcxHandleInfoMonth> zhuanchuks = zhuyuanMap.get(YZCXConstant.zhuyuan_zhuanchuKS);
        List<YzcxHandleInfoMonth> zhuanruks = zhuyuanMap.get(YZCXConstant.zhuyuan_zhuanruKS);
        ZyxxIndex index = new ZyxxIndex();
        index.setParam(param);
        if (chuyuanrs != null) {
            index.setChuyuan(chuyuanrs.stream().collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        }
        if (ruyuanrs != null) {
            index.setRuyuan(ruyuanrs.stream().collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        }
        if (brqk != null) {
            Map<String, Double> brqkMap = brqk.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getName,Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
            Double bws = brqkMap.get(YZCXConstant.zhuyuan_brqk_bingwei);//病危
            Double bzs = brqkMap.get(YZCXConstant.zhuyuan_brqk_bingzhong);//病重数
            if (bws == null) {
                bws = 0d;
            }
            if (bzs == null) {
                bzs = 0d;
            }
            index.setWeizhong(bws + bzs);
        }
        if (cyfs != null) {
            Map<String, Double> cyfsMap = cyfs.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getName,Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
            Double sws = cyfsMap.get(YZCXConstant.zhuyuan_cyfs_siwang);
            index.setSiwang(sws);
        }
        if (zhuanchuks != null) {
            index.setZhuanchu(zhuanchuks.stream().collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        }
        if (zhuanruks != null) {
            index.setZhuanru(zhuanruks.stream().collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        }
        /////////////////////////////////////////////////////////////////床位
        param.setHandletype(Arrays.asList(YZCXConstant.zhuyuan_keshishizhan,YZCXConstant.zhuyuan_keshikaifang));
        final List<YzcxHandleInfoMonth> chuangweiList =yzcxCommonService.getMonthDataByParam(param);
        final Map<Integer, Double> keshiChuangweiMap = chuangweiList.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getHandletype, Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        Double shizhan = keshiChuangweiMap.get(YZCXConstant.zhuyuan_keshishizhan);
        Double kaifang = keshiChuangweiMap.get(YZCXConstant.zhuyuan_keshikaifang);
        index.setShizhan(shizhan);
        index.setKaifang(kaifang);
        if(shizhan!=null&&shizhan!=0&&kaifang!=null&&kaifang!=0){
            index.setCwshiyonglv(shizhan/kaifang);
        }
        return index;
    }

    @Override
    public Map<String, Object> zhuyuan_yue_chart(YZCXSearchParam yzcxSearchParam) throws ParseException {
        List<Integer> zyxxType = Arrays.asList(YZCXConstant.zhuyuan_keshiruyuan);
        yzcxSearchParam.setHandletype(zyxxType);
        List<YzcxHandleInfo> ksruyuanList = yzcxHandleInfoMapper.selectByDateAndType(yzcxSearchParam);
        LinkedHashMap<String, Double> everyDayRuYuan = ksruyuanList.stream().map(item -> {
            return YzcxHandleInfoFactory.createYzcxHandleInfoExtForEveryDay(item.getHandledate(), item.getCount(), item.getName());
        }).collect(Collectors.groupingBy(YzcxHandleInfoExt::getHandledateStr, LinkedHashMap::new, Collectors.summingDouble(YzcxHandleInfoExt::getCount)));
        Map<String, List<Number>> nameAndData_ruyuan = new HashMap<>();
        List<String> category_ruyuan = new ArrayList<>();
        List<Number> ksshizhan = new ArrayList<>();
        everyDayRuYuan.forEach((dateStr,SumNumber)->{
            ksshizhan.add(SumNumber);
            category_ruyuan.add(dateStr);
        });
        nameAndData_ruyuan.put("入院情况", ksshizhan);
        ////////////////////////////////////////////////
        List<YzcxHandleInfoMonth> keshiruyuanList = yzcxCommonService.getMonthDataByParam(yzcxSearchParam);
        final Map<String, Double> ksAndRyNum = keshiruyuanList.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getName, Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        List<YzcxHandleInfoDay> ksAndSumRenshuList = new ArrayList<>();
        ksAndRyNum.forEach((ksname, sumRenshu) -> {
            YzcxHandleInfoDay newObj = new YzcxHandleInfoDay();
            newObj.setName(ksname);
            newObj.setCount(sumRenshu);
            ksAndSumRenshuList.add(newObj);
        });
        List<YzcxHandleInfoDay> qianshiKsRuYuan = ksAndSumRenshuList.stream().sorted(Comparator.comparing(YzcxHandleInfoDay::getCount).reversed()).limit(10).collect(Collectors.toList());
        List<String> category_ruyuanks = new ArrayList<>();
        List<Number> ruyuanData = new ArrayList<>();
        qianshiKsRuYuan.forEach(item -> {
            category_ruyuanks.add(item.getName());
            ruyuanData.add(item.getCount());
        });
        Map<String, List<Number>> nameAndData_ksryqs = new HashMap<>();
        nameAndData_ksryqs.put("科室入院人数", ruyuanData);
        ////////////////////////////////////////////////////////////////////同期分析
        final Double jinnianSum = keshiruyuanList.stream().collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount));
        final YZCXSearchParam qunianParam = YZCXControllerUtil.getSearchParamBeforeOneYear(yzcxSearchParam);
        qunianParam.setHandletype(zyxxType);
        final List<YzcxHandleInfoMonth> qunianList = yzcxCommonService.getMonthDataByParam(qunianParam);
        final Double qunianSum = qunianList.stream().collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount));
        List<String> category_ruyuanks_tongqi = new ArrayList<>();
        category_ruyuanks_tongqi.add(LdgDateUtil.get_zhongwen_yyyyMM(qunianParam.getStart()));
        category_ruyuanks_tongqi.add(LdgDateUtil.get_zhongwen_yyyyMM(yzcxSearchParam.getStart()));
        List<Number> ruyuanData_tongqi_qunian = new ArrayList<>();
        ruyuanData_tongqi_qunian.add(qunianSum);
        ruyuanData_tongqi_qunian.add(jinnianSum);
        Map<String, List<Number>> nameAndData_ksryqs_tongqi = new HashMap<>();
        nameAndData_ksryqs_tongqi.put("入院人数", ruyuanData_tongqi_qunian);

        ///////////////////
        GsonOption echartOption_ruyuan = EchartsBuilder.buildEchartOption_bar(" ", "日入院人次", category_ruyuan, nameAndData_ruyuan, false);
        GsonOption echartOption_ruyuanks = EchartsBuilder.buildEchartOption_bar(" ", " ", category_ruyuanks, nameAndData_ksryqs, false);
        GsonOption echartOption_ruyuanks_tongqi = EchartsBuilder.buildEchartOption_bar(" ", " ", category_ruyuanks_tongqi, nameAndData_ksryqs_tongqi, true);
        Map<String, Object> rs = new HashMap<>();
        rs.put("echartOption", echartOption_ruyuan.toString());
        rs.put("echartOption_ruyuanks", echartOption_ruyuanks.toString());
        rs.put("echartOption_ruyuanks_tongqi", echartOption_ruyuanks_tongqi.toString());
        return rs;
    }

    @Override
    public Map<String, Object> zhuyuan_year(YZCXSearchParam yzcxSearchParam) throws ParseException {
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.zhuyuan_chuyuanRenshu));
        List<YzcxHandleInfoMonth> zhuyuanYearList = yzcxHandleInfoMonthMapper.selectByDateAndType(yzcxSearchParam);
        Map<String, Double> zhuyuanYearMap = zhuyuanYearList.stream().map(item -> {
            YzcxHandleInfoMonthExt yzcxHandleInfoMonthExt = new YzcxHandleInfoMonthExt();
            yzcxHandleInfoMonthExt.setSumNum(item.getCount());
            yzcxHandleInfoMonthExt.setMonthStr(LdgDateUtil.getMonthHanzi(item.getHandledate()));
            return yzcxHandleInfoMonthExt;
        }).collect(Collectors.toMap(YzcxHandleInfoMonthExt::getMonthStr, YzcxHandleInfoMonthExt::getSumNum));
        List<String> category_ruyuan = new ArrayList<>();
        List<Number> ksshizhan = new ArrayList<>();
        Arrays.asList(YZCXConstant.months).forEach(month -> {
            Double menzhenSum = zhuyuanYearMap.get(month);
            category_ruyuan.add(month);
            ksshizhan.add(menzhenSum);
        });

        Map<String, List<Number>> nameAndData_ruyuan = new HashMap<>();
        nameAndData_ruyuan.put("入院人数", ksshizhan);
        GsonOption echartOption_ruyuan = EchartsBuilder.buildEchartOption_bar(" ", "月入院人次", category_ruyuan, nameAndData_ruyuan, false);
        ///////////////////////////////////////////////////////////////////////////////////////
        List<String> category_ruyuan_tongqi = new ArrayList<>();
        String currentDateStr = LdgDateUtil.getYearHanzi(yzcxSearchParam.getStart());
        List<YzcxHandleInfoMonth> currentlist = yzcxHandleInfoMonthMapper.selectByDateAndType(yzcxSearchParam);
        yzcxSearchParam=YZCXControllerUtil.getSearchParamBeforeOneYear(yzcxSearchParam);//获取前一年同月日期
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.zhuyuan_chuyuanRenshu));
        String qunianDateStr = LdgDateUtil.getYearHanzi(yzcxSearchParam.getStart());
        List<YzcxHandleInfoMonth> qunianlist = yzcxHandleInfoMonthMapper.selectByDateAndType(yzcxSearchParam);
        if (qunianlist.size() == 0) {
            return null;
        }
        category_ruyuan_tongqi.add(currentDateStr);
        category_ruyuan_tongqi.add(qunianDateStr);
        List<Number> ruyuanData_tongqi_qunian = new ArrayList<>();
        ruyuanData_tongqi_qunian.add(currentlist.stream().collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        ruyuanData_tongqi_qunian.add(qunianlist.stream().collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        Map<String, List<Number>> nameAndData_ryqs_tongqi = new HashMap<>();
        nameAndData_ryqs_tongqi.put("入院人数", ruyuanData_tongqi_qunian);
        GsonOption echartOption_ruyuan_tongqi = EchartsBuilder.buildEchartOption_bar(" ", " ", category_ruyuan_tongqi, nameAndData_ryqs_tongqi, true);
        //////////////////////
        Map<String, Object> rs = new HashMap<>();
        rs.put("echartOption", echartOption_ruyuan.toString());
        rs.put("echartOption_ruyuan_tongqi", echartOption_ruyuan_tongqi.toString());
        return rs;
    }
}
