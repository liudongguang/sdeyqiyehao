package com.yzcx.api.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by LiuDongguang on 2017/11/6.
 */
public class LdgDateUtil {
    private final static String yyyy_mm_dd_hh_mm_ss = "yyyy-MM-dd HH:mm:ss";
    private final static String yyyy_mm_dd_hh = "yyyy-MM-dd HH";
    private final static String yyyy_mm_dd = "yyyy-MM-dd";
    private final static ZoneId zoneId = ZoneId.systemDefault();
    private final static String yyyy_mm_dd_00_00_00 = "yyyy-MM-dd 00:00:00";
    private final static String yyyy_mm_dd_23_59_59 = "yyyy-MM-dd 23:59:59";
    private final static String yyyy_mm_dd_HH_00_00 = "yyyy-MM-dd HH:00:00";
    public final static DateTimeFormatter newDateFormat_yyyy_mm_dd_HH_00_00=DateTimeFormatter.ofPattern(yyyy_mm_dd_HH_00_00);

    public static String getyyyy_mm_dd_HH_00_00String(Date date) {
        return DateFormatUtils.format(date, yyyy_mm_dd_HH_00_00);
    }
    public static String getYyyy_mm_dd_hh_mm_ssString(Date date) {
        return DateFormatUtils.format(date, yyyy_mm_dd_hh_mm_ss);
    }

    public static Date getYyyy_mm_dd_hh_mm_ssDate(String dateStr) throws ParseException {
        return DateUtils.parseDate(dateStr, yyyy_mm_dd_hh_mm_ss);
    }

    public static String getYyyy_mm_ddString(Date date) {
        return DateFormatUtils.format(date, yyyy_mm_dd);
    }

    public static Date getYyyy_mm_ddDate(String dateStr) throws ParseException {
        return DateUtils.parseDate(dateStr, yyyy_mm_dd);
    }


    public static String getyyyy_mm_dd_hhString(Date date) {
        return DateFormatUtils.format(date, yyyy_mm_dd_hh);
    }

    public static Date getyyyy_mm_dd_hhDate(String dateStr) throws ParseException {
        return DateUtils.parseDate(dateStr, yyyy_mm_dd_hh);
    }

    public static Date parseLocalDateToDate(LocalDate localDate) {
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    public static LocalDate parseDateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }
    public static LocalDateTime parseDateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDate = instant.atZone(zoneId).toLocalDateTime();
        return localDate;
    }
    public static List<Date> getDateByBetween(Date start, Date end) {
        List<Date> listDate = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(end);
        LocalDate newstart = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
        LocalDate newend = LocalDate.of(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH)+1, calendar2.get(Calendar.DAY_OF_MONTH));
        if (newstart.isAfter(newend)) {
            System.out.println("开始时间不能在结束时间之后");
            return null;
        }
        while (true) {
            if (newstart.isBefore(newend)) {
                listDate.add(parseLocalDateToDate(newstart));
                newstart=newstart.plusDays(1);
            } else {
                break;
            }
        }
        listDate.add(parseLocalDateToDate(newstart));//包含结尾时间
        return listDate;
    }

    public static Date getDayZeroTime() throws ParseException {
        Date now=new Date();
        String str= DateFormatUtils.format(now,yyyy_mm_dd_00_00_00);
        return DateUtils.parseDate(str, yyyy_mm_dd_hh_mm_ss);
    }
    public static Date getDayLastTime() throws ParseException {
        Date now=new Date();
        String str= DateFormatUtils.format(now,yyyy_mm_dd_23_59_59);
        return DateUtils.parseDate(str, yyyy_mm_dd_hh_mm_ss);
    }
}
