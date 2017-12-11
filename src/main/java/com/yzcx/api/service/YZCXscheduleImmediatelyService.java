package com.yzcx.api.service;

import com.ldg.api.vo.ResultMsg2;
import com.yzcx.api.vo.YZCXHandlerData;
import com.yzcx.api.vo.YZCXSearchParam;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by LiuDongguang on 2017/11/3.
 * 即时的信息处理
 */
public interface YZCXscheduleImmediatelyService {
    /**
     * 删除日处理信息
     * @param param
     */
    int deleteMenzhenDayHandler(YZCXSearchParam param);

    void ImmediatelyHandler()  throws ParseException;
}
