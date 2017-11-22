package com.yzcx.impl.service;

import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.service.YZCXFeiYongSearchService;
import com.yzcx.api.util.HighChartBuilder;
import com.yzcx.api.util.YZCXConstant;
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
        series.add(HighChartBuilder.builderSeries_bar("住院收入",Arrays.asList(zhuyuanYiLiao,zhuyuanYao,zhuyuanQiTa)));
        HighchartsConfig_bar zhuyuanChart= HighChartBuilder.builderHighchartsConfig_bar(Arrays.asList(YZCXConstant.zhuyuan_yiliao, YZCXConstant.zhuyuan_yaofei, YZCXConstant.zhuyuan_qita), "单位：元", series);
        ////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////
        List<Series_bar> menzhenseries = new ArrayList<>();
        menzhenseries.add(HighChartBuilder.builderSeries_bar("门诊收入",Arrays.asList(menzhenYiLiao,menzhenYao,menzhenQiTa)));
        HighchartsConfig_bar menzhenChart=HighChartBuilder.builderHighchartsConfig_bar(Arrays.asList(YZCXConstant.menzhen_yiliao, YZCXConstant.menzhen_yaofei, YZCXConstant.menzhen_qita), "单位：元", menzhenseries);
        ////////////////////////////////////////////////////////
        rs.put("zhuyuan",zhuyuanChart);
        rs.put("menzhen",menzhenChart);
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
        FeiYongHuiZong feiYongHuiZong=new FeiYongHuiZong();
        feiYongHuiZong.setMenzhenzong(menzhenZong);
        feiYongHuiZong.setZhuyuanzong(zhuyuanZong);
        return feiYongHuiZong;
    }
}
