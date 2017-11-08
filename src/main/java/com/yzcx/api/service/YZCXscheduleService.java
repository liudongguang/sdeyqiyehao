package com.yzcx.api.service;

import com.ldg.api.vo.ResultMsg2;
import com.yzcx.api.vo.YZCXHandlerData;
import com.yzcx.api.vo.YZCXSearchParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by LiuDongguang on 2017/11/3.
 */
public interface YZCXscheduleService {
    YZCXHandlerData getmzinfo(YZCXSearchParam param) throws IOException;

    void saveYZCXData(YZCXHandlerData handlerData,YZCXSearchParam param);


    ResultMsg2 montho_mzinfo(YZCXSearchParam param);

    void menzhenDayHandler(YZCXSearchParam param);
}
