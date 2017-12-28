package com.yzcx.impl.service;

import com.github.abel533.echarts.data.PieData;
import com.yzcx.api.po.YzcxHandleInfoDay;
import com.yzcx.api.service.YZCXShoushuSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.SsxxIndex;
import com.yzcx.impl.mapper.YzcxHandleInfoDayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class YZCXShoushuSearchServiceImpl implements YZCXShoushuSearchService {
    @Autowired
    private YzcxHandleInfoDayMapper yzcxHandleInfoDayMapper;
    @Override
    public SsxxIndex getIndexData(YZCXSearchParam param) {
        param.setHandletype(Arrays.asList(YZCXConstant.shoushu_anpai));
        final List<YzcxHandleInfoDay> anpaiList = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        param.setHandletype(Arrays.asList(YZCXConstant.shoushu_info));
        final List<YzcxHandleInfoDay> ssshijiList = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        param.setHandletype(Arrays.asList(YZCXConstant.shoushu_anpai_nextDay));
        final List<YzcxHandleInfoDay> nextDayanpaiList = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        SsxxIndex ssxxIndex=new SsxxIndex();
        if(anpaiList!=null&&anpaiList.size()!=0){
            ssxxIndex.setAnpai(anpaiList.get(0).getCount().intValue());
        }
        if(ssshijiList!=null&&ssshijiList.size()!=0){
            ssxxIndex.setShijiss(ssshijiList.get(0).getCount().intValue());
        }
        if(nextDayanpaiList!=null&&nextDayanpaiList.size()!=0){
            ssxxIndex.setNextDayanpai(nextDayanpaiList.get(0).getCount().intValue());
        }
        return ssxxIndex;
    }

    @Override
    public Map<String, Object> getIndexChart(YZCXSearchParam param) {
        param.setHandletype(Arrays.asList(YZCXConstant.shoushu_fenji));
        final List<YzcxHandleInfoDay> fenjiList = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        final Map<String, Double> fenjiMap = fenjiList.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName, Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        List<PieData> pieDatalist=new ArrayList<>();
        List<String> legendList=new ArrayList<>();
        fenjiMap.forEach((name,num)->{
            legendList.add(name);
            PieData pieData=new PieData(name,num);
            pieDatalist.add(pieData);
        });
        Map<String, Object> rs = new HashMap<>();
        rs.put("pieDatalist", pieDatalist);
        rs.put("legendList", legendList);
        return rs;
    }
}
