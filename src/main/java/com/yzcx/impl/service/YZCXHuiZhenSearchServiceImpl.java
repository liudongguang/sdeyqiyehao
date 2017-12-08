package com.yzcx.impl.service;

import com.yzcx.api.po.YzcxHandleInfoDay;
import com.yzcx.api.service.YZCXHuiZhenSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.HzxxIndex;
import com.yzcx.impl.mapper.YzcxHandleInfoDayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class YZCXHuiZhenSearchServiceImpl implements YZCXHuiZhenSearchService {
    @Autowired
    private YzcxHandleInfoDayMapper yzcxHandleInfoDayMapper;
    @Override
    public HzxxIndex getIndexHuiZhenForDay(YZCXSearchParam param) throws ParseException {
        param.setHandletype(Arrays.asList(YZCXConstant.huizhen_jieshou));
        final List<YzcxHandleInfoDay> jieshouList = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        param.setHandletype(Arrays.asList(YZCXConstant.huizhen_shenqing));
        final List<YzcxHandleInfoDay> shenqingList = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        HzxxIndex ssxxIndex=new HzxxIndex();
        if(jieshouList!=null&&jieshouList.size()!=0){
            ssxxIndex.setJieshou(jieshouList.get(0).getCount().intValue());
        }
        if(shenqingList!=null&&shenqingList.size()!=0){
            ssxxIndex.setShenqing(shenqingList.get(0).getCount().intValue());
        }
        return ssxxIndex;
    }

    @Override
    public Map<String, Object> getIndexChart(YZCXSearchParam param) throws ParseException {
        param.setHandletype(Arrays.asList(YZCXConstant.huizhen_jieshou));
        List<YzcxHandleInfoDay> list_jieshou = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        param.setHandletype(Arrays.asList(YZCXConstant.huizhen_shenqing));
        List<YzcxHandleInfoDay> list_shenqing = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        final Map<String, Double> jieshoulist = list_jieshou.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName, Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        final Map<String, Double> shenqinglist = list_shenqing.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName, Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        List<String>  legendData= new ArrayList<>();
        List<Integer> shenqingNum = new ArrayList<>();
        List<Integer> jieshousNum = new ArrayList<>();
        jieshoulist.forEach((ksname,jssum) -> {
            legendData.add(ksname);
            Double shenqingsum = shenqinglist.get(ksname);
            jieshousNum.add(jssum != null ? jssum.intValue() : 0);
            shenqingNum.add(shenqingsum != null ? shenqingsum.intValue() : 0);
        });
        Map<String, Object> rs = new HashMap<>();
        rs.put("legendData", legendData);
        rs.put("shenqing", shenqingNum);
        rs.put("jieshou", jieshousNum);
        return rs;
    }
}
