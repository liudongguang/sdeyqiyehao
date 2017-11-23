package com.yzcx.impl.service;

import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.po.YzcxHandleInfoDay;
import com.yzcx.api.po.YzcxHandleInfoMonth;
import com.yzcx.api.service.YZCXSearchService;
import com.yzcx.api.util.HighChartUtils;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.highchat.*;
import com.yzcx.api.vo.highchat.bar.HighchartsConfig_bar;
import com.yzcx.api.vo.highchat.bar.PlotOptions_bar_series;
import com.yzcx.api.vo.highchat.bar.Series_bar;
import com.yzcx.api.vo.highchat.column.HighchartsConfig_column;
import com.yzcx.api.vo.highchat.column.PlotOptions_column_series;
import com.yzcx.api.vo.highchat.column.Series_column;
import com.yzcx.api.vo.highchat.pie.HighchartsConfig_pie;
import com.yzcx.api.vo.highchat.pie.Series_pie;
import com.yzcx.api.vo.highchat.pie.Series_pie_data;
import com.yzcx.api.vo.yzcxdisplay.Menzhen_Month_Yuyue;
import com.yzcx.api.vo.yzcxdisplay.QyglVo;
import com.yzcx.api.vo.yzcxdisplay.YzcxHandleInfoExt;
import com.yzcx.api.vo.yzcxdisplay.YzcxHandleInfoMonthExt;
import com.yzcx.impl.mapper.YzcxHandleImportdateMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoDayMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMonthMapper;
import com.yzcx.impl.service.handler.YzcxHandleInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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
        YZCXSearchParam param = YZCXControllerUtil.getSearchParamForDay();
        param.setHandletype(Arrays.asList(YZCXConstant.menzhen_sfjz));
        List<YzcxHandleInfoDay> list = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        Map<String, Double> collect = list.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName, Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        if (collect.size() > 0) {
            String double_putong = collect.get(YZCXConstant.putong).toString();
            String double_jizhen = collect.get(YZCXConstant.jizhen).toString();
            rs.setPutong(Double.valueOf(double_putong));
            rs.setJizhen(Double.valueOf(double_jizhen));
        }
        param.setHandletype(Arrays.asList(YZCXConstant.yuyue_ks));
        List<YzcxHandleInfoDay> yuyuelist = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        ;
        if (yuyuelist.size() > 0) {
            Double yuyuesum = yuyuelist.stream().collect(Collectors.summingDouble(YzcxHandleInfoDay::getCount));
            rs.setYuyueshu(yuyuesum);
        }
        return rs;
    }

    @Override
    public HighchartsConfig_arr getQygl_riChart(int chartType) throws ParseException {
        YZCXSearchParam param = YZCXControllerUtil.getSearchParamForDay();
        param.setHandletype(Arrays.asList(YZCXConstant.menzhen_sfjz));
        List<YzcxHandleInfoDay> list = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        HighchartsConfig_arr hcfg = HighChartUtils.createArrBasicChat("", "单位：人");
        if (chartType == 2) {
            hcfg.getChart().setType("column");
        }
        //hcfg.getxAxis().setCategories(Arrays.asList(YZCXConstant.dayHours));
        hcfg.getxAxis().setType("datetime");
        DateTimeLabelFormats dlf = new DateTimeLabelFormats();
        dlf.setHour("%H");
        dlf.setDay("%H");
        hcfg.getxAxis().setDateTimeLabelFormats(dlf);
        hcfg.getxAxis().setTickInterval(3600 * 1000);
        Tooltip tooltip = new Tooltip();
        tooltip.setPointFormat("<span style=\"color:{series.color}\">{series.name}</span>: <b>{point.y}人</b><br/>");
        hcfg.setTooltip(tooltip);
        Series_arr menzhens = new Series_arr();
        Series_arr jizhens = new Series_arr();
        Map<String, List<YzcxHandleInfoDay>> collect = list.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName));
        List<YzcxHandleInfoDay> putong = collect.get(YZCXConstant.putong);
        List<YzcxHandleInfoDay> jizhen = collect.get(YZCXConstant.jizhen);
        menzhens.setName(YZCXConstant.putong);
        menzhens.setPointStart(param.getStart().getTime());//
        menzhens.setPointInterval(3600 * 1000L);//跨度1个小时
        menzhens.setData(getDisData(putong));
        hcfg.getSeries().add(menzhens);
        jizhens.setName(YZCXConstant.jizhen);
        jizhens.setPointStart(param.getStart().getTime());//
        jizhens.setPointInterval(3600 * 1000L);//跨度1个小时
        jizhens.setData(getDisData(jizhen));
        hcfg.getSeries().add(jizhens);
        return hcfg;
    }

    private List<Series_Data> getDisData(List<YzcxHandleInfoDay> handleData) {
        if (handleData != null && handleData.size() != 0) {
            //Collections.sort(handleData,Comparator.comparing(YzcxHandleInfoDay::getHandledate));//排序
            Map<Integer, YzcxHandleInfoDay> map = new HashMap<>();
            handleData.forEach(item -> {
                map.put(LdgDateUtil.getHourNum(item.getHandledate()), item);
            });
            YzcxHandleInfoDay yzcxHandleInfoDay = handleData.get(handleData.size() - 1);//获取最大的小时数
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

    private HighchartsConfig getYuyueKsChart(YZCXSearchParam param) {
        List<YzcxHandleInfoDay> list = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        if (list != null && list.size() > 0) {
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
            ////// 计算总和获取前十名
            Map<String, Double> collect = list.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName, Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
            List<YzcxHandleInfoDay> tempList = new ArrayList<>();
            collect.forEach((k, v) -> {
                YzcxHandleInfoDay yzd = new YzcxHandleInfoDay();
                yzd.setCount(v);
                yzd.setName(k);
                tempList.add(yzd);
            });
            Collections.sort(tempList, Comparator.comparingDouble(YzcxHandleInfoDay::getCount).reversed());
            /////
            int maxIndex = tempList.size() > 9 ? 9 : list.size() - 1;
            Series series1 = new Series();
            series1.setName("预约");
            List<Integer> series1_Data = new ArrayList<>();
            for (int i = 0; i <= maxIndex; i++) {
                YzcxHandleInfoDay yzcxHandleInfoDay = tempList.get(i);
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

    @Override
    public HighchartsConfig getYuyue_riChart() throws ParseException {
        YZCXSearchParam param = YZCXControllerUtil.getSearchParamForDay();
        param.setHandletype(Arrays.asList(YZCXConstant.yuyue_ks));
        return getYuyueKsChart(param);
    }

    @Override
    public HighchartsConfig getJiBing_riChart() throws ParseException {
        YZCXSearchParam yzcxSearchParam = YZCXControllerUtil.getSearchParamForDay();
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.jbzd_jb));//
        List<YzcxHandleInfoDay> jzlist = yzcxHandleInfoDayMapper.selectByDateAndType(yzcxSearchParam);
        Map<String, Double> jbzdmap = jzlist.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName, Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        jzlist.clear();
        jbzdmap.forEach((jbname, count) -> {
            YzcxHandleInfoDay yzcxHandleInfoDay = new YzcxHandleInfoDay();
            yzcxHandleInfoDay.setName(jbname);
            yzcxHandleInfoDay.setCount(count);
            jzlist.add(yzcxHandleInfoDay);
        });
        if (jzlist != null && jzlist.size() > 0) {
            Collections.sort(jzlist, Comparator.comparingDouble(YzcxHandleInfoDay::getCount).reversed());
            HighchartsConfig hcfg = HighChartUtils.createBasicChat("", "单位：人", "bar");
            XAxis xAxis = hcfg.getxAxis();
            List<String> categories = new ArrayList<>();
            xAxis.setCategories(categories);
            YAxis yAxis = hcfg.getyAxis();
            yAxis.getTitle().setText("单位：人");
            hcfg.getTooltip().setPointFormat("{series.name}:{point.y} 人");
            PlotOptions plotOptions = new PlotOptions();
            plotOptions.setColumn(null);
            plotOptions.setSpline(null);
            hcfg.setPlotOptions(plotOptions);
            List<Series> series = hcfg.getSeries();
            Series series1 = new Series();
            series1.setName("诊断");
            List<Integer> series1_Data = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                YzcxHandleInfoDay yzcxHandleInfoDay = jzlist.get(i);
                categories.add(yzcxHandleInfoDay.getName());
                series1_Data.add(yzcxHandleInfoDay.getCount().intValue());
            }
            series1.setData(series1_Data);
            series.add(series1);
            return hcfg;
        }
        return null;
    }

    @Override
    public QyglVo getQygl_month(YZCXSearchParam cparam) throws ParseException {
        cparam.setHandletype(Arrays.asList(YZCXConstant.menzhen_sfjz));//获取普通，急诊
        List<YzcxHandleInfoMonth> list = yzcxHandleInfoMonthMapper.selectByDateAndType(cparam);
        Map<String, Double> collect = list.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getName, Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        if (collect.size() > 0) {
            QyglVo rs = new QyglVo();
            String double_putong = collect.get(YZCXConstant.putong).toString();
            String double_jizhen = collect.get(YZCXConstant.jizhen).toString();
            rs.setPutong(Double.valueOf(double_putong));
            rs.setJizhen(Double.valueOf(double_jizhen));
            rs.setParam(cparam);
            return rs;
        }
        return null;
    }

    @Override
    public HighchartsConfig getQygl_yueChart(YZCXSearchParam cparam) throws ParseException {
        cparam.setHandletype(Arrays.asList(YZCXConstant.jbzd_ks_jizhen, YZCXConstant.jbzd_ks_menzhen));//获取普通，急诊
        List<YzcxHandleInfoMonth> jzlist = yzcxHandleInfoMonthMapper.selectByDateAndType(cparam);
        if (jzlist != null && jzlist.size() > 0) {
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
            final Map<String, Map<Integer, Double>> collect = jzlist.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getName, Collectors.groupingBy(YzcxHandleInfoMonth::getHandletype, Collectors.summingDouble(YzcxHandleInfoMonth::getCount))));
            /////
            // int maxIndex = tempList.size() > 9?10:list.size()-1;
            Series series1 = new Series();
            series1.setName("门诊");
            Series series2 = new Series();
            series2.setName("急诊");
            List<Integer> series1_Data = new ArrayList<>();
            List<Integer> series2_Data = new ArrayList<>();
            ///排序
            List<YzcxHandleInfoMonth> tempsortList = new ArrayList<>();
            collect.forEach((ksname, v) -> {
                v.forEach((type, sum) -> {
                    YzcxHandleInfoMonth yzcxHandleInfoMonth = new YzcxHandleInfoMonth();
                    yzcxHandleInfoMonth.setCount(sum);
                    yzcxHandleInfoMonth.setHandletype(type);
                    yzcxHandleInfoMonth.setName(ksname);
                    tempsortList.add(yzcxHandleInfoMonth);
                });
            });
            Collections.sort(tempsortList, Comparator.comparingDouble(YzcxHandleInfoMonth::getCount).reversed());
            for(int i=0;i<(tempsortList.size()>10?10:tempsortList.size());i++){
                YzcxHandleInfoMonth item=tempsortList.get(i);
                categories.add(item.getName());
                if (item.getHandletype() == YZCXConstant.jbzd_ks_menzhen) {
                    series1_Data.add(item.getCount().intValue());
                    series2_Data.add(null);
                } else {
                    series2_Data.add(item.getCount().intValue());
                    series1_Data.add(null);
                }
            }
            xAxis.setCategories(categories);
            series1.setData(series1_Data);
            series2.setData(series2_Data);
            series.add(series1);
            series.add(series2);
            return hcfg;
        }
        return null;
    }

    @Override
    public HighchartsConfig getQygl_yueChart_tongqimenzhen(YZCXSearchParam cparam) throws ParseException {
        cparam.setHandletype(Arrays.asList(YZCXConstant.jbzd_ks_menzhen, YZCXConstant.jbzd_ks_jizhen));//获取普通，急诊
        List<YzcxHandleInfoMonth> currentlist = yzcxHandleInfoMonthMapper.selectByDateAndType(cparam);
        if (currentlist.size() == 0) {
            return null;
        }
        String currentDateStr = LdgDateUtil.get_zhongwen_yyyyMM(cparam.getStart());
        cparam = YZCXControllerUtil.getSearchParamBeforeOneYear(cparam);//获取前一年同月日期
        String qunianDateStr = LdgDateUtil.get_zhongwen_yyyyMM(cparam.getStart());
        List<YzcxHandleInfoMonth> qunianlist = yzcxHandleInfoMonthMapper.selectByDateAndType(cparam);
        if (qunianlist.size() == 0) {
            return null;
        }
        Map<Integer, Double> currentData = currentlist.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getHandletype, Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        Map<Integer, Double> qunianData = qunianlist.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getHandletype, Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        HighchartsConfig hcfg = HighChartUtils.createBasicChat("", "单位：人", "column");
        XAxis xAxis = hcfg.getxAxis();
        List<String> categories = new ArrayList<>();
        categories.add(qunianDateStr);
        categories.add(currentDateStr);
        xAxis.setCategories(categories);//x轴设置完毕
        YAxis yAxis = hcfg.getyAxis();
        yAxis.getTitle().setText("单位：人");
        hcfg.getTooltip().setPointFormat("{series.name}:{point.y} 人");
        PlotOptions plotOptions = new PlotOptions();
        plotOptions.setColumn(null);
        plotOptions.setSpline(null);
        hcfg.setPlotOptions(plotOptions);
        List<Series> series = hcfg.getSeries();
        Series series1 = new Series();
        series1.setName("门诊");
        Series series2 = new Series();
        series2.setName("急诊");
        List<Integer> series1_Data = new ArrayList<>();
        List<Integer> series2_Data = new ArrayList<>();
        series1_Data.add(qunianData.get(YZCXConstant.jbzd_ks_menzhen).intValue());
        series1_Data.add(currentData.get(YZCXConstant.jbzd_ks_menzhen).intValue());
        series2_Data.add(qunianData.get(YZCXConstant.jbzd_ks_jizhen).intValue());
        series2_Data.add(currentData.get(YZCXConstant.jbzd_ks_jizhen).intValue());
        series1.setData(series1_Data);
        series2.setData(series2_Data);
        series.add(series1);
        series.add(series2);
        return hcfg;
    }

    @Override
    public HighchartsConfig getQygl_yueChart_jibing(YZCXSearchParam param) throws ParseException {
        param.setHandletype(Arrays.asList(YZCXConstant.jbzd_jb));//
        List<YzcxHandleInfoMonth> jzlist = yzcxHandleInfoMonthMapper.selectByDateAndType(param);
        if (jzlist != null && jzlist.size() > 0) {
            Collections.sort(jzlist, Comparator.comparingDouble(YzcxHandleInfoMonth::getCount).reversed());
            HighchartsConfig hcfg = HighChartUtils.createBasicChat("", "单位：人", "bar");
            XAxis xAxis = hcfg.getxAxis();
            List<String> categories = new ArrayList<>();
            xAxis.setCategories(categories);
            YAxis yAxis = hcfg.getyAxis();
            yAxis.getTitle().setText("单位：人");
            hcfg.getTooltip().setPointFormat("{series.name}:{point.y} 人");
            PlotOptions plotOptions = new PlotOptions();
            plotOptions.setColumn(null);
            plotOptions.setSpline(null);
            hcfg.setPlotOptions(plotOptions);
            List<Series> series = hcfg.getSeries();
            Series series1 = new Series();
            series1.setName("诊断");
            List<Integer> series1_Data = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                YzcxHandleInfoMonth yzcxHandleInfoMonth = jzlist.get(i);
                categories.add(yzcxHandleInfoMonth.getName());
                series1_Data.add(yzcxHandleInfoMonth.getCount().intValue());
            }
            series1.setData(series1_Data);
            series.add(series1);
            return hcfg;
        }
        return null;
    }

    @Override
    public HighchartsConfig_bar getEveryDayOneMonthChart(YZCXSearchParam yzcxSearchParam) {
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.menzhen_sfjz));
        List<YzcxHandleInfo> monthDayData = yzcxHandleInfoMapper.selectByDateAndType(yzcxSearchParam); //获取一月中每天的信息
        Map<String, Map<String, List<YzcxHandleInfoExt>>> collect = monthDayData.stream().map(item -> {
            return YzcxHandleInfoFactory.createYzcxHandleInfoExtForEveryDay(item.getHandledate(),item.getCount(),item.getName());
        }).collect(Collectors.groupingBy(YzcxHandleInfoExt::getHandledateStr,LinkedHashMap::new,Collectors.groupingBy(YzcxHandleInfoExt::getName)));
        //获取每天的统计信息
        HighchartsConfig_bar hcfg = new HighchartsConfig_bar();
        XAxis xAxis = hcfg.getxAxis();
        List<String> categories = new ArrayList<>();
        xAxis.setCategories(categories);
        YAxis yAxis = hcfg.getyAxis();
        yAxis.getTitle().setText("单位：人");
        PlotOptions_bar_series plotOptions_bar_series= new PlotOptions_bar_series();
        plotOptions_bar_series.setStacking("normal"); //叠加
        hcfg.getPlotOptions().setSeries(plotOptions_bar_series);
        List<Series_bar> series = hcfg.getSeries();
        Series_bar seriesData_menzhen=new Series_bar();
        Series_bar seriesData_jizhen=new Series_bar();
        seriesData_menzhen.setName(YZCXConstant.putong);
        seriesData_jizhen.setName(YZCXConstant.jizhen);
        List<? super Number> menzhenData = seriesData_menzhen.getData();
        List<? super Number> jizhendata = seriesData_jizhen.getData();
        collect.forEach((date,map)->{
            categories.add(date);
            map.forEach((handletyeName,obj)->{
                if(handletyeName.equals(YZCXConstant.jizhen)){
                    jizhendata.add(obj.get(0).getCount());
                }else if(handletyeName.equals(YZCXConstant.putong)){
                    menzhenData.add(obj.get(0).getCount());
                }
            });
        });
        series.add(seriesData_menzhen);
        series.add(seriesData_jizhen);
        return hcfg;
    }

    @Override
    public Menzhen_Month_Yuyue getMenzhen_Month_Yuyue_month(YZCXSearchParam param) throws ParseException {
        Menzhen_Month_Yuyue rs = new Menzhen_Month_Yuyue();
        param.setHandletype(Arrays.asList(YZCXConstant.menzhen));//获取门诊
        List<YzcxHandleInfoMonth> list = yzcxHandleInfoMonthMapper.selectByDateAndType(param);
        if (list.size() > 0) {
            Double menzhensum = list.stream().collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount));
            rs.setZongmenzhen(menzhensum);
        }
        param.setHandletype(Arrays.asList(YZCXConstant.yuyue_ks));
        List<YzcxHandleInfoMonth> yuyuelist = yzcxHandleInfoMonthMapper.selectByDateAndType(param);
        if (yuyuelist.size() > 0) {
            Double yuyuesum = yuyuelist.stream().collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount));
            rs.setYuyue(yuyuesum);
        }
        rs.setParam(param);
        return rs;
    }

    @Override
    public HighchartsConfig_pie getMenzhenYuyueZhanbi_yueChart(Menzhen_Month_Yuyue menzhen_month_yuyue) {
        HighchartsConfig_pie hcfg = new HighchartsConfig_pie();
        hcfg.getTitle().setText("月预约占门诊比例图");
        List<Series_pie> series = hcfg.getSeries();
        Series_pie series1 = new Series_pie();
        series.add(series1);
        Series_pie_data data1 = new Series_pie_data();
        Series_pie_data data2 = new Series_pie_data();
        data1.setName("门诊挂号");
        data2.setName("门诊预约");
        double yymzbili = menzhen_month_yuyue.getYuyue() / menzhen_month_yuyue.getZongmenzhen();
        data1.setY(1 - yymzbili);
        data2.setY(yymzbili);
        data1.setLdgnumber(menzhen_month_yuyue.getMenzhenGuaHao());
        data2.setLdgnumber(menzhen_month_yuyue.getYuyue());
        series1.getData().add(data1);
        series1.getData().add(data2);
        return hcfg;
    }

    @Override
    public HighchartsConfig getMenzhenYuyue_yueChart(YZCXSearchParam yzcxSearchParam) {
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.yuyue_ks));
        List<YzcxHandleInfoMonth> list = yzcxHandleInfoMonthMapper.selectByDateAndType(yzcxSearchParam);
        if (list != null && list.size() > 0) {
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
            ////// 计算总和获取前十名
            Map<String, Double> collect = list.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getName, Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
            List<YzcxHandleInfoMonth> tempList = new ArrayList<>();
            collect.forEach((k, v) -> {
                YzcxHandleInfoMonth yzd = new YzcxHandleInfoMonth();
                yzd.setCount(v);
                yzd.setName(k);
                tempList.add(yzd);
            });
            Collections.sort(tempList, Comparator.comparingDouble(YzcxHandleInfoMonth::getCount).reversed());
            /////
            int maxIndex = tempList.size() > 9 ? 9 : list.size() - 1;
            Series series1 = new Series();
            series1.setName("预约");
            List<Integer> series1_Data = new ArrayList<>();
            for (int i = 0; i <= maxIndex; i++) {
                YzcxHandleInfoMonth yzcxHandleInfoMonth = tempList.get(i);
                categories.add(yzcxHandleInfoMonth.getName());
                series1_Data.add(yzcxHandleInfoMonth.getCount().intValue());
            }
            xAxis.setCategories(categories);
            series1.setData(series1_Data);
            series.add(series1);
            return hcfg;
        }
        return null;
    }

    @Override
    public HighchartsConfig getTongqiyuyueChart(YZCXSearchParam yzcxSearchParam) throws ParseException {
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.yuyue_ks));//获取普通，急诊
        List<YzcxHandleInfoMonth> currentlist = yzcxHandleInfoMonthMapper.selectByDateAndType(yzcxSearchParam);
        if (currentlist.size() == 0) {
            return null;
        }
        String currentDateStr = LdgDateUtil.get_zhongwen_yyyyMM(yzcxSearchParam.getStart());
        yzcxSearchParam = YZCXControllerUtil.getSearchParamBeforeOneYear(yzcxSearchParam);//获取前一年同月日期
        String qunianDateStr = LdgDateUtil.get_zhongwen_yyyyMM(yzcxSearchParam.getStart());
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.yuyue_ks));//获取普通，急诊
        List<YzcxHandleInfoMonth> qunianlist = yzcxHandleInfoMonthMapper.selectByDateAndType(yzcxSearchParam);
        if (qunianlist.size() == 0) {
            return null;
        }
        HighchartsConfig hcfg = HighChartUtils.createBasicChat("", "单位：人", "column");
        XAxis xAxis = hcfg.getxAxis();
        List<String> categories = new ArrayList<>();
        categories.add(qunianDateStr);
        categories.add(currentDateStr);
        xAxis.setCategories(categories);//x轴设置完毕
        YAxis yAxis = hcfg.getyAxis();
        yAxis.getTitle().setText("单位：人");
        hcfg.getTooltip().setPointFormat("{series.name}:{point.y} 人");
        PlotOptions plotOptions = new PlotOptions();
        plotOptions.setBar(null);
        plotOptions.setSpline(null);
        hcfg.setPlotOptions(plotOptions);
        List<Series> series = hcfg.getSeries();
        Series series1 = new Series();
        series1.setName("预约");
        List<Integer> series1_Data = new ArrayList<>();
        ///
        final Double qunianSum = qunianlist.stream().collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount));
        final Double jinnianSum = currentlist.stream().collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount));
        ///
        series1_Data.add(qunianSum.intValue());
        series1_Data.add(jinnianSum.intValue());
        series1.setData(series1_Data);
        series.add(series1);
        return hcfg;
    }

    @Override
    public HighchartsConfig_column getMenzhen_year_chart(YZCXSearchParam param) {
        param.setHandletype(Arrays.asList(YZCXConstant.menzhen_sfjz));//获取普通，急诊
        List<YzcxHandleInfoMonth> list = yzcxHandleInfoMonthMapper.selectByDateAndType(param);
        final Map<String, List<YzcxHandleInfoMonth>> menjizhen = list.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getName));
        List<YzcxHandleInfoMonth> putong = menjizhen.get(YZCXConstant.putong);
        List<YzcxHandleInfoMonth> jizhen = menjizhen.get(YZCXConstant.jizhen);
        Map<String, Double> putongmenzhenMap = putong.stream().map(item -> {
            YzcxHandleInfoMonthExt yzcxHandleInfoMonthExt = new YzcxHandleInfoMonthExt();
            yzcxHandleInfoMonthExt.setSumNum(item.getCount());
            yzcxHandleInfoMonthExt.setMonthStr(LdgDateUtil.getMonthHanzi(item.getHandledate()));
            return yzcxHandleInfoMonthExt;
        }).collect(Collectors.toMap(YzcxHandleInfoMonthExt::getMonthStr, YzcxHandleInfoMonthExt::getSumNum));

        Map<String, Double> jizhenzhenMap = jizhen.stream().map(item -> {
            YzcxHandleInfoMonthExt yzcxHandleInfoMonthExt = new YzcxHandleInfoMonthExt();
            yzcxHandleInfoMonthExt.setMonthStr(LdgDateUtil.getMonthHanzi(item.getHandledate()));
            yzcxHandleInfoMonthExt.setSumNum(item.getCount());
            return yzcxHandleInfoMonthExt;
        }).collect(Collectors.toMap(YzcxHandleInfoMonthExt::getMonthStr, YzcxHandleInfoMonthExt::getSumNum));
        HighchartsConfig_column hcfg = new HighchartsConfig_column();
        XAxis xAxis = hcfg.getxAxis();
        List<String> categories = new ArrayList<>();
        xAxis.setCategories(categories);
        YAxis yAxis = hcfg.getyAxis();
        yAxis.getTitle().setText("单位：人");
        PlotOptions_column_series plotOptions_column_series= new PlotOptions_column_series();
        plotOptions_column_series.setStacking("normal"); //叠加
        hcfg.getPlotOptions().setSeries(plotOptions_column_series);
        List<Series_column> series = hcfg.getSeries();
        Series_column seriesData_menzhen=new Series_column();
        Series_column seriesData_jizhen=new Series_column();
        seriesData_menzhen.setName(YZCXConstant.putong);
        seriesData_jizhen.setName(YZCXConstant.jizhen);
        List<? super Number> menzhenData = seriesData_menzhen.getData();
        List<? super Number> jizhendata = seriesData_jizhen.getData();
        Arrays.asList(YZCXConstant.months).forEach(month -> {
            Double menzhenSum = putongmenzhenMap.get(month);
            Double jizhenSum = jizhenzhenMap.get(month);
            categories.add(month);
            menzhenData.add(menzhenSum);
            jizhendata.add(jizhenSum);
        });
        series.add(seriesData_menzhen);
        series.add(seriesData_jizhen);
        return hcfg;
    }

    @Override
    public HighchartsConfig_column getMenzhenTongqi_year_chart(YZCXSearchParam yzcxSearchParam) throws ParseException {
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.jbzd_ks_menzhen, YZCXConstant.jbzd_ks_jizhen));//获取普通，急诊
        List<YzcxHandleInfoMonth> currentlist = yzcxHandleInfoMonthMapper.selectByDateAndType(yzcxSearchParam);
        if (currentlist.size() == 0) {
            return null;
        }
        String currentDateStr = LdgDateUtil.getYearHanzi(yzcxSearchParam.getStart());
        yzcxSearchParam=YZCXControllerUtil.getSearchParamBeforeOneYear(yzcxSearchParam);//获取前一年同月日期
        yzcxSearchParam.setHandletype(Arrays.asList(YZCXConstant.jbzd_ks_menzhen, YZCXConstant.jbzd_ks_jizhen));//获取普通，急诊
        String qunianDateStr = LdgDateUtil.getYearHanzi(yzcxSearchParam.getStart());
        List<YzcxHandleInfoMonth> qunianlist = yzcxHandleInfoMonthMapper.selectByDateAndType(yzcxSearchParam);
        if (qunianlist.size() == 0) {
            return null;
        }
        Map<Integer, Double> currentData = currentlist.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getHandletype, Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        Map<Integer, Double> qunianData = qunianlist.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getHandletype, Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        HighchartsConfig_column hcfg = new HighchartsConfig_column();
        XAxis xAxis = hcfg.getxAxis();
        List<String> categories = new ArrayList<>();
        categories.add(qunianDateStr);
        categories.add(currentDateStr);
        xAxis.setCategories(categories);//x轴设置完毕
        YAxis yAxis = hcfg.getyAxis();
        yAxis.getTitle().setText("单位：人");
        List<Series_column> series = hcfg.getSeries();
        Series_column seriesData_menzhen=new Series_column();
        Series_column seriesData_jizhen=new Series_column();
        seriesData_menzhen.setName(YZCXConstant.putong);
        seriesData_jizhen.setName(YZCXConstant.jizhen);
        List<? super Number> menzhenData = seriesData_menzhen.getData();
        List<? super Number> jizhendata = seriesData_jizhen.getData();
        menzhenData.add(qunianData.get(YZCXConstant.jbzd_ks_menzhen).intValue());
        menzhenData.add(currentData.get(YZCXConstant.jbzd_ks_menzhen).intValue());
        jizhendata.add(qunianData.get(YZCXConstant.jbzd_ks_jizhen).intValue());
        jizhendata.add(currentData.get(YZCXConstant.jbzd_ks_jizhen).intValue());
        series.add(seriesData_menzhen);
        series.add(seriesData_jizhen);
        return hcfg;
    }
}
