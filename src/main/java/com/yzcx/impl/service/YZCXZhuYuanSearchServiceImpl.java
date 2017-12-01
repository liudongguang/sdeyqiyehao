package com.yzcx.impl.service;

import com.github.abel533.echarts.json.GsonOption;
import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.po.YzcxHandleInfoDay;
import com.yzcx.api.po.YzcxHandleInfoMonth;
import com.yzcx.api.service.YZCXCommonService;
import com.yzcx.api.service.YZCXZhuYuanSearchService;
import com.yzcx.api.util.EchartsBuilder;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.YzcxHandleInfoDayExt;
import com.yzcx.api.vo.yzcxdisplay.ZyxxIndex;
import com.yzcx.api.vo.yzcxdisplay.ZyxxKeshiChuanwei;
import com.yzcx.impl.mapper.YzcxHandleInfoDayMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMonthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class YZCXZhuYuanSearchServiceImpl implements YZCXZhuYuanSearchService {
    @Autowired
    private YzcxHandleInfoDayMapper yzcxHandleInfoDayMapper;
    @Autowired
    private YzcxHandleInfoMapper yzcxHandleInfoMapper;
    @Autowired
    private YzcxHandleInfoMonthMapper yzcxHandleInfoMonthMapper;
    @Autowired
    private YZCXCommonService yzcxCommonService;

    @Override
    public ZyxxIndex getIndexZhuYuanForDay(YZCXSearchParam param) throws ParseException {
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
        index.setParam(param);
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
            index.setWeizhong(bws + bzs);
        }
        if (cyfs != null) {
            Map<String, Double> cyfsMap = cyfs.stream().collect(Collectors.toMap(YzcxHandleInfoDay::getName, YzcxHandleInfoDay::getCount));
            Double sws = cyfsMap.get(YZCXConstant.zhuyuan_cyfs_siwang);
            index.setSiwang(sws);
        }
        if (zhuanchuks != null) {
            index.setZhuanchu(zhuanchuks.stream().collect(Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        }
        if (zhuanruks != null) {
            index.setZhuanchu(zhuanruks.stream().collect(Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
        }
        /////////////////////////////////////////////////////////////////获取前一天的床位信息
        YZCXSearchParam beforeDayparam= YZCXControllerUtil.getBeforeDayByNum(1);
        beforeDayparam.setHandletype(Arrays.asList(YZCXConstant.zhuyuan_keshishizhan,YZCXConstant.zhuyuan_keshikaifang));
        final List<YzcxHandleInfo> chuangweiList = yzcxHandleInfoMapper.selectByDateAndType(beforeDayparam);
        final Map<Integer, Double> keshiChuangweiMap = chuangweiList.stream().collect(Collectors.groupingBy(YzcxHandleInfo::getHandletype, Collectors.summingDouble(YzcxHandleInfo::getCount)));
        Double shizhan = keshiChuangweiMap.get(YZCXConstant.zhuyuan_keshishizhan);
        Double kaifang = keshiChuangweiMap.get(YZCXConstant.zhuyuan_keshikaifang);
        index.setShizhan(shizhan);
        index.setKaifang(kaifang);
        if(shizhan!=0&&kaifang!=0){
            index.setCwshiyonglv(shizhan/kaifang);
        }
        return index;
    }

    @Override
    public Map<String, Object> getIndexChart(YZCXSearchParam param) throws ParseException {
        List<Integer> zyxxType = Arrays.asList(YZCXConstant.zhuyuan_keshiruyuan);
        param.setHandletype(zyxxType);
        final List<YzcxHandleInfoDay> keshiruyuanList = yzcxHandleInfoDayMapper.selectByDateAndType(param);
        Map<String, Object> rs = new HashMap<>();
        if (keshiruyuanList != null) {
            Map<String, Double> ruyuanMap = keshiruyuanList.stream().map(item -> {
                YzcxHandleInfoDayExt yzcxHandleInfoDayExt = new YzcxHandleInfoDayExt();
                yzcxHandleInfoDayExt.setHandledateStr(LdgDateUtil.getHourNum(item.getHandledate()).toString());
                yzcxHandleInfoDayExt.setCount(item.getCount());
                return yzcxHandleInfoDayExt;
            }).collect(Collectors.groupingBy(YzcxHandleInfoDayExt::getHandledateStr, Collectors.summingDouble(YzcxHandleInfoDayExt::getCount)));
            final List<String> hoursList = YZCXConstant.hoursList;
            Integer maxHourNum = LdgDateUtil.getHourNum(keshiruyuanList.get(keshiruyuanList.size() - 1).getHandledate());
            List<String> category = new ArrayList<>();
            List<Number> zhexianNum = new ArrayList<>();
            for (int i = 0; i < hoursList.size(); i++) {
                if (i > maxHourNum) {
                    break;
                }
                String hourNum = hoursList.get(i);
                Double sumNum = ruyuanMap.get(hourNum);
                if (sumNum == null) {
                    sumNum = 0d;
                }
                category.add(hourNum);
                zhexianNum.add(sumNum);
            }
            Map<String, List<Number>> nameAndData = new HashMap<>();
            nameAndData.put("入院人数", zhexianNum);
            //////////////////////////////////科室入院排名
            final Map<String, Double> ksAndRyNum = keshiruyuanList.stream().collect(Collectors.groupingBy(YzcxHandleInfoDay::getName, Collectors.summingDouble(YzcxHandleInfoDay::getCount)));
            List<YzcxHandleInfoDay> ksAndSumRenshuList = new ArrayList<>();
            ksAndRyNum.forEach((ksname, sumRenshu) -> {
                YzcxHandleInfoDay newObj = new YzcxHandleInfoDay();
                newObj.setName(ksname);
                newObj.setCount(sumRenshu);
                ksAndSumRenshuList.add(newObj);
            });
            List<YzcxHandleInfoDay> qianshiKsRuYuan = ksAndSumRenshuList.stream().sorted(Comparator.comparing(YzcxHandleInfoDay::getCount).reversed()).limit(10).collect(Collectors.toList());
            List<String> category_ruyuan = new ArrayList<>();
            List<Number> ruyuanData = new ArrayList<>();
            qianshiKsRuYuan.forEach(item -> {
                category_ruyuan.add(item.getName());
                ruyuanData.add(item.getCount());
            });
            Map<String, List<Number>> nameAndData_ksryqs = new HashMap<>();
            nameAndData_ksryqs.put("入院人数", ruyuanData);
            /////////////////////////////////////////////////////////////////////////床位信息
            YZCXSearchParam beforeDayparam= YZCXControllerUtil.getBeforeDayByNum(1);
            beforeDayparam.setHandletype(Arrays.asList(YZCXConstant.zhuyuan_keshishizhan,YZCXConstant.zhuyuan_keshikaifang));
            final List<YzcxHandleInfo> chuangweiList = yzcxHandleInfoMapper.selectByDateAndType(beforeDayparam);
            final Map<String, Map<Integer, Double>> keshiType = chuangweiList.stream().collect(Collectors.groupingBy(YzcxHandleInfo::getName, Collectors.groupingBy(YzcxHandleInfo::getHandletype, Collectors.summingDouble(YzcxHandleInfo::getCount))));
            List<ZyxxKeshiChuanwei> cwList=new ArrayList<>();
            keshiType.forEach((keshiName,typeAndSum)->{
                Double shizhan = typeAndSum.get(YZCXConstant.zhuyuan_keshishizhan);
                Double kaifang=typeAndSum.get(YZCXConstant.zhuyuan_keshikaifang);
                ZyxxKeshiChuanwei zyxxKeshiChuanwei=new ZyxxKeshiChuanwei();
                zyxxKeshiChuanwei.setShizhan(shizhan);
                zyxxKeshiChuanwei.setKaifang(kaifang);
                zyxxKeshiChuanwei.setKsname(keshiName);
                if(shizhan!=null&&shizhan!=0&&kaifang!=null&&kaifang!=0){
                    zyxxKeshiChuanwei.setCwshiyonglv(shizhan/kaifang);
                }
                cwList.add(zyxxKeshiChuanwei);
            });

            List<ZyxxKeshiChuanwei> shiyonglvQianshi = cwList.stream().filter(item -> item.getCwshiyonglv() != null ? true : false).sorted(Comparator.comparing(ZyxxKeshiChuanwei::getCwshiyonglv).reversed()).limit(10).collect(Collectors.toList());
            List<String> category_chuangwei = new ArrayList<>();
            List<Number> ksshizhan = new ArrayList<>();
            List<Number> kskaifang = new ArrayList<>();
            shiyonglvQianshi.forEach(item->{
                category_chuangwei.add(item.getKsname());
                ksshizhan.add(item.getShizhan());
                kskaifang.add(item.getKaifang());
            });

            Map<String, List<Number>> nameAndData_chuangweisyl = new HashMap<>();
            nameAndData_chuangweisyl.put("实占床位", ksshizhan);
            nameAndData_chuangweisyl.put("开放床位", kskaifang);
            ////////////////////
            GsonOption echartOption = EchartsBuilder.buildEchartOption_line(" ", "入院人次波动图", category, nameAndData);
            GsonOption echartOption_ruyuanqianshi = EchartsBuilder.buildEchartOption_bar(" ", " ", category_ruyuan, nameAndData_ksryqs, false);
            GsonOption echartOption_chuangweishu = EchartsBuilder.buildEchartOption_bar(" ", " ", category_chuangwei, nameAndData_chuangweisyl, false);
            rs.put("echartOption", echartOption.toString());
            rs.put("echartOption_ruyuanqianshi", echartOption_ruyuanqianshi.toString());
            rs.put("echartOption_chuangweishu", echartOption_chuangweishu.toString());
        }
        return rs;
    }

    @Override
    public ZyxxIndex getIndexZhuYuanForYue(YZCXSearchParam param) throws ParseException {
        List<Integer> zyxxType = Arrays.asList(YZCXConstant.zhuyuan_brqk, YZCXConstant.zhuyuan_cyfs,
                YZCXConstant.zhuyuan_chuyuanRenshu, YZCXConstant.zhuyuan_ruyuanrenshu,
                YZCXConstant.zhuyuan_zhuanchuKS, YZCXConstant.zhuyuan_zhuanruKS);
        param.setHandletype(zyxxType);
        List<YzcxHandleInfoMonth> ksfeiyongList = yzcxCommonService.getMonthDataByParam(param);
        final Map<Integer, List<YzcxHandleInfoMonth>> zhuyuanMap = ksfeiyongList.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getHandletype));
        List<YzcxHandleInfoMonth> brqk = zhuyuanMap.get(YZCXConstant.zhuyuan_brqk);
        List<YzcxHandleInfoMonth> cyfs = zhuyuanMap.get(YZCXConstant.zhuyuan_cyfs);
        List<YzcxHandleInfoMonth> chuyuanrs = zhuyuanMap.get(YZCXConstant.zhuyuan_chuyuanRenshu);
        List<YzcxHandleInfoMonth> ruyuanrs = zhuyuanMap.get(YZCXConstant.zhuyuan_ruyuanrenshu);
        List<YzcxHandleInfoMonth> zhuanchuks = zhuyuanMap.get(YZCXConstant.zhuyuan_zhuanchuKS);
        List<YzcxHandleInfoMonth> zhuanruks = zhuyuanMap.get(YZCXConstant.zhuyuan_zhuanruKS);
        ZyxxIndex index = new ZyxxIndex();
        index.setParam(param);
        if (chuyuanrs != null) {
            index.setChuyuan(chuyuanrs.get(0).getCount());
        }
        if (ruyuanrs != null) {
            index.setRuyuan(ruyuanrs.get(0).getCount());
        }
        if (brqk != null) {
            Map<String, Double> brqkMap = brqk.stream().collect(Collectors.toMap(YzcxHandleInfoMonth::getName, YzcxHandleInfoMonth::getCount));
            Double bws = brqkMap.get(YZCXConstant.zhuyuan_brqk_bingwei);//病危
            Double bzs = brqkMap.get(YZCXConstant.zhuyuan_brqk_bingzhong);//病重数
            if (bws == null) {
                bws = 0d;
            }
            if (bzs == null) {
                bzs = 0d;
            }
            index.setWeizhong(bws + bzs);
        }
        if (cyfs != null) {
            Map<String, Double> cyfsMap = cyfs.stream().collect(Collectors.toMap(YzcxHandleInfoMonth::getName, YzcxHandleInfoMonth::getCount));
            Double sws = cyfsMap.get(YZCXConstant.zhuyuan_cyfs_siwang);
            index.setSiwang(sws);
        }
        if (zhuanchuks != null) {
            index.setZhuanchu(zhuanchuks.stream().collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        }
        if (zhuanruks != null) {
            index.setZhuanchu(zhuanruks.stream().collect(Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        }
        /////////////////////////////////////////////////////////////////床位
        param.setHandletype(Arrays.asList(YZCXConstant.zhuyuan_keshishizhan,YZCXConstant.zhuyuan_keshikaifang));
        final List<YzcxHandleInfoMonth> chuangweiList =yzcxCommonService.getMonthDataByParam(param);
        final Map<Integer, Double> keshiChuangweiMap = chuangweiList.stream().collect(Collectors.groupingBy(YzcxHandleInfoMonth::getHandletype, Collectors.summingDouble(YzcxHandleInfoMonth::getCount)));
        Double shizhan = keshiChuangweiMap.get(YZCXConstant.zhuyuan_keshishizhan);
        Double kaifang = keshiChuangweiMap.get(YZCXConstant.zhuyuan_keshikaifang);
        index.setShizhan(shizhan);
        index.setKaifang(kaifang);
        if(shizhan!=null&&shizhan!=0&&kaifang!=null&&kaifang!=0){
            index.setCwshiyonglv(shizhan/kaifang);
        }
        return index;
    }


}
