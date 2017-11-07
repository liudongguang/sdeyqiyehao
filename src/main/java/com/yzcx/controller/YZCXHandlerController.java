package com.yzcx.controller;

import com.ldg.api.vo.ResultMsg2;
import com.yzcx.api.service.YZCXscheduleService;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.vo.YZCXHandlerData;
import com.yzcx.api.vo.YZCXSearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping(value = "/yzcxdata")
public class YZCXHandlerController{
	@Autowired
	private YZCXscheduleService yzcXscheduleService;

	@RequestMapping(value = "/testmz")
	@ResponseBody
	public ResultMsg2 testmz(YZCXSearchParam param) throws IOException, ParseException {
		System.out.println("-------------");
		param.setStart(LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate("2017-10-06 00:00:00"));
		param.setEnd(LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate("2017-10-08 23:00:00"));
		YZCXHandlerData handlerData=yzcXscheduleService.getmzinfo(param);
		yzcXscheduleService.saveYZCXData(handlerData);
		return new ResultMsg2();
	}
}
