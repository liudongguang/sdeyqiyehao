package com.yzcx.impl.service;

import com.github.abel533.echarts.data.PieData;
import com.yzcx.api.bo.YzcxHandleInfo_FeiYong;
import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.po.YzcxHandleInfoMonth;
import com.yzcx.api.service.YZCXCommonService;
import com.yzcx.api.service.YZCXFeiYongSearchService;
import com.yzcx.api.util.*;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.highchat.bar.HighchartsConfig_bar;
import com.yzcx.api.vo.highchat.pie.HighchartsConfig_pie2;
import com.yzcx.api.vo.yzcxdisplay.FeiYongHuiZong;
import com.yzcx.api.vo.yzcxdisplay.FeiYongIndexData;
import com.yzcx.api.vo.yzcxdisplay.YzcxHandleInfoExt;
import com.yzcx.impl.mapper.YzcxHandleInfoMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMonthMapper;
import com.yzcx.impl.service.handler.YzcxHandleInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class YZCXFeiYongSearchServiceImpl implements YZCXFeiYongSearchService {
    @Autowired
    private YzcxHandleInfoMapper yzcxHandleInfoMapper;
    @Autowired
    private YzcxHandleInfoMonthMapper yzcxHandleInfoMonthMapper;
    @Autowired
    private YZCXCommonService yzcxCommonService;

    @Override
    public Map<String, Object> getIndexChart(YZCXSearchParam param) {
        Map<String, Object> rs = new HashMap<>();
        param.setHandletype(Arrays.asList(YZCXConstant.feiyong));
        List<YzcxHandleInfo> feiyongList = yzcxHandleInfoMapper.selectByDateAndType(param);
        Map<String, Double> zhuyuanMenzhenMap = feiyongList.stream().collect(Collectors.groupingBy(YzcxHandleInfo::getName, Collectors.summingDouble(YzcxHandleInfo::getCount)));
        Double zhuyuanYiLiao = zhuyuanMenzhenMap.get(YZCXConstant.zhuyuan_yiliao);
        Double zhuyuanYao = zhuyuanMenzhenMap.get(YZCXConstant.zhuyuan_yaofei);
        Double zhuyuanQiTa = zhuyuanMenzhenMap.get(YZCXConstant.zhuyuan_qita);
        Double menzhenYiLiao = zhuyuanMenzhenMap.get(YZCXConstant.menzhen_yiliao);
        Double menzhenYao = zhuyuanMenzhenMap.get(YZCXConstant.menzhen_yaofei);
        Double menzhenQiTa = zhuyuanMenzhenMap.get(YZCXConstant.menzhen_qita);
        if (zhuyuanYiLiao == null || zhuyuanYao == null || zhuyuanQiTa == null || menzhenYiLiao == null || menzhenYao == null || menzhenQiTa == null) {
            return rs;
        }
        /////////////////////////////////////////////////////////////
        //Map<String,List<Number>> zysrMap=new HashMap<>();
        //zysrMap.put("住院收入", Arrays.asList(zhuyuanYiLiao, zhuyuanYao, zhuyuanQiTa));
        //HighchartsConfig_bar zhuyuanChart = HighChartBuilder.builderHighchartsConfig_bar(Arrays.asList(YZCXConstant.zhuyuan_yiliao, YZCXConstant.zhuyuan_yaofei, YZCXConstant.zhuyuan_qita), "单位：元", zysrMap);
        ///////////////////////////////////////////////////////////////////
        //Map<String,List<Number>> mzsrMap=new HashMap<>();
        //mzsrMap.put("门诊收入", Arrays.asList(menzhenYiLiao, menzhenYao, menzhenQiTa));
        //HighchartsConfig_bar menzhenChart = HighChartBuilder.builderHighchartsConfig_bar(Arrays.asList(YZCXConstant.menzhen_yiliao, YZCXConstant.menzhen_yaofei, YZCXConstant.menzhen_qita), "单位：元", mzsrMap);
        ////////////////////////////////////////////////////////
        param.setHandletype(Arrays.asList(YZCXConstant.feiyong_zhuyuan_yaofei, YZCXConstant.feiyong_zhuyuan_qitafei, YZCXConstant.feiyong_zhuyuan_yiliaofei, YZCXConstant.feiyong_menzhen_yaofei, YZCXConstant.feiyong_menzhen_qitafei, YZCXConstant.feiyong_menzhen_yiliaofei));
        List<YzcxHandleInfo> ksfeiyongList = yzcxHandleInfoMapper.selectByDateAndType(param);
        Map<String, Map<Integer, Double>> ksTypeNum = ksfeiyongList.stream().collect(Collectors.groupingBy(YzcxHandleInfo::getName, Collectors.groupingBy(YzcxHandleInfo::getHandletype, Collectors.summingDouble(YzcxHandleInfo::getCount))));
        List<YzcxHandleInfo_FeiYong> ksFeiYongInfoList = YZCXscheduleMapToListHandler.getKSFeiyong(ksTypeNum);
        Collections.sort(ksFeiYongInfoList, Comparator.comparingDouble(YzcxHandleInfo_FeiYong::getZhuyuanZong).reversed());
        List<YzcxHandleInfo_FeiYong> zhuyuanqianshi = ksFeiYongInfoList.stream().limit(10).collect(Collectors.toList());
        Map<String, List<Number>> zhuyuanksfeiyongMap = new HashMap<>();
        zhuyuanksfeiyongMap.put(YZCXConstant.zhuyuan_yiliao, zhuyuanqianshi.stream().map(item -> {
            return item.getZhuyuanyiliaofei();
        }).collect(Collectors.toList()));
        zhuyuanksfeiyongMap.put(YZCXConstant.zhuyuan_yaofei, zhuyuanqianshi.stream().map(item -> {
            return item.getZhuyuanyaofei();
        }).collect(Collectors.toList()));
        zhuyuanksfeiyongMap.put(YZCXConstant.zhuyuan_qita, zhuyuanqianshi.stream().map(item -> {
            return item.getZhuyuanqitafei();
        }).collect(Collectors.toList()));
        HighchartsConfig_bar kszhuyuanpaimingChart = HighChartBuilder.builderHighchartsConfig_bar(zhuyuanqianshi.stream().map(item -> {
            return item.getKsname();
        }).collect(Collectors.toList()), "单位：元", zhuyuanksfeiyongMap, true);
        Collections.sort(ksFeiYongInfoList, Comparator.comparingDouble(YzcxHandleInfo_FeiYong::getMenzhenZong).reversed());
        List<YzcxHandleInfo_FeiYong> menzhenqianshi = ksFeiYongInfoList.stream().limit(10).collect(Collectors.toList());
        Map<String, List<Number>> menzhenksfeiyongMap = new HashMap<>();
        menzhenksfeiyongMap.put(YZCXConstant.menzhen_yiliao, menzhenqianshi.stream().map(item -> {
            return item.getMenzhenyiliaofei();
        }).collect(Collectors.toList()));
        menzhenksfeiyongMap.put(YZCXConstant.menzhen_yaofei, menzhenqianshi.stream().map(item -> {
            return item.getMenzhenyaofei();
        }).collect(Collectors.toList()));
        menzhenksfeiyongMap.put(YZCXConstant.menzhen_qita, menzhenqianshi.stream().map(item -> {
            return item.getMenzhenqitafei();
        }).collect(Collectors.toList()));
        HighchartsConfig_bar ksmenzhenpaimingChart = HighChartBuilder.builderHighchartsConfig_bar(menzhenqianshi.stream().map(item -> item.getKsname()).collect(Collectors.toList()), "单位：元", menzhenksfeiyongMap, true);
        ////////////////////////////////////////////////
        double zhuyuan = zhuyuanYiLiao + zhuyuanQiTa + zhuyuanYao;
        double menzhen = menzhenYiLiao + menzhenQiTa + menzhenYao;
//        Map<String,Double> mzzyData=new HashMap<>();
//        Map<String,Map<String,Double>> mzzyZhanBi=new HashMap<>();
//        mzzyData.put("门诊",menzhen);
//        mzzyData.put("住院",zhuyuan);
//        mzzyZhanBi.put("门诊，住院收入占比（昨日）",mzzyData);
//        HighchartsConfig_pie2 menzhenZhuYuanPie=HighChartBuilder.builderHighchartsConfig_pie("门诊，住院收入占比（昨日）",mzzyZhanBi);
        List<PieData> menzhenZhuYuanPie = new ArrayList<>();
        menzhenZhuYuanPie.add(new PieData("门诊", menzhen));
        menzhenZhuYuanPie.add(new PieData("住院", zhuyuan));

        ///////////
        double qita = zhuyuanQiTa + menzhenQiTa;
        double yiliao = zhuyuanYiLiao + menzhenYiLiao;
        double yaofei = zhuyuanYao + menzhenYao;
//        Map<String, Double> ylypqtData = new HashMap<>();
//        Map<String, Map<String, Double>> ylypqtZhanBi = new HashMap<>();
//        ylypqtData.put("医疗", yiliao);
//        ylypqtData.put("药品", yaofei);
//        ylypqtData.put("其他", qita);
//        ylypqtZhanBi.put("全院收入类别占比（昨日）", ylypqtData);
//        HighchartsConfig_pie2 ylypqtYuanPie = HighChartBuilder.builderHighchartsConfig_pie("全院收入类别占比（昨日）", ylypqtZhanBi);
        List<PieData> ylypqtYuanPie = new ArrayList<>();
        ylypqtYuanPie.add(new PieData("医疗", yiliao));
        ylypqtYuanPie.add(new PieData("药品", yaofei));
        ylypqtYuanPie.add(new PieData("其他", qita));
        ////////
        FeiYongIndexData feiYongIndexData = new FeiYongIndexData();
        feiYongIndexData.setMenzhenzong(menzhen);
        feiYongIndexData.setZhuyuanzong(zhuyuan);
        feiYongIndexData.setQita(qita);
        feiYongIndexData.setYiliao(yiliao);
        feiYongIndexData.setYaopin(yaofei);
        ///////
        rs.put("zhuyuan", Arrays.asList(zhuyuanYiLiao, zhuyuanYao, zhuyuanQiTa));
        rs.put("menzhen", Arrays.asList(menzhenYiLiao, menzhenYao, menzhenQiTa));
        rs.put("kszhuyuan", kszhuyuanpaimingChart);
        rs.put("ksmenzhen", ksmenzhenpaimingChart);
        rs.put("zhuyuanPie_mzzybi", menzhenZhuYuanPie);
        rs.put("zhuyuanPie_ylypqt", ylypqtYuanPie);
        rs.put("dataNum", feiYongIndexData);
        return rs;
    }

    @Override
    public FeiYongHuiZong getIndexFeiYongZong(YZCXSearchParam param) throws ParseException {
        param.setHandletype(Arrays.asList(YZCXConstant.feiyong));
        List<YzcxHandleInfo> feiyongList = yzcxHandleInfoMapper.selectByDateAndType(param);
        Double zhuyuanZong = feiyongList.stream().filter(obj -> {
            if (obj.getName().indexOf("住院") != -1) {
                return true;
            }
            return false;
        }).collect(Collectors.summingDouble(YzcxHandleInfo::getCount));
        Double menzhenZong = feiyongList.stream().filter(obj -> {
            if (obj.getName().indexOf("门诊") != -1) {
                return true;
            }
            return false;
        }).collect(Collectors.summingDouble(YzcxHandleInfo::getCount));
        FeiYongHuiZong feiYongHuiZong = new FeiYongHuiZong();
        feiYongHuiZong.setMenzhenzong(menzhenZong);
        feiYongHuiZong.setZhuyuanzong(zhuyuanZong);
        ////获取前天数据
        param = YZCXControllerUtil.getBeforeDayByNum(2);
        param.setHandletype(Arrays.asList(YZCXConstant.feiyong));
        List<YzcxHandleInfo> feiyongList_qiantian = yzcxHandleInfoMapper.selectByDateAndType(param);
        Double qiantianFeiYong = feiyongList_qiantian.stream().collect(Collectors.summingDouble(YzcxHandleInfo::getCount));
        double feiyonglv = ((menzhenZong + zhuyuanZong - qiantianFeiYong) / qiantianFeiYong);
        feiYongHuiZong.setQianribaifenbi(feiyonglv);
        return feiYongHuiZong;
    }

    @Override
    public Map<String, Object> getfeiyong_yue(YZCXSearchParam yzcxSearchParam) {
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.feiyong));
        List<YzcxHandleInfo> yzcxHandleInfos = yzcxHandleInfoMapper.selectByDateAndType(yzcxSearchParam);
        ////每天信息
        LinkedHashMap<String, Double> everyDayFeiYong = yzcxHandleInfos.stream().map(item -> {
            return YzcxHandleInfoFactory.createYzcxHandleInfoExtForEveryDay(item.getHandledate(), item.getCount(), item.getName());
        }).collect(Collectors.groupingBy(YzcxHandleInfoExt::getHandledateStr, LinkedHashMap::new, Collectors.summingDouble(YzcxHandleInfoExt::getCount)));
        List<String> categoriesDays = new ArrayList<>();
        List<Number> series_barDate = new ArrayList<>();
        everyDayFeiYong.forEach((date, jine) -> {
            categoriesDays.add(date);
            series_barDate.add(jine);
        });
        Map<String, List<Number>> everydayDateMap = new HashMap<>();
        everydayDateMap.put("每日总费用", series_barDate);
        HighchartsConfig_bar meitianChart = HighChartBuilder.builderHighchartsConfig_bar(categoriesDays, "单位：元", everydayDateMap, true);
        ///科室费用
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.feiyong_zhuyuan_yaofei, YZCXConstant.feiyong_zhuyuan_qitafei, YZCXConstant.feiyong_zhuyuan_yiliaofei, YZCXConstant.feiyong_menzhen_yaofei, YZCXConstant.feiyong_menzhen_qitafei, YZCXConstant.feiyong_menzhen_yiliaofei));
        List<YzcxHandleInfoMonth> ksfeiyongList = yzcxCommonService.getMonthDataByParam(yzcxSearchParam);
        Map<String, Map<Integer, Double>> ksTypeNum = ksfeiyongList.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getName, Collectors.groupingBy(YzcxHandleInfoMonth::getHandletype, Collectors.summingDouble(YzcxHandleInfoMonth::getCount))));
        List<YzcxHandleInfo_FeiYong> ksFeiYongInfoList = YZCXscheduleMapToListHandler.getKSFeiyong(ksTypeNum);
        Collections.sort(ksFeiYongInfoList, Comparator.comparingDouble(YzcxHandleInfo_FeiYong::getZhuyuanZong).reversed());
        List<YzcxHandleInfo_FeiYong> zhuyuanqianshi = ksFeiYongInfoList.stream().limit(10).collect(Collectors.toList());
        Map<String, List<Number>> zhuyuanksfeiyongMap = new HashMap<>();
        zhuyuanksfeiyongMap.put(YZCXConstant.zhuyuan_yiliao, zhuyuanqianshi.stream().map(item -> {
            return item.getZhuyuanyiliaofei();
        }).collect(Collectors.toList()));
        zhuyuanksfeiyongMap.put(YZCXConstant.zhuyuan_yaofei, zhuyuanqianshi.stream().map(item -> {
            return item.getZhuyuanyaofei();
        }).collect(Collectors.toList()));
        zhuyuanksfeiyongMap.put(YZCXConstant.zhuyuan_qita, zhuyuanqianshi.stream().map(item -> {
            return item.getZhuyuanqitafei();
        }).collect(Collectors.toList()));
        HighchartsConfig_bar kszhuyuanpaimingChart = HighChartBuilder.builderHighchartsConfig_bar(zhuyuanqianshi.stream().map(item -> {
            return item.getKsname();
        }).collect(Collectors.toList()), "单位：元", zhuyuanksfeiyongMap, true);
        Collections.sort(ksFeiYongInfoList, Comparator.comparingDouble(YzcxHandleInfo_FeiYong::getMenzhenZong).reversed());
        List<YzcxHandleInfo_FeiYong> menzhenqianshi = ksFeiYongInfoList.stream().limit(10).collect(Collectors.toList());
        Map<String, List<Number>> menzhenksfeiyongMap = new HashMap<>();
        menzhenksfeiyongMap.put(YZCXConstant.menzhen_yiliao, menzhenqianshi.stream().map(item -> {
            return item.getMenzhenyiliaofei();
        }).collect(Collectors.toList()));
        menzhenksfeiyongMap.put(YZCXConstant.menzhen_yaofei, menzhenqianshi.stream().map(item -> {
            return item.getMenzhenyaofei();
        }).collect(Collectors.toList()));
        menzhenksfeiyongMap.put(YZCXConstant.menzhen_qita, menzhenqianshi.stream().map(item -> {
            return item.getMenzhenqitafei();
        }).collect(Collectors.toList()));
        HighchartsConfig_bar ksmenzhenpaimingChart = HighChartBuilder.builderHighchartsConfig_bar(menzhenqianshi.stream().map(item -> item.getKsname()).collect(Collectors.toList()), "单位：元", menzhenksfeiyongMap, true);

        ///
        Map<String, Object> rs = new HashMap<>();
        rs.put("meitianChart", meitianChart);
        rs.put("kszhuyuanpaimingChart", kszhuyuanpaimingChart);
        rs.put("ksmenzhenpaimingChart", ksmenzhenpaimingChart);
        return rs;
    }

    @Override
    public FeiYongHuiZong getFeiYong_Month_pagedata(YZCXSearchParam cparam) {
        cparam.setHandletype(Arrays.asList(YZCXConstant.feiyong));
        List<YzcxHandleInfoMonth> feiyongList = yzcxCommonService.getMonthDataByParam(cparam);
        Double zhuyuanZong = feiyongList.stream().filter(obj -> {
            if (obj.getName().indexOf("住院") != -1) {
                return true;
            }
            return false;
        }).collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount));
        Double menzhenZong = feiyongList.stream().filter(obj -> {
            if (obj.getName().indexOf("门诊") != -1) {
                return true;
            }
            return false;
        }).collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount));
        FeiYongHuiZong feiYongHuiZong = new FeiYongHuiZong();
        feiYongHuiZong.setMenzhenzong(menzhenZong);
        feiYongHuiZong.setZhuyuanzong(zhuyuanZong);
        feiYongHuiZong.setParam(cparam);
        return feiYongHuiZong;
    }
}
