package com.yzcx.impl.service;

import com.github.abel533.echarts.Option;
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
        List<YzcxHandleInfoDay> cyfs = zhuyuanMap.get(YZCXConstant.zhuyuan_cyfs);
        List<YzcxHandleInfoDay> chuyuanrs = zhuyuanMap.get(YZCXConstant.zhuyuan_chuyuanRenshu);
        List<YzcxHandleInfoDay> ruyuanrs = zhuyuanMap.get(YZCXConstant.zhuyuan_ruyuanrenshu);
        List<YzcxHandleInfoDay> zhuanchuks = zhuyuanMap.get(YZCXConstant.zhuyuan_zhuanchuKS);
        List<YzcxHandleInfoDay> zhuanruks = zhuyuanMap.get(YZCXConstant.zhuyuan_zhuanruKS);
        ZyxxIndex index = new ZyxxIndex();
        if (chuyuanrs != null) {
            index.setChuyuan(chuyuanrs.get(0).getCount());
        }
        if (ruyuanrs != null) {
            index.setRuyuan(ruyuanrs.get(0).getCount());
        }
        if (brqk != null) {
            Map<String, Double> brqkMap = brqk.stream().collect(Collectors.toMap(YzcxHandleInfoDay::getName, YzcxHandleInfoDay::getCount));
            Double bws = brqkMap.get(YZCXConstant.zhuyuan_brqk_bingwei);//病危
            Double bzs = brqkMap.get(YZCXConstant.zhuyuan_brqk_bingzhong);//病重数
            if (bws == null) {
                bws = 0d;
            }
            if (bzs == null) {
                bzs = 0d;
            }
            index.setWeizhong(bws+bzs);
        }
        if (cyfs != null) {
            Map<String, Double> cyfsMap = cyfs.stream().collect(Collectors.toMap(YzcxHandleInfoDay::getName, YzcxHandleInfoDay::getCount));
            Double sws = cyfsMap.get(YZCXConstant.zhuyuan_cyfs_siwang);
            index.setSiwang(sws);
        }
        if(zhuanchuks!=null){
            index.setZhuanchu(zhuanchuks.stream().collect(Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        }
        if(zhuanruks!=null){
            index.setZhuanchu(zhuanruks.stream().collect(Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        }
        return index;
    }

    @Override
    public Map<String, Object> getIndexChart(YZCXSearchParam param) {
        Option option=new Option();

        return null;
    }
}
