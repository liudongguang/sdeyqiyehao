package com.yzcx.api.service;

import com.ldg.api.vo.ResultMsg2;
import com.yzcx.api.vo.YZCXSearchParam;

import java.text.ParseException;
import java.util.List;

/**
 * Created by LiuDongguang on 2017/11/3.
 */
public interface YZCXscheduleService {
    /**
     * 保存门诊记录

     * @param param
     */
    void saveYZCXMenzhenData(YZCXSearchParam param);
    ResultMsg2 montho_mzinfo(YZCXSearchParam param);



    /**
     * 处理费用信息
     * @param param
     * @return
     * @throws ParseException
     */
    void handlerFeiyonginfo(YZCXSearchParam param) throws ParseException;


    /**
     * 获取时间段内日归档的日期
     * @param searchParam
     * @return
     */
    List<YZCXSearchParam> getExistDaysFromGuiDangDays(YZCXSearchParam searchParam);

    /**
     * 处理住院信息
     * @param param
     */
    void handlerZhuYuanXinxiRiGuiDang(YZCXSearchParam param) throws ParseException;



    /**
     * 日归档会诊
     * @param param
     */
    void handlerHuizhenRiGuiDang(YZCXSearchParam param) throws ParseException;
}
