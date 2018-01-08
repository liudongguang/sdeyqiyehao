package com.yzcx.impl.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ldg.api.util.JsonUtil;
import com.ldg.api.util.RequestFileUtil;
import com.membirth.api.po.SdeyMember;
import com.membirth.api.po.SdeyMemberBirsend;
import com.membirth.impl.mapper.SdeyMemberBirsendMapper;
import com.membirth.impl.mapper.SdeyMemberMapper;
import com.weixin.util.WinXinUtils;
import com.yzcx.api.bo.excel.Excel_birthday;
import com.yzcx.api.bo.wxsendmsg.TuWenMsg;
import com.yzcx.api.bo.wxsendmsg.TuWenMsg_news;
import com.yzcx.api.bo.wxsendmsg.TuWenMsg_news_articles;
import com.yzcx.api.service.ExcelImportService;
import com.yzcx.api.util.ExcelUtils;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExcelImportServiceImpl implements ExcelImportService {
    @Autowired
    private SdeyMemberMapper sdeyMemberMapper;
    @Autowired
    private SdeyMemberBirsendMapper sdeyMemberBirsendMapper;


    @Override
    public String saveExcelBirthData(HttpServletRequest request) throws Exception {
        List<MultipartFile> uploadFile = RequestFileUtil.getUploadFile(request);
        System.out.println(uploadFile);
        if (uploadFile != null && uploadFile.size() != 0) {
            MultipartFile excelFile = uploadFile.get(0);
            String originalFilename = excelFile.getOriginalFilename();
            List<Excel_birthday> excel_birthdays = ExcelUtils.readExcel(excelFile.getInputStream(), Excel_birthday.class, originalFilename);
            if (excel_birthdays != null && excel_birthdays.size() != 0) {
                List<Excel_birthday> invalidIdcardList = excel_birthdays.stream().filter(item -> {
                    if (item.getIdcard().length() != 18) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
                if (invalidIdcardList.size() == 0) {
                    /////////////////////////////////////////////////////////
                    List<SdeyMember> dbmonthData = sdeyMemberMapper.getAllByMonthNum(Integer.valueOf(excel_birthdays.get(0).getIdcard().substring(10, 12)));//获取月信息
                    Map<String, String> dbMap = new HashMap<>();
                    dbmonthData.forEach(item -> {
                        dbMap.put(item.getIdcard(), item.getIdcard());
                    });
                    ////////////
                    List<Excel_birthday> saveDataList = excel_birthdays.stream().filter(item -> {
                        if (dbMap.get(item.getIdcard()) == null) {
                            return true;
                        }
                        return false;
                    }).collect(Collectors.toList());
                    List<SdeyMember> saveToDBData = saveDataList.stream().map(item -> {
                        SdeyMember sdeyMember = new SdeyMember();
                        String subMonth = item.getIdcard().substring(10, 12);
                        String subDay = item.getIdcard().substring(12, 14);
                        sdeyMember.setMonth(Integer.valueOf(subMonth));
                        sdeyMember.setDay(Integer.valueOf(subDay));
                        sdeyMember.setGonghao(item.getGonghao());
                        sdeyMember.setIdcard(item.getIdcard());
                        sdeyMember.setMobile(item.getMobile());
                        sdeyMember.setXingming(item.getXingming());
                        return sdeyMember;
                    }).collect(Collectors.toList());
                    if (saveToDBData.size() != 0) {
                        sdeyMemberMapper.batchInsert(saveToDBData);
                        return "人员添加完毕！";
                    } else {
                        return "没有新增人员！";
                    }
                } else {
                    return "身份证号码错误！";
                }
            } else {
                return "解析文件失败！";
            }
        }
        return "没有上传文件！";
    }

    @Override
    public String sendBirthday() throws ParseException {
        final YZCXSearchParam searchParamForThisMonth = LdgDateUtil.getSearchParamForThisMonth();
        int count = sdeyMemberBirsendMapper.selectByDate(searchParamForThisMonth);
        if (count == 0) {
            int monthValue = searchParamForThisMonth.getLocalDate().getMonthValue();
            SdeyMemberBirsend sdeyMemberBirsend = new SdeyMemberBirsend();
            sdeyMemberBirsend.setSenddate(searchParamForThisMonth.getStart());
            List<SdeyMember> dbmonthData = sdeyMemberMapper.getAllByMonthNumForSend(monthValue);
//            List<SdeyMember> dbmonthData =new ArrayList<>();
//            SdeyMember sdeyMember=new SdeyMember();
//            sdeyMember.setXingming("刘东光");
//            sdeyMember.setGonghao("ceshi22");
//            sdeyMember.setMonth(3);
//            sdeyMember.setDay(15);
           // dbmonthData.add(sdeyMember);
            dbmonthData.forEach(item -> {
                String xingming = item.getXingming();
                Integer month = item.getMonth();
                Integer day = item.getDay();
                String gonghao = item.getGonghao();
                TuWenMsg tuWenMsg = new TuWenMsg();
                TuWenMsg_news news = new TuWenMsg_news();
                tuWenMsg.setNews(news);
                TuWenMsg_news_articles tuWenMsg_news_articles = new TuWenMsg_news_articles();
                tuWenMsg_news_articles.setTitle("生日祝福");
                tuWenMsg_news_articles.setDescription("");
                tuWenMsg_news_articles.setPicurl("http://jyxc.sdey.net:9000/sdeyqiyehao/assets/img/bir.jpg");
                tuWenMsg_news_articles.setUrl("http://jyxc.sdey.net:9000/sdeyqiyehao/birthday/index.jsp?xingming=" + xingming + "&month=" + month + "&day=" + day);
                news.getArticles().add(tuWenMsg_news_articles);
                /////////////////
                tuWenMsg.setTouser(gonghao);
                //tuWenMsg.setTouser("5598");
                tuWenMsg.setAgentid(0);
                try {
                    String s = WinXinUtils.sendJsonMsgToUser(JsonUtil.parseToJson(tuWenMsg));
                    SdeyMember updatemem = new SdeyMember();
                    updatemem.setGonghao(gonghao);
                    updatemem.setSendstate(s);
                    int updatenum=sdeyMemberMapper.updateSendState(updatemem);
                    System.out.println(gonghao+"   "+updatenum);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
            sdeyMemberBirsendMapper.insertSelective(sdeyMemberBirsend);
            return "ok";
        }
        return null;
    }
}
