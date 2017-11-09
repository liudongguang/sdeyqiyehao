package com.yzcx.impl.service;

import com.yzcx.api.po.YzcxHandleInfoDay;
import com.yzcx.api.service.YZCXSearchService;
import com.yzcx.api.util.HighChartUtils;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.vo.highchat.*;
import com.yzcx.api.vo.yzcxdisplay.QyglVo;
import com.yzcx.impl.mapper.YzcxHandleImportdateMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoDayMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMonthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by LiuDongguang on 2017/11/9.
 */
@Service
public class YZCXSearchServiceImpl implements YZCXSearchService {
    @Autowired
    private YzcxHandleInfoMapper yzcxHandleInfoMapper;
    @Autowired
    private YzcxHandleImportdateMapper yzcxHandleImportdateMapper;
    @Autowired
    private YzcxHandleInfoMonthMapper yzcxHandleInfoMonthMapper;
    @Autowired
    private YzcxHandleInfoDayMapper yzcxHandleInfoDayMapper;

    @Override
    public QyglVo getQygl_ri() throws ParseException {
        QyglVo rs = new QyglVo();
        List<YzcxHandleInfoDay> list = yzcxHandleInfoDayMapper.selectByDate(LdgDateUtil.getDayZeroTime(), LdgDateUtil.getDayLastTime());
        Map<String, Double> collect = list.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName, Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        String double_putong=collect.get(YZCXConstant.putong).toString();
        String double_jizhen=collect.get(YZCXConstant.jizhen).toString();
        rs.setPutong(Double.valueOf(double_putong));
        rs.setJizhen(Double.valueOf(double_jizhen));
        return rs;
    }

    @Override
    public HighchartsConfig getQygl_riChart() throws ParseException {
        List<YzcxHandleInfoDay> list = yzcxHandleInfoDayMapper.selectByDate(LdgDateUtil.getDayZeroTime(), LdgDateUtil.getDayLastTime());
        HighchartsConfig hcfg = HighChartUtils.createBasicChat("","单位：人");
        //hcfg.getxAxis().setCategories(Arrays.asList(YZCXConstant.dayHours));
        //hcfg.getxAxis().setType("datetime");
        Tooltip tooltip=new Tooltip();
        tooltip.setPointFormat("<span style=\"color:{series.color}\">{series.name}</span>: <b>{point.y}人</b><br/>");
        hcfg.setTooltip(tooltip);
        Series menzhens=new Series();
        Series jizhens=new Series();
        Map<String, List<YzcxHandleInfoDay>> collect = list.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName));
        List<YzcxHandleInfoDay> putong = collect.get(YZCXConstant.putong);
        List<YzcxHandleInfoDay> jizhen =collect.get(YZCXConstant.jizhen);
        menzhens.setName(YZCXConstant.putong);
        menzhens.setData(putong.stream().map(item->{
            return item.getCount();
        }).collect(Collectors.toList()));

        jizhens.setName(YZCXConstant.jizhen);
        jizhens.setData(jizhen.stream().map(item->{
            return item.getCount();
        }).collect(Collectors.toList()));
        hcfg.getSeries().add(menzhens);
        //hcfg.getSeries().add(jizhens);
        return hcfg;
    }
}
