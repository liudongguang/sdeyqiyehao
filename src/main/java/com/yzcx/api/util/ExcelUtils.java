package com.yzcx.api.util;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtils<T> {
    public static <T> void createExcelWorkBook(Workbook workbook, List<T> data, Map<String, String> columns) {
        List<String> fields = new ArrayList<String>();
        List<String> heads = new ArrayList<String>();
        for (String key : columns.keySet()) {
            fields.add(key);
            heads.add(columns.get(key));
        }
        int rowNum = 0;
        ///// 创建表头
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(rowNum);
        setCellValue(heads, row);
        //sheet.autoSizeColumn(rowNum);
        rowNum++;
        /////
        for (T t : data) {
            row = sheet.createRow(rowNum);
            rowNum++;
            Class clazz = t.getClass();
            int cellNum = 0;
            for (String field : fields) {
                Field fl = null;
                try {
                    fl = clazz.getDeclaredField(field);
                } catch (NoSuchFieldException e) {
                    try {
                        fl = clazz.getSuperclass().getDeclaredField(field);
                    } catch (NoSuchFieldException e1) {
                        e1.printStackTrace();
                    } catch (SecurityException e1) {
                        e1.printStackTrace();
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
                fl.setAccessible(true);
                Object objValue = null;
                try {
                    objValue = fl.get(t);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                setCellValue(objValue, row, cellNum);
                cellNum++;
            }
        }
        for (int i = 0; i < fields.size(); i++) {
            sheet.setColumnWidth(i, 20 * 256);
        }
    }

    private static <E> void setCellValue(List<E> list, Row row) {
        int cellNum = 0;
        for (E e : list) {
            Cell cell = row.createCell(cellNum);
            cellNum++;
            if (e == null) {
                continue;
            }
            String value = e.toString();
            cell.setCellValue(value);// 设置单元格内容
            // cell.setCellStyle(cellStyle);//设置单元格样式
            // cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
            // cell.setCellComment(comment);//添加注释

        }
    }

    private static void setCellValue(Object obj, Row row, int cellNum) {
        Cell cell = row.createCell(cellNum);
        if (obj != null) {
            if ("java.util.Date".equals(obj.getClass().getName())) {
                Date d = (Date) obj;
                try {
                    cell.setCellValue(LdgDateUtil.getYyyy_mm_ddDateStr(d));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                cell.setCellValue(obj.toString());// 设置单元格内容
            }
        } else {
            cell.setCellValue("");// 设置单元格内容
        }
    }

    public static String getFileName(String fileNameSource, HttpServletRequest request) {
        /**
         * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE
         * 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
         * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1;
         * zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
         */
        String agent = request.getHeader("USER-AGENT");
        try {
            if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
                String newFileName = URLEncoder.encode(fileNameSource, "UTF-8");
                newFileName = StringUtils.replace(newFileName, "+", "%20");
                if (newFileName.length() > 150) {
                    newFileName = new String(fileNameSource.getBytes("GB2312"), "ISO8859-1");
                    newFileName = StringUtils.replace(newFileName, " ", "%20");
                }
                return newFileName;
            }
            if ((agent != null) && (-1 != agent.indexOf("Mozilla"))) {
                return MimeUtility.encodeText(fileNameSource, "UTF-8", "B");
            }
            return fileNameSource;
        } catch (Exception ex) {
            return fileNameSource;
        }
    }


    public static <T> List<T> readExcel(InputStream in, Class<T> clazz, String fileName) throws Exception {
        Workbook workbook = null;
        List<T> rtList = new ArrayList<>();
        try {
            if (fileName.toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(in);
            } else if (fileName.toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(in);
            }
            Sheet sheet = workbook.getSheetAt(0);
            //得到行的迭代器
            Iterator<Row> rowIterator = sheet.iterator();
            int rowCount = 0;
            //循环每一行
            rowIterator.next();
            while (rowIterator.hasNext()) {
                T t = clazz.newInstance(); //创建新的对象
                Field[] fields = t.getClass().getDeclaredFields();
                //得到一行对象
                Row row = rowIterator.next();
                int fieldLength = fields.length;
                int colsSize = row.getLastCellNum();
                if (colsSize > fieldLength) {
                    colsSize = fieldLength;
                }
                for (int i = row.getFirstCellNum(); i < colsSize; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    //得到单元格对象
                    Cell cell = row.getCell(i);
                    String ss = parseExcel(cell, i);
                    //   System.out.println(ss+"   "+"  "+i+"   "+field.getName());
                    setStrVal(field, t, ss);
                }
                // System.out.println("--------------------");
                rtList.add(t);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rtList;
    }

    public static <T> List<T> readExcel(String fileName, Class<T> clazz) throws Exception {
        Workbook workbook = null;
        Sheet sheet = null;
        InputStream in = null;
        List<T> rtList = null;
        try {
            ClassPathResource hospitalInterface = new ClassPathResource(fileName);
            in = hospitalInterface.getInputStream();
            rtList = readExcel(in, clazz, fileName);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rtList;
    }

    private static String parseExcel(Cell cell, int i) {
        String result = "";
        if (cell == null) {
            return result;
        }
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                            .getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = LdgDateUtil.yyyy_MM_dd_HH_mmFormat;
                    }
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = LdgDateUtil.yyyy_MM_dd_HH_mmFormat;
                    double value = cell.getNumericCellValue();
                    Date date = DateUtil
                            .getJavaDate(value);
                    result = sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#.##");
                    }
                    result = format.format(value);
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:// String类型
                result = cell.getRichStringCellValue().toString();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                result = "";
                break;
            default:
                System.out.println(cell.getCellType() + "+++++++++++" + i);
                result = "";
                break;
        }
        return result;
    }

    private static <T> void setStrVal(Field field, T t, String val) throws IllegalAccessException, ParseException {
        String type = field.getType().getTypeName();
        switch (type) {
            case SysConstant.type_String:
                if (StringUtils.isNotBlank(val)) {
                    field.set(t, val);
                } else {
                    field.set(t, "");
                }
                break;
            case SysConstant.type_BigDecimal:
                if (StringUtils.isNotBlank(val)) {
                    BigDecimal bd = new BigDecimal(val);
                    field.set(t, bd);
                }
                break;
            case SysConstant.type_Date:
                if (StringUtils.isNotBlank(val)) {

                    if (val.length() == 8) {
                        field.set(t, LdgDateUtil.getYyyy_mm_ddDate(val));
                    } else {
                        field.set(t, LdgDateUtil.getyyyy_mm_dd_hh_mmDate(val));
                    }
                }
                break;
            case SysConstant.type_Long:
                if (StringUtils.isNotBlank(val) && val.indexOf("岁") != -1) {
                    val = val.substring(0, val.indexOf("岁"));
                    field.set(t, Long.valueOf(val));
                }
                if (StringUtils.isNotBlank(val) && val.indexOf("天") != -1) {
                    val = val.substring(0, val.indexOf("天"));
                    double year = Math.ceil(Long.valueOf(val) * 1.0 / 365);
                    field.set(t, Long.valueOf((long) year));
                } else if (StringUtils.isNotBlank(val)) {
                    field.set(t, Long.valueOf(val));
                }
                break;
            case SysConstant.type_Short:
                if (StringUtils.isNotBlank(val)) {
                    field.set(t, Short.valueOf(val));
                }
                break;
            case SysConstant.type_Integer:
                if (StringUtils.isNotBlank(val)) {
                    field.set(t, Integer.valueOf(val));
                }
                break;
            default:
                System.out.println(type + "     " + field.getName());
                break;
        }
    }

    public static List<String> getFirstRowCols(InputStream in, String fileName) throws IOException {
        Workbook workbook = null;
        List<String> rtList = new ArrayList<>();

        if (fileName.toLowerCase().endsWith("xlsx")) {
            workbook = new XSSFWorkbook(in);
        } else if (fileName.toLowerCase().endsWith("xls")) {
            workbook = new HSSFWorkbook(in);
        }
        Sheet sheet = workbook.getSheetAt(0);
        //得到行的迭代器
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
                String val = parseExcel(row.getCell(i), i);
                rtList.add(val);
            }
            break;
        }
        return rtList;

    }

    public static void main(String[] args) {
        System.out.println(StringUtils.isNotBlank(""));
    }
}
