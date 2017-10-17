package com.ldg.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateConverter implements Converter<String, Date> {
	@Override
	public Date convert(String source) {
		SimpleDateFormat smt = null;
		try {
			// 转换成功直接返回
			if (!StringUtils.isEmpty(source)) {
				if (source.length() == 10) {
					smt = new SimpleDateFormat("yyyy-MM-dd");
				} else {
					smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				}
				Date rtDate = smt.parse(source);
				return rtDate;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 参数绑定失败返回null
		return null;
	}

}
