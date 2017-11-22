package com.yzcx.impl.service;

import com.yzcx.api.bo.YzcxHandleInfo_FeiYong;
import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.service.YZCXFeiYongSearchService;
import com.yzcx.api.util.HighChartBuilder;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXscheduleMapToListHandler;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.highchat.bar.HighchartsConfig_bar;
import com.yzcx.api.vo.highchat.bar.Series_bar;
import com.yzcx.api.vo.yzcxdisplay.FeiYongHuiZong;
import com.yzcx.impl.mapper.YzcxHandleInfoMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMonthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class YZCXFeiYongSearchServiceImpl implements YZCXFeiYongSearchService {
    @Autowired
    private YzcxHandleInfoMapper yzcxHandleInfoMapper;
    @Autowired
    private YzcxHandleInfoMonthMapper yzcxHandleInfoMonthMapper;

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
        /////////////////////////////////////////////////////////////
        List<Series_bar> series = new ArrayList<>();
        series.add(HighChartBuilder.builderSeries_bar("住院收入", Arrays.asList(zhuyuanYiLiao, zhuyuanYao, zhuyuanQiTa)));
        HighchartsConfig_bar zhuyuanChart = HighChartBuilder.builderHighchartsConfig_bar(Arrays.asList(YZCXConstant.zhuyuan_yiliao, YZCXConstant.zhuyuan_yaofei, YZCXConstant.zhuyuan_qita), "单位：元", series);
        ///////////////////////////////////////////////////////////////////
        List<Series_bar> menzhenseries = new ArrayList<>();
        menzhenseries.add(HighChartBuilder.builderSeries_bar("门诊收入", Arrays.asList(menzhenYiLiao, menzhenYao, menzhenQiTa)));
        HighchartsConfig_bar menzhenChart = HighChartBuilder.builderHighchartsConfig_bar(Arrays.asList(YZCXConstant.menzhen_yiliao, YZCXConstant.menzhen_yaofei, YZCXConstant.menzhen_qita), "单位：元", menzhenseries);
        ////////////////////////////////////////////////////////
        param.setHandletype(Arrays.asList(YZCXConstant.feiyong_zhuyuan_yaofei, YZCXConstant.feiyong_zhuyuan_qitafei, YZCXConstant.feiyong_zhuyuan_yiliaofei, YZCXConstant.feiyong_menzhen_yaofei, YZCXConstant.feiyong_menzhen_qitafei, YZCXConstant.feiyong_menzhen_yiliaofei));
        List<YzcxHandleInfo> ksfeiyongList = yzcxHandleInfoMapper.selectByDateAndType(param);
        Map<String, Map<Integer, Double>> ksTypeNum = ksfeiyongList.stream().collect(Collectors.groupingBy(YzcxHandleInfo::getName, Collectors.groupingBy(YzcxHandleInfo::getHandletype, Collectors.summingDouble(YzcxHandleInfo::getCount))));
        List<YzcxHandleInfo_FeiYong> ksFeiYongInfoList= YZCXscheduleMapToListHandler.getKSFeiyong(ksTypeNum);
        Collections.sort(ksFeiYongInfoList,Comparator.comparingDouble(YzcxHandleInfo_FeiYong::getZhuyuanZong).reversed());
        List<YzcxHandleInfo_FeiYong> zhuyuanqianshi = ksFeiYongInfoList.stream().limit(10).collect(Collectors.toList());
        List<Series_bar> zhuyuanpaimingseries = new ArrayList<>();
        List<String> kszhuyuanfeiyongcategories=new ArrayList<>();
        Series_bar zhuyuan_yiliaobar=new Series_bar();
        zhuyuan_yiliaobar.setName(YZCXConstant.zhuyuan_yiliao);
        Series_bar zhuyuan_yaofeibar=new Series_bar();
        zhuyuan_yaofeibar.setName(YZCXConstant.zhuyuan_yaofei);
        Series_bar zhuyuan_qitabar=new Series_bar();
        zhuyuan_qitabar.setName(YZCXConstant.zhuyuan_qita);
        zhuyuanpaimingseries.add(zhuyuan_yiliaobar);
        zhuyuanpaimingseries.add(zhuyuan_yaofeibar);
        zhuyuanpaimingseries.add(zhuyuan_qitabar);
        zhuyuanqianshi.forEach(zhuyuanObj->{
            kszhuyuanfeiyongcategories.add(zhuyuanObj.getKsname());
            double zhuyuanyiliao=zhuyuanObj.getZhuyuanyiliaofei();
            double zhuyuanyao=zhuyuanObj.getZhuyuanyaofei();
            double zhuyuanqita=zhuyuanObj.getZhuyuanqitafei();
            zhuyuan_yiliaobar.getData().add(zhuyuanyiliao);
            zhuyuan_yaofeibar.getData().add(zhuyuanyao);
            zhuyuan_qitabar.getData().add(zhuyuanqita);
        });
        HighchartsConfig_bar kszhuyuanpaimingChart = HighChartBuilder.builderHighchartsConfig_bar(kszhuyuanfeiyongcategories, "单位：元", zhuyuanpaimingseries,true);
        Collections.sort(ksFeiYongInfoList,Comparator.comparingDouble(YzcxHandleInfo_FeiYong::getMenzhenZong).reversed());
        List<YzcxHandleInfo_FeiYong> menzhenqianshi = ksFeiYongInfoList.stream().limit(10).collect(Collectors.toList());
        List<Series_bar> menzhenpaimingseries = new ArrayList<>();
        List<String> ksmenzhenfeiyongcategories=new ArrayList<>();
        Series_bar menzhen_yiliaobar=new Series_bar();
        menzhen_yiliaobar.setName(YZCXConstant.menzhen_yiliao);
        Series_bar menzhen_yaofeibar=new Series_bar();
        menzhen_yaofeibar.setName(YZCXConstant.menzhen_yaofei);
        Series_bar menzhen_qitabar=new Series_bar();
        menzhen_qitabar.setName(YZCXConstant.menzhen_qita);
        menzhenpaimingseries.add(menzhen_yiliaobar);
        menzhenpaimingseries.add(menzhen_yaofeibar);
        menzhenpaimingseries.add(menzhen_qitabar);
        menzhenqianshi.forEach(zhuyuanObj->{
            ksmenzhenfeiyongcategories.add(zhuyuanObj.getKsname());
            double menzhenyiliao=zhuyuanObj.getMenzhenyiliaofei();
            double menzhenyao=zhuyuanObj.getMenzhenyaofei();
            double menzhenqita=zhuyuanObj.getMenzhenqitafei();
            menzhen_yiliaobar.getData().add(menzhenyiliao);
            menzhen_yaofeibar.getData().add(menzhenyao);
            menzhen_qitabar.getData().add(menzhenqita);
        });
        HighchartsConfig_bar ksmenzhenpaimingChart = HighChartBuilder.builderHighchartsConfig_bar(ksmenzhenfeiyongcategories, "单位：元", menzhenpaimingseries,true);
        rs.put("zhuyuan", zhuyuanChart);
        rs.put("menzhen", menzhenChart);
        rs.put("kszhuyuan", kszhuyuanpaimingChart);
        rs.put("ksmenzhen", ksmenzhenpaimingChart);
        return rs;
    }

    @Override
    public FeiYongHuiZong getIndexFeiYongZong(YZCXSearchParam param) {
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
        return feiYongHuiZong;
    }
}
