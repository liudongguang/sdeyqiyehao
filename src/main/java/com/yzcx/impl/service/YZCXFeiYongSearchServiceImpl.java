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
        Map<String,List<Number>> zysrMap=new HashMap<>();
        zysrMap.put("住院收入", Arrays.asList(zhuyuanYiLiao, zhuyuanYao, zhuyuanQiTa));
        HighchartsConfig_bar zhuyuanChart = HighChartBuilder.builderHighchartsConfig_bar(Arrays.asList(YZCXConstant.zhuyuan_yiliao, YZCXConstant.zhuyuan_yaofei, YZCXConstant.zhuyuan_qita), "单位：元", zysrMap);
        ///////////////////////////////////////////////////////////////////
        Map<String,List<Number>> mzsrMap=new HashMap<>();
        mzsrMap.put("门诊收入", Arrays.asList(menzhenYiLiao, menzhenYao, menzhenQiTa));
        HighchartsConfig_bar menzhenChart = HighChartBuilder.builderHighchartsConfig_bar(Arrays.asList(YZCXConstant.menzhen_yiliao, YZCXConstant.menzhen_yaofei, YZCXConstant.menzhen_qita), "单位：元", mzsrMap);
        ////////////////////////////////////////////////////////
        param.setHandletype(Arrays.asList(YZCXConstant.feiyong_zhuyuan_yaofei, YZCXConstant.feiyong_zhuyuan_qitafei, YZCXConstant.feiyong_zhuyuan_yiliaofei, YZCXConstant.feiyong_menzhen_yaofei, YZCXConstant.feiyong_menzhen_qitafei, YZCXConstant.feiyong_menzhen_yiliaofei));
        List<YzcxHandleInfo> ksfeiyongList = yzcxHandleInfoMapper.selectByDateAndType(param);
        Map<String, Map<Integer, Double>> ksTypeNum = ksfeiyongList.stream().collect(Collectors.groupingBy(YzcxHandleInfo::getName, Collectors.groupingBy(YzcxHandleInfo::getHandletype, Collectors.summingDouble(YzcxHandleInfo::getCount))));
        List<YzcxHandleInfo_FeiYong> ksFeiYongInfoList= YZCXscheduleMapToListHandler.getKSFeiyong(ksTypeNum);
        Collections.sort(ksFeiYongInfoList,Comparator.comparingDouble(YzcxHandleInfo_FeiYong::getZhuyuanZong).reversed());
        List<YzcxHandleInfo_FeiYong> zhuyuanqianshi = ksFeiYongInfoList.stream().limit(10).collect(Collectors.toList());
        Map<String,List<Number>> zhuyuanksfeiyongMap=new HashMap<>();
        zhuyuanksfeiyongMap.put(YZCXConstant.zhuyuan_yiliao,zhuyuanqianshi.stream().map(item->{return item.getZhuyuanyiliaofei();}).collect(Collectors.toList()));
        zhuyuanksfeiyongMap.put(YZCXConstant.zhuyuan_yaofei,zhuyuanqianshi.stream().map(item->{return item.getZhuyuanyaofei();}).collect(Collectors.toList()));
        zhuyuanksfeiyongMap.put(YZCXConstant.zhuyuan_qita,zhuyuanqianshi.stream().map(item->{return item.getZhuyuanqitafei();}).collect(Collectors.toList()));
        HighchartsConfig_bar kszhuyuanpaimingChart = HighChartBuilder.builderHighchartsConfig_bar(zhuyuanqianshi.stream().map(item->{return item.getKsname();}).collect(Collectors.toList()), "单位：元", zhuyuanksfeiyongMap,true);
        Collections.sort(ksFeiYongInfoList,Comparator.comparingDouble(YzcxHandleInfo_FeiYong::getMenzhenZong).reversed());
        List<YzcxHandleInfo_FeiYong> menzhenqianshi = ksFeiYongInfoList.stream().limit(10).collect(Collectors.toList());
        Map<String,List<Number>> menzhenksfeiyongMap=new HashMap<>();
        menzhenksfeiyongMap.put(YZCXConstant.menzhen_yiliao,menzhenqianshi.stream().map(item->{return item.getMenzhenyiliaofei();}).collect(Collectors.toList()));
        menzhenksfeiyongMap.put(YZCXConstant.menzhen_yaofei,menzhenqianshi.stream().map(item->{return item.getMenzhenyaofei();}).collect(Collectors.toList()));
        menzhenksfeiyongMap.put(YZCXConstant.menzhen_qita,menzhenqianshi.stream().map(item->{return item.getMenzhenqitafei();}).collect(Collectors.toList()));
        HighchartsConfig_bar ksmenzhenpaimingChart = HighChartBuilder.builderHighchartsConfig_bar(menzhenqianshi.stream().map(item->item.getKsname()).collect(Collectors.toList()), "单位：元", menzhenksfeiyongMap,true);
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
