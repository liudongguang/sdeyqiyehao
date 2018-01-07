package com.membirth.impl.mapper;

import com.membirth.api.po.SdeyMemberBirsend;
import com.yzcx.api.vo.YZCXSearchParam;
import tk.mybatis.mapper.common.Mapper;

public interface SdeyMemberBirsendMapper extends Mapper<SdeyMemberBirsend> {
    int selectByDate(YZCXSearchParam searchParamForThisMonth);
}