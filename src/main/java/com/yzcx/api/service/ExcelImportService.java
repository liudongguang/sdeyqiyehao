package com.yzcx.api.service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public interface ExcelImportService {
    String saveExcelBirthData(HttpServletRequest request) throws Exception;

    /**
     * 发送生日祝福信息
     * @return
     */
    String sendBirthday() throws ParseException;
}
