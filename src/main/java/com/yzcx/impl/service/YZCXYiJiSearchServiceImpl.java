package com.yzcx.impl.service;

import com.github.abel533.echarts.data.PieData;
import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.po.YzcxHandleInfoDay;
import com.yzcx.api.service.YZCXYiJiSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.YiJiMonthData;
import com.yzcx.api.vo.yzcxdisplay.YzcxHandleInfoExt;
import com.yzcx.impl.mapper.YzcxHandleInfoDayMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMapper;
import com.yzcx.impl.service.handler.YzcxHandleInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class YZCXYiJiSearchServiceImpl implements YZCXYiJiSearchService {
    @Autowired
    private YzcxHandleInfoMapper yzcxHandleInfoMapper;
    @Autowired
    private YzcxHandleInfoDayMapper yzcxHandleInfoDayMapper;
    @Override
    public Map<String, Object> getIndexChart(YZCXSearchParam param) {
        param.setHandletype(Arrays.asList(YZCXConstant.yiji_menzhen));
        List<YzcxHandleInfoDay> list_menzhen = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        param.setHandletype(Arrays.asList(YZCXConstant.yiji_zhuyuan));
        List<YzcxHandleInfoDay> list_zhuyuan = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        param.setHandletype(Arrays.asList(YZCXConstant.yiji_menzhen_heji));
        List<YzcxHandleInfoDay> list_menzhen_heji = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        param.setHandletype(Arrays.asList(YZCXConstant.yiji_zhuyuan_heji));
        List<YzcxHandleInfoDay> list_zhuyuan_heji = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        param.setHandletype(Arrays.asList(YZCXConstant.yiji_type));
        List<YzcxHandleInfoDay> list_type = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        if(list_menzhen.size()==0){
            return null;
        }
        final YzcxHandleInfoDay menzhenNum = list_menzhen.get(0);
        final YzcxHandleInfoDay zhuyuanNum = list_zhuyuan.get(0);
        final YzcxHandleInfoDay menzhenNum_heji = list_menzhen_heji.get(0);
        final YzcxHandleInfoDay zhuyuanNum_heji = list_zhuyuan_heji.get(0);
        final Map<String, Double> yijiTypeMap = list_type.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName, Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        Map<String, Object> rs = new HashMap<>();
        PieData yijiRenCi_menzhen=new PieData("门诊人数",menzhenNum.getCount());
        PieData yijiRenCi_zhuyuan=new PieData("住院人数",zhuyuanNum.getCount());
        rs.put("yijiRenci", Arrays.asList(yijiRenCi_menzhen,yijiRenCi_zhuyuan));
        PieData yijiHeji_menzhen=new PieData("门诊金额",menzhenNum_heji.getCount());
        PieData yijiHeji_zhuyuan=new PieData("住院金额",zhuyuanNum_heji.getCount());
        rs.put("yijiHeji", Arrays.asList(yijiHeji_menzhen,yijiHeji_zhuyuan));
        List<PieData> yijiType=new ArrayList<>();
        yijiTypeMap.forEach((typeName,renci)->{
            PieData newPd=new PieData(typeName,renci);
            yijiType.add(newPd);
        });
        rs.put("yijiType", yijiType);
        return rs;
    }

    @Override
    public YiJiMonthData getMonthChartData(YZCXSearchParam yzcxSearchParam) {
        YiJiMonthData yiJiMonthData=new YiJiMonthData();
        yiJiMonthData.setParam(yzcxSearchParam);
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.yiji_menzhen));
        List<YzcxHandleInfo> list_menzhen = yzcxHandleInfoMapper.selectByDateAndType(yzcxSearchParam);
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.yiji_zhuyuan));
        List<YzcxHandleInfo> list_zhuyuan = yzcxHandleInfoMapper.selectByDateAndType(yzcxSearchParam);
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.yiji_menzhen_heji));
        List<YzcxHandleInfo> list_menzhen_heji = yzcxHandleInfoMapper.selectByDateAndType(yzcxSearchParam);
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.yiji_zhuyuan_heji));
        List<YzcxHandleInfo> list_zhuyuan_heji = yzcxHandleInfoMapper.selectByDateAndType(yzcxSearchParam);
        if(list_menzhen.size()==0){
            return null;
        }
        yiJiMonthData.setMenzhenHeji(list_menzhen_heji.get(0).getCount());
        yiJiMonthData.setZhuyuanHeji(list_zhuyuan_heji.get(0).getCount());
        yiJiMonthData.setMenzhenRenci(list_menzhen.get(0).getCount().intValue());
        yiJiMonthData.setZhuyuanRenci(list_menzhen.get(0).getCount().intValue());
        yiJiMonthData.pingjun();
        return yiJiMonthData;
    }

    @Override
    public Map<String, Object> monthChartPage_chart(YZCXSearchParam yzcxSearchParam) {
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.yiji_menzhen,YZCXConstant.yiji_zhuyuan));
        List<YzcxHandleInfo> huizhenList_shenqing = yzcxHandleInfoMapper.selectByDateAndType(yzcxSearchParam);
        LinkedHashMap<String, Double> everyDayRenCi= huizhenList_shenqing.stream().map(item -> {
            return YzcxHandleInfoFactory.createYzcxHandleInfoExtForEveryDay(item.getHandledate(), item.getCount(), item.getName());
        }).collect(Collectors.groupingBy(YzcxHandleInfoExt::getHandledateStr, LinkedHashMap::new, Collectors.summingDouble(YzcxHandleInfoExt::getCount)));
        /////////
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.yiji_menzhen_heji,YZCXConstant.yiji_zhuyuan_heji));
        List<YzcxHandleInfo> huizhenList_jieshou = yzcxHandleInfoMapper.selectByDateAndType(yzcxSearchParam);
        LinkedHashMap<String, Double> everyDayFeiyong = huizhenList_jieshou.stream().map(item -> {
            return YzcxHandleInfoFactory.createYzcxHandleInfoExtForEveryDay(item.getHandledate(), item.getCount(), item.getName());
        }).collect(Collectors.groupingBy(YzcxHandleInfoExt::getHandledateStr, LinkedHashMap::new, Collectors.summingDouble(YzcxHandleInfoExt::getCount)));
        Map<String, Object> rs = new HashMap<>();
        List<String> legendData = new ArrayList<>();
        List<Number> renci = new ArrayList<>();
        List<Number> heji = new ArrayList<>();
        everyDayRenCi.forEach((dateStr,num)->{
            legendData.add(dateStr);
            renci.add(num);
            Double hejijj = everyDayFeiyong.get(dateStr);
            hejijj=hejijj==null?0:hejijj;
            heji.add(hejijj);
        });
        rs.put("legendData", legendData);
        rs.put("renci", renci);
        rs.put("heji", heji);
        return rs;
    }
}
