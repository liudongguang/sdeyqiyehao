package com.yzcx.impl.service;

import com.github.abel533.echarts.data.PieData;
import com.yzcx.api.po.YzcxHandleInfoDay;
import com.yzcx.api.service.YZCXYiJiSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.impl.mapper.YzcxHandleInfoDayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class YZCXYiJiSearchServiceImpl implements YZCXYiJiSearchService {
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
}
