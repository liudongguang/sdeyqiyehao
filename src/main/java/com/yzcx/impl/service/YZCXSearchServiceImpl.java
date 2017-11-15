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
        if(collect.size()>0){
            String double_putong = collect.get(YZCXConstant.putong).toString();
            String double_jizhen = collect.get(YZCXConstant.jizhen).toString();
            rs.setPutong(Double.valueOf(double_putong));
            rs.setJizhen(Double.valueOf(double_jizhen));
        }
        List<YzcxHandleInfoDay> yuyuelist = yzcxHandleInfoDayMapper.selectYuYueByDate(LdgDateUtil.getDayZeroTime(), LdgDateUtil.getDayLastTime());
        Double yuyuesum = yuyuelist.stream().collect(Collectors.summingDouble(YzcxHandleInfoDay::getCount));
        rs.setYuyueshu(yuyuesum);
        return rs;
    }

    @Override
    public HighchartsConfig_arr getQygl_riChart(int chartType) throws ParseException {
        Date startTime = LdgDateUtil.getDayZeroTime();
        List<YzcxHandleInfoDay> list = yzcxHandleInfoDayMapper.selectByDate(startTime, LdgDateUtil.getDayLastTime());
        HighchartsConfig_arr hcfg = HighChartUtils.createArrBasicChat("", "单位：人");
        if(chartType==2){
            hcfg.getChart().setType("column");
        }
        //hcfg.getxAxis().setCategories(Arrays.asList(YZCXConstant.dayHours));
        hcfg.getxAxis().setType("datetime");
        DateTimeLabelFormats dlf = new DateTimeLabelFormats();
        dlf.setHour("%H");
        dlf.setDay("%H");
        hcfg.getxAxis().setDateTimeLabelFormats(dlf);
        hcfg.getxAxis().setTickInterval(3600*1000);
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

    @Override
    public HighchartsConfig getYuyue_riChart() throws ParseException {
        List<YzcxHandleInfoDay> list = yzcxHandleInfoDayMapper.selectYuYueByDate(LdgDateUtil.getDayZeroTime(), LdgDateUtil.getDayLastTime());
         if(list!=null&&list.size()>0) {
             HighchartsConfig hcfg = HighChartUtils.createBasicChat("", "单位：人", "bar");
             XAxis xAxis = hcfg.getxAxis();
             List<String> categories = new ArrayList<>();
             YAxis yAxis = hcfg.getyAxis();
             yAxis.getTitle().setText("单位：人");
             hcfg.getTooltip().setPointFormat("{series.name}:{point.y} 人");
             PlotOptions plotOptions = new PlotOptions();
             plotOptions.setColumn(null);
             plotOptions.setSpline(null);
             hcfg.setPlotOptions(plotOptions);
             List<Series> series = hcfg.getSeries();
             int maxIndex = list.size() > 9?10:list.size()-1;
             Series series1=new Series();
             series1.setName("预约");
             List<Integer> series1_Data=new ArrayList<>();
             for(int i=0;i<=maxIndex;i++){
                 YzcxHandleInfoDay yzcxHandleInfoDay = list.get(i);
                 categories.add(yzcxHandleInfoDay.getName());
                 series1_Data.add(yzcxHandleInfoDay.getCount().intValue());
             }
             xAxis.setCategories(categories);
             series1.setData(series1_Data);
             series.add(series1);
             return hcfg;
         }
        return null;
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        //Date date = Date.from(localDateTime.toInstant(ZoneOffset.UTC));
        System.out.println(localDateTime);

    }
}
