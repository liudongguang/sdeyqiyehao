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
		ResultMsg2 msg=new ResultMsg2();
		param.setStart(LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate("2017-09-01 00:00:00"));
		param.setEnd(LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate("2017-10-05 23:59:59"));
		YZCXHandlerData handlerData=yzcXscheduleService.getmzinfo(param);
		if(handlerData==null){
			msg.setErrmsg("已导入！");
			return msg;
		}
		yzcXscheduleService.saveYZCXData(handlerData,param);
		return msg;
	}

	@RequestMapping(value = "/testmzMonth")
	@ResponseBody
	public ResultMsg2 testmzMonth(YZCXSearchParam param) throws IOException, ParseException {
		System.out.println("-------------");
		param.setStart(LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate("2017-09-01 00:00:00"));
		param.setEnd(LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate("2017-09-30 23:59:59"));
		ResultMsg2 msg=yzcXscheduleService.montho_mzinfo(param);
		return msg;
	}
}
