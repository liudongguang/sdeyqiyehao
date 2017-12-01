package com.yzcx.impl.service;

import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.po.YzcxHandleInfoMonth;
import com.yzcx.api.service.YZCXCommonService;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.impl.mapper.YzcxHandleInfoMapper;
import com.yzcx.impl.mapper.YzcxHandleInfoMonthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class YZCXCommonServiceImpl implements YZCXCommonService {
    @Autowired
    private YzcxHandleInfoMapper yzcxHandleInfoMapper;
    @Autowired
    private YzcxHandleInfoMonthMapper yzcxHandleInfoMonthMapper;
    @Override
    public List<YzcxHandleInfoMonth> getMonthDataByParam(YZCXSearchParam yzcxSearchParam) {
        boolean isDangYue= LdgDateUtil.isDangYue(yzcxSearchParam);
        List<YzcxHandleInfoMonth> ksfeiyongList = null;
        if(isDangYue){
            List<YzcxHandleInfo> dayDays=yzcxHandleInfoMapper.selectByDateAndType(yzcxSearchParam);
            ksfeiyongList=dayDays.stream().map(item->{
                YzcxHandleInfoMonth yzcxHandleInfoMonth=new YzcxHandleInfoMonth();
                yzcxHandleInfoMonth.setName(item.getName());
                yzcxHandleInfoMonth.setCount(item.getCount());
                yzcxHandleInfoMonth.setHandletype(item.getHandletype());
                return yzcxHandleInfoMonth;
            }).collect(Collectors.toList());
        }else{
            ksfeiyongList = yzcxHandleInfoMonthMapper.selectByDateAndType(yzcxSearchParam);
        }
        return ksfeiyongList;
    }
}
