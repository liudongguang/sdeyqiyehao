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
@RequestMapping(value = "/webyzcx")
public class YZCXWebController {
	@Autowired
	private YZCXscheduleService yzcXscheduleService;
	@RequestMapping(value = "/index")
	public String index(YZCXSearchParam param) throws IOException, ParseException {

		return "/yzcx/index.jsp";
	}

	@RequestMapping(value = "/menzhen")
	public String menzhen(YZCXSearchParam param) throws IOException, ParseException {

		return "/yzcx/menzhen.jsp";
	}

}
