package com.yzcx.impl.service;

import com.ldg.api.util.RequestFileUtil;
import com.membirth.api.po.SdeyMember;
import com.membirth.impl.mapper.SdeyMemberMapper;
import com.yzcx.api.bo.excel.Excel_birthday;
import com.yzcx.api.service.ExcelImportService;
import com.yzcx.api.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExcelImportServiceImpl implements ExcelImportService {
    @Autowired
    private SdeyMemberMapper sdeyMemberMapper;
    @Override
    public String saveExcelBirthData(HttpServletRequest request) throws Exception {
        List<MultipartFile> uploadFile = RequestFileUtil.getUploadFile(request);
        System.out.println(uploadFile);
        if (uploadFile != null && uploadFile.size() != 0) {
            MultipartFile excelFile = uploadFile.get(0);
            String originalFilename = excelFile.getOriginalFilename();
            List<Excel_birthday> excel_birthdays = ExcelUtils.readExcel(excelFile.getInputStream(), Excel_birthday.class, originalFilename);
            if(excel_birthdays!=null&&excel_birthdays.size()!=0){
                List<Excel_birthday> invalidIdcardList = excel_birthdays.stream().filter(item -> {
                    if (item.getIdcard().length() != 18) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
                if(invalidIdcardList.size()==0){
                    /////////////////////////////////////////////////////////
                    List<SdeyMember> dbmonthData=sdeyMemberMapper.getAllByMonthNum(Integer.valueOf(excel_birthdays.get(0).getIdcard().substring(10, 12)));//获取月信息
                    Map<String,String> dbMap=new HashMap<>();
                    dbmonthData.forEach(item->{
                        dbMap.put(item.getIdcard(),item.getIdcard());
                    });
                    ////////////
                    List<Excel_birthday> saveDataList = excel_birthdays.stream().filter(item -> {
                        if (dbMap.get(item.getIdcard()) == null) {
                            return true;
                        }
                        return false;
                    }).collect(Collectors.toList());
                    List<SdeyMember> saveToDBData = saveDataList.stream().map(item -> {
                        SdeyMember sdeyMember=new SdeyMember();
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
                    if(saveToDBData.size()!=0){
                        sdeyMemberMapper.batchInsert(saveToDBData);
                        return "人员添加完毕！";
                    }else{
                        return "没有新增人员！";
                    }
                }else{
                    return "身份证号码错误！";
                }
            }else{
                return "解析文件失败！";
            }
        }
        return "没有上传文件！";
    }
}
