package com.yzcx.impl.service;

import com.yzcx.api.po.YzcxHandleInfoDay;
import com.yzcx.api.service.YZCXHuanZhenSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.impl.mapper.YzcxHandleInfoDayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class YZCXHuanZhenSearchServiceImpl implements YZCXHuanZhenSearchService {
    @Autowired
    private YzcxHandleInfoDayMapper yzcxHandleInfoDayMapper;

    @Override
    public Map<String, Object> getIndexChart(YZCXSearchParam param) {
        param.setHandletype(Arrays.asList(YZCXConstant.menzhen_xingbieAge_nan));
        List<YzcxHandleInfoDay> list_nan = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        param.setHandletype(Arrays.asList(YZCXConstant.menzhen_xingbieAge_nv));
        List<YzcxHandleInfoDay> list_nv = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        final Map<String, Double> nan = list_nan.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName, Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        final Map<String, Double> nv = list_nv.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName, Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        List<Integer> nanNum = new ArrayList<>();
        List<Integer> nvNum = new ArrayList<>();
        YZCXConstant.ageStrList.forEach(str -> {
            Double numNan = nan.get(str);
            Double numNV = nv.get(str);
            nanNum.add(numNan != null ? numNan.intValue() : 0);
            nvNum.add(numNV != null ? numNV.intValue() : 0);
        });
        Map<String, Object> rs = new HashMap<>();
        rs.put("ageStrList", YZCXConstant.ageStrList);
        rs.put("nanNum", nanNum);
        rs.put("nvNum", nvNum);
        return rs;
    }
}
