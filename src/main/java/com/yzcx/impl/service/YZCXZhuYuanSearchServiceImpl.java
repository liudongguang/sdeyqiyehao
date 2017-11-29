package com.yzcx.impl.service;

import com.yzcx.api.po.YzcxHandleInfoDay;
import com.yzcx.api.service.YZCXZhuYuanSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.ZyxxIndex;
import com.yzcx.impl.mapper.YzcxHandleInfoDayMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMonthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class YZCXZhuYuanSearchServiceImpl implements YZCXZhuYuanSearchService {
    @Autowired
    private YzcxHandleInfoDayMapper yzcxHandleInfoDayMapper;
    @Autowired
    private YzcxHandleInfoMapper yzcxHandleInfoMapper;
    @Autowired
    private YzcxHandleInfoMonthMapper yzcxHandleInfoMonthMapper;
    @Override
    public ZyxxIndex getIndexZhuYuanForDay(YZCXSearchParam param) {
        List<Integer> zyxxType = Arrays.asList(YZCXConstant.zhuyuan_brqk, YZCXConstant.zhuyuan_cyfs,
                YZCXConstant.zhuyuan_chuyuanRenshu, YZCXConstant.zhuyuan_ruyuanrenshu,
                YZCXConstant.zhuyuan_zhuanchuKS, YZCXConstant.zhuyuan_zhuanruKS);
        param.setHandletype(zyxxType);
        final List<YzcxHandleInfoDay> yzcxHandleInfoDays = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        final Map<Integer, List<YzcxHandleInfoDay>> zhuyuanMap = yzcxHandleInfoDays.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getHandletype));
        List<YzcxHandleInfoDay> brqk = zhuyuanMap.get(YZCXConstant.zhuyuan_brqk);
        List<YzcxHandleInfoDay> cyfs =zhuyuanMap.get(YZCXConstant.zhuyuan_cyfs);
        List<YzcxHandleInfoDay> chuyuanrs =zhuyuanMap.get(YZCXConstant.zhuyuan_chuyuanRenshu);
        List<YzcxHandleInfoDay> ruyuanrs=zhuyuanMap.get(YZCXConstant.zhuyuan_ruyuanrenshu);
        List<YzcxHandleInfoDay> zhuanchuks =zhuyuanMap.get(YZCXConstant.zhuyuan_zhuanchuKS);
        List<YzcxHandleInfoDay> zhuanruks =zhuyuanMap.get(YZCXConstant.zhuyuan_zhuanruKS);
        zhuyuanMap.forEach((k,v)->{
            System.out.println(k+"    "+v);
        });
        return null;
    }
}
