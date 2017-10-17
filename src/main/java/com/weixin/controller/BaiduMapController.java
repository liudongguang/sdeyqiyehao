package com.weixin.controller;

import com.baidumap.MapUtils;
import com.baidumap.vo.JingWeiToAddress;
import com.ldg.api.vo.ResultMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping(value = "/baiduMap")
public class BaiduMapController {
	@RequestMapping(value = "/getPositionByLngAndLat")
	public @ResponseBody
    ResultMsg getPositionByLngAndLat(String lng, String lat) throws UnsupportedEncodingException {
		ResultMsg msg = new ResultMsg();
		JingWeiToAddress address = MapUtils.getAddressNameByJingWeiDu(lng, lat);
		msg.setData(address);
		return msg;
	}
}
