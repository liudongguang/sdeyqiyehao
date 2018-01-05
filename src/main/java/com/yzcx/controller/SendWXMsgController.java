package com.yzcx.controller;

import com.ldg.api.util.JsonUtil;
import com.weixin.util.WinXinUtils;
import com.yzcx.api.bo.wxsendmsg.TuWenMsg;
import com.yzcx.api.bo.wxsendmsg.TuWenMsg_news;
import com.yzcx.api.bo.wxsendmsg.TuWenMsg_news_articles;
import com.yzcx.api.service.ExcelImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping(value = "/sendwxmsg")
public class SendWXMsgController {

    @Autowired
    private ExcelImportService excelImportService;

    @RequestMapping(value = "/handlerBirthExcel")
    @ResponseBody
    public String handlerBirthExcel(HttpServletRequest request) throws Exception {
        String rs=excelImportService.saveExcelBirthData(request);
        return rs;
    }


    @RequestMapping(value = "/sendBirthday")
    @ResponseBody
    public void sendBirthday() throws IOException, ParseException {
        TuWenMsg tuWenMsg=new TuWenMsg();
        TuWenMsg_news news=new TuWenMsg_news();
        tuWenMsg.setNews(news);
        TuWenMsg_news_articles tuWenMsg_news_articles=new TuWenMsg_news_articles();
        tuWenMsg_news_articles.setTitle("生日祝福");
        tuWenMsg_news_articles.setDescription("");
        tuWenMsg_news_articles.setPicurl("http://jyxc.sdey.net:9000/sdeyqiyehao/assets/img/bir.jpg");
        tuWenMsg_news_articles.setUrl("http://jyxc.sdey.net:9000/sdeyqiyehao/birthday/index.jsp");
        news.getArticles().add(tuWenMsg_news_articles);
        /////////////////
        tuWenMsg.setTouser("ceshi22|5598|ssdf|336dfdd");
        //tuWenMsg.setTouser("5598");
        tuWenMsg.setAgentid(1000004);
        WinXinUtils.sendJsonMsgToUser(JsonUtil.parseToJson(tuWenMsg));
    }
}
