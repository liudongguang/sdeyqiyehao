package com.yzcx.impl.service;

import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.service.YZCXFeiYongSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.impl.mapper.YzcxHandleInfoMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMonthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        return rs;
    }
}
