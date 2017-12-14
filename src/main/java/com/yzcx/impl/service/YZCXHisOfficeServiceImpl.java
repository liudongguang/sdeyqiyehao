package com.yzcx.impl.service;

import com.yzcx.api.bo.FbaiduParam;
import com.yzcx.api.po.YzcxHisoffice;
import com.yzcx.api.service.YZCXHisOfficeService;
import com.yzcx.impl.mapper.YzcxHisofficeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YZCXHisOfficeServiceImpl implements YZCXHisOfficeService {
    @Autowired
    private YzcxHisofficeMapper yzcxHisofficeMapper;

    @Override
    public void batchInsert(List<YzcxHisoffice> list) {
        yzcxHisofficeMapper.batchInsert(list);
    }

    @Override
    public int deleteAll() {
        return yzcxHisofficeMapper.deleteAll();
    }

    @Override
    public List<YzcxHisoffice> searchKS(FbaiduParam fbaiduParam) {
        return yzcxHisofficeMapper.searchKS(fbaiduParam);
    }
}
