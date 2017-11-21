package com.yzcx.api.service;

import com.ldg.api.vo.ResultMsg2;
import com.yzcx.api.vo.YZCXHandlerData;
import com.yzcx.api.vo.YZCXSearchParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

/**
 * Created by LiuDongguang on 2017/11/3.
 */
public interface YZCXscheduleService {
    YZCXHandlerData getmzinfo(YZCXSearchParam param) throws IOException, ParseException;

    void saveYZCXMenzhenData(YZCXHandlerData handlerData, YZCXSearchParam param);


    ResultMsg2 montho_mzinfo(YZCXSearchParam param);

    void menzhenDayHandler() throws ParseException;

    /**
     * 获取费用信息
     * @param param
     * @return
     * @throws ParseException
     */
    YZCXHandlerData handlerFeiyonginfo(YZCXSearchParam param) throws ParseException;

    /**
     * 保存费用信息
     * @param handlerFeiYongData
     * @param param
     */
    void saveYZCXFeiyongData(YZCXHandlerData handlerFeiYongData, YZCXSearchParam param);
}
