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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
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
        String double_putong = collect.get(YZCXConstant.putong).toString();
        String double_jizhen = collect.get(YZCXConstant.jizhen).toString();
        rs.setPutong(Double.valueOf(double_putong));
        rs.setJizhen(Double.valueOf(double_jizhen));
        return rs;
    }

    @Override
    public HighchartsConfig_arr getQygl_riChart() throws ParseException {
        Date startTime = LdgDateUtil.getDayZeroTime();
        List<YzcxHandleInfoDay> list = yzcxHandleInfoDayMapper.selectByDate(startTime, LdgDateUtil.getDayLastTime());
        HighchartsConfig_arr hcfg = HighChartUtils.createArrBasicChat("", "单位：人");
        //hcfg.getxAxis().setCategories(Arrays.asList(YZCXConstant.dayHours));
        hcfg.getxAxis().setType("datetime");
        DateTimeLabelFormats dlf = new DateTimeLabelFormats();
        dlf.setHour("%H");
        dlf.setDay("%H");
        hcfg.getxAxis().setDateTimeLabelFormats(dlf);
        Tooltip tooltip = new Tooltip();
        tooltip.setPointFormat("<span style=\"color:{series.color}\">{series.name}</span>: <b>{point.y}人</b><br/>");
        hcfg.setTooltip(tooltip);
        Series_arr menzhens = new Series_arr();
        Series_arr jizhens = new Series_arr();
        Map<String, List<YzcxHandleInfoDay>> collect = list.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName));
        List<YzcxHandleInfoDay> putong = collect.get(YZCXConstant.putong);
        List<YzcxHandleInfoDay> jizhen = collect.get(YZCXConstant.jizhen);
        menzhens.setName(YZCXConstant.putong);
        menzhens.setPointStart(startTime.getTime());//
        menzhens.setPointInterval(3600 * 1000L);//跨度1个小时
        menzhens.setData(getDisData(putong));
        hcfg.getSeries().add(menzhens);
        jizhens.setName(YZCXConstant.jizhen);
        jizhens.setPointStart(startTime.getTime());//
        jizhens.setPointInterval(3600 * 1000L);//跨度1个小时
        jizhens.setData(getDisData(jizhen));
        hcfg.getSeries().add(jizhens);
        return hcfg;
    }

    private List<Series_Data> getDisData(List<YzcxHandleInfoDay> handleData) {
        if (handleData != null && handleData.size() != 0) {
            Map<Integer, YzcxHandleInfoDay> map = new HashMap<>();
            handleData.forEach(item -> {
                map.put(LdgDateUtil.getHourNum(item.getHandledate()), item);
            });
            YzcxHandleInfoDay yzcxHandleInfoDay = handleData.get(handleData.size() - 1);
            Integer hourNum = LdgDateUtil.getHourNum(yzcxHandleInfoDay.getHandledate());
            List<Series_Data> series_data = new ArrayList<Series_Data>();
            for (Integer i = 0; i <= hourNum; i++) {
                Series_Data data = new Series_Data();
                YzcxHandleInfoDay mapVal = map.get(i);
                if (mapVal != null) {
                    data.setX(mapVal.getHandledate().getTime());
                    data.setY(mapVal.getCount().longValue());
                } else {
                    data.setX(LdgDateUtil.getTimeByHH(i));
                    data.setY(0L);
                }
                series_data.add(data);
            }
            return series_data;
        }
        return null;
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        //Date date = Date.from(localDateTime.toInstant(ZoneOffset.UTC));
        System.out.println(localDateTime);

    }
}
