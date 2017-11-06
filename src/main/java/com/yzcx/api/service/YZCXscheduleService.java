package com.yzcx.api.service;

import com.yzcx.api.vo.YZCXSearchParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by LiuDongguang on 2017/11/3.
 */
public interface YZCXscheduleService {
    void getmzinfo(YZCXSearchParam param) throws IOException;
}
