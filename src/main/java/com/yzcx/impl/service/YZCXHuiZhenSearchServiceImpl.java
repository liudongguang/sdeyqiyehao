package com.yzcx.impl.service;

import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.po.YzcxHandleInfoDay;
import com.yzcx.api.service.YZCXCommonService;
import com.yzcx.api.service.YZCXHuiZhenSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.HzxxIndex;
import com.yzcx.api.vo.yzcxdisplay.YzcxHandleInfoExt;
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
public class YZCXHuiZhenSearchServiceImpl implements YZCXHuiZhenSearchService {
    @Autowired
    private YzcxHandleInfoDayMapper yzcxHandleInfoDayMapper;
    @Autowired
    private YzcxHandleInfoMapper yzcxHandleInfoMapper;
    @Autowired
    private YzcxHandleInfoMonthMapper yzcxHandleInfoMonthMapper;
    @Autowired
    private YZCXCommonService yzcxCommonService;
    @Override
    public HzxxIndex getIndexHuiZhenForDay(YZCXSearchParam param) throws ParseException {
        param.setHandletype(Arrays.asList(YZCXConstant.huizhen_jieshou));
        final List<YzcxHandleInfoDay> jieshouList = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        param.setHandletype(Arrays.asList(YZCXConstant.huizhen_shenqing));
        final List<YzcxHandleInfoDay> shenqingList = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        HzxxIndex ssxxIndex=new HzxxIndex();
        if(jieshouList!=null&&jieshouList.size()!=0){
            Double jsnum = jieshouList.stream().collect(Collectors.summingDouble(YzcxHandleInfoDay::getCount));
            ssxxIndex.setJieshou(jsnum.intValue());
        }
        if(shenqingList!=null&&shenqingList.size()!=0){
            Double sqnum = shenqingList.stream().collect(Collectors.summingDouble(YzcxHandleInfoDay::getCount));
            ssxxIndex.setShenqing(sqnum.intValue());
        }
        return ssxxIndex;
    }

    @Override
    public Map<String, Object> getIndexChart(YZCXSearchParam param) throws ParseException {
        param.setHandletype(Arrays.asList(YZCXConstant.huizhen_jieshou));
        List<YzcxHandleInfoDay> list_jieshou = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        param.setHandletype(Arrays.asList(YZCXConstant.huizhen_shenqing));
        List<YzcxHandleInfoDay> list_shenqing = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        final Map<String, Double> jieshouMap = list_jieshou.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName, Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        final Map<String, Double> shenqingMap = list_shenqing.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName, Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        List<String>  legendData= new ArrayList<>();
        List<Integer> shenqingNum = new ArrayList<>();
        List<Integer> jieshousNum = new ArrayList<>();
        shenqingMap.forEach((ksname,sqsum) -> {
            legendData.add(ksname);
            Double jieshousum = jieshouMap.get(ksname);
            jieshousNum.add(jieshousum != null ? jieshousum.intValue() : 0);
            shenqingNum.add(sqsum != null ? sqsum.intValue() : 0);
        });
        Map<String, Object> rs = new HashMap<>();
        rs.put("legendData", legendData);
        rs.put("shenqing", shenqingNum);
        rs.put("jieshou", jieshousNum);
        return rs;
    }

    @Override
    public Map<String, Object> getMonthChart(YZCXSearchParam param) {
        param.setHandletype(Arrays.asList(YZCXConstant.huizhen_shenqing));
        List<YzcxHandleInfo> huizhenList_shenqing = yzcxHandleInfoMapper.selectByDateAndType(param);
        LinkedHashMap<String, Double> everyDayRuYuan_shenqing = huizhenList_shenqing.stream().map(item -> {
            return YzcxHandleInfoFactory.createYzcxHandleInfoExtForEveryDay(item.getHandledate(), item.getCount(), item.getName());
        }).collect(Collectors.groupingBy(YzcxHandleInfoExt::getHandledateStr, LinkedHashMap::new, Collectors.summingDouble(YzcxHandleInfoExt::getCount)));
        /////////
        param.setHandletype(Arrays.asList(YZCXConstant.huizhen_jieshou));
        List<YzcxHandleInfo> huizhenList_jieshou = yzcxHandleInfoMapper.selectByDateAndType(param);
        LinkedHashMap<String, Double> everyDayRuYuan_jieshou = huizhenList_jieshou.stream().map(item -> {
            return YzcxHandleInfoFactory.createYzcxHandleInfoExtForEveryDay(item.getHandledate(), item.getCount(), item.getName());
        }).collect(Collectors.groupingBy(YzcxHandleInfoExt::getHandledateStr, LinkedHashMap::new, Collectors.summingDouble(YzcxHandleInfoExt::getCount)));


        List<String> legendData = new ArrayList<>();
        List<Number> shenqing = new ArrayList<>();
        List<Number> jieshou = new ArrayList<>();
        everyDayRuYuan_shenqing.forEach((dateStr,SumNumber)->{
            legendData.add(dateStr);
            shenqing.add(SumNumber);
             Double jieshouNum = everyDayRuYuan_jieshou.get(dateStr);
            jieshouNum=jieshouNum==null?0:jieshouNum;
            jieshou.add(jieshouNum);
        });
        Map<String, Object> rs = new HashMap<>();
        rs.put("legendData", legendData);
        rs.put("shenqing", shenqing);
        rs.put("jieshou", jieshou);
        return rs;
    }
}
