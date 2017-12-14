package com.yzcx.controller;

import com.ldg.api.util.JsonUtil;
import com.ldg.api.vo.ResultMsg2;
import com.weixin.util.HttpClientUtil;
import com.yzcx.api.bo.FbaiduParam;
import com.yzcx.api.po.YzcxHisoffice;
import com.yzcx.api.service.YZCXHisOfficeService;
import com.yzcx.api.util.PingyinHandler;
import com.yzcx.api.util.YZCXProperties;
import com.yzcx.api.vo.ZYXXchuangwei;
import com.yzcx.api.vo.parsejson.Json_HisOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/hisoffice")
public class YZCXHisOfficeHandlerController {
    @Autowired
    private YZCXHisOfficeService yzcxHisOffice;

    @RequestMapping(value = "/initKS")
    @ResponseBody
    public ResultMsg2 initKS() throws IOException, ParseException {
        String zhuyuanurl = YZCXProperties.getRequestPropertiesVal("getHisOffice");//
        HttpClientUtil htc = HttpClientUtil.getInstance();
        final String hisOfficeStr = htc.sendHttpPost(zhuyuanurl);
        Json_HisOffice hisOffice = JsonUtil.getObjectByJSON(hisOfficeStr, Json_HisOffice.class);
        final List<ZYXXchuangwei> data = hisOffice.getData();
        List<YzcxHisoffice> saveKsList = data.stream().map(item -> {
            YzcxHisoffice yzcxHisoffice = new YzcxHisoffice();
            String ksName = item.getKs();
            yzcxHisoffice.setKsname(ksName);
            yzcxHisoffice.setKspinyin(PingyinHandler.converterToFirstSpell(ksName));
            return yzcxHisoffice;
        }).collect(Collectors.toList());
        yzcxHisOffice.deleteAll();
        yzcxHisOffice.batchInsert(saveKsList);
        ResultMsg2 msg = new ResultMsg2();
        return msg;
    }

    @RequestMapping(value = "/searchKS")
    @ResponseBody
    public ResultMsg2 searchKS(FbaiduParam fbaiduParam){
        List<YzcxHisoffice> list=yzcxHisOffice.searchKS(fbaiduParam);
        ResultMsg2 rs=new ResultMsg2();
        rs.setData(list);
        return rs;
    }

}
