package com.weixin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.Page;
import com.ldg.api.util.JsonUtil;
import com.ldg.api.vo.ResultMsg;
import com.qq.weixin.mp.aes.AesException;
import com.weixin.api.po.YzcxQxusers;
import com.weixin.api.service.YZCXService;
import com.weixin.constant.WeixinConstant;
import com.weixin.util.Access_token;
import com.weixin.util.HttpClientUtil;
import com.weixin.util.PropertiesUtil;
import com.weixin.vo.PageParam;
import com.weixin.vo.assets.department.DetailEmpList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.MessageFormat;

@Controller
@RequestMapping(value = "/yzcx")
public class YZCXController {
	@Autowired
	private YZCXService yZCXService;

	@RequestMapping(value = "/test")
	public String test() throws IOException, AesException {
		return "/yzcx/quanxian/add_admin_selectDept.jsp";
	}

	/**
	 * 详细的人员列表
	 * 
	 * @param departmentId
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getDetailEmployeeByDepartMentID")
	public String getDetailEmployeeByDepartMentID(Integer departmentId, String ksname, HttpServletRequest request)
			throws JsonProcessingException {
		String accessToken = Access_token.getAccessToken();
		String url = MessageFormat.format(
				PropertiesUtil.weixinPropertiesVal(WeixinConstant.GETXIANGXIEMPLOYEEBYDEPARTMENTID), accessToken,
				departmentId, 1, 1);
		String result = HttpClientUtil.getInstance().sendHttpGet(url);
		DetailEmpList emplist = JsonUtil.getObjectByJSON(result, DetailEmpList.class);
		request.setAttribute("obj", emplist);
		request.setAttribute("ksname", ksname);
		return "/yzcx/quanxian/add_admin_selectUser.jsp";
	}

	@RequestMapping(value = "/qXUserList")
	public String qXUserList(HttpServletRequest request, YzcxQxusers qxuser, PageParam pageParam) {
		Page<YzcxQxusers> page = yZCXService.selectQXUsers(pageParam);
		request.setAttribute("page", page.toPageInfo());
		return "/yzcx/quanxian/admin-role.jsp";
	}

	@RequestMapping(value = "/saveQXUser")
	public String saveQXUser(HttpServletRequest request, YzcxQxusers qxuser) {
		int i = yZCXService.saveQXUser(qxuser);
		return "/yzcx/qXUserList";
	}

	@RequestMapping(value = "/delUserByID")
	public @ResponseBody
    ResultMsg delUserByID(HttpServletRequest request, Integer id) {
		int i = yZCXService.delQXUserByID(id);
		ResultMsg msg = new ResultMsg();
		return msg;
	}

	@RequestMapping(value = "/delUserByIDS")
	public @ResponseBody
    ResultMsg delUserByIDS(HttpServletRequest request, String ids) {
		int i = yZCXService.delQXUserByIDS(ids);
		ResultMsg msg = new ResultMsg();
		return msg;
	}

	@RequestMapping(value = "/getQXUserById")
	public String getQXUserById(HttpServletRequest request, Integer id) {
		YzcxQxusers qxuser = yZCXService.getQXUserById(id);
		request.setAttribute("qxuser", qxuser);
		return "/yzcx/quanxian/update_admin_role.jsp";
	}

}
