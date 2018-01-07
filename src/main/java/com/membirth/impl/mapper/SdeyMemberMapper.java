package com.membirth.impl.mapper;

import com.membirth.api.po.SdeyMember;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SdeyMemberMapper extends Mapper<SdeyMember> {
    List<SdeyMember> getAllByMonthNum(Integer integer);
    List<SdeyMember> getAllByMonthNumForSend(Integer integer);


    void batchInsert(List<SdeyMember> saveToDBData);

    int updateSendState(SdeyMember updatemem);
}