package com.weixin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ldg.api.util.JsonUtil;
import com.ldg.api.vo.ResultMsg;
import com.weixin.constant.WeixinConstant;
import com.weixin.util.Access_token;
import com.weixin.util.HttpClientUtil;
import com.weixin.util.PropertiesUtil;
import com.weixin.util.WX_Util;
import com.weixin.vo.assets.department.DetailEmpList;
import com.weixin.vo.assets.department.SimpleEmpList;
import com.weixin.vo.assets.qyappmanager.GetqyApp;
import com.weixin.vo.assets.qyappmanager.GetqyAppList;
import com.weixin.vo.assets.qyappmanager.SetqyApp;
import com.weixin.vo.assets.tongxunlu.BootStrapTree;
import com.weixin.vo.assets.tongxunlu.DepartmentVo;
import com.weixin.vo.assets.tongxunlu.WXDepartment;
import com.weixin.vo.assets.tongxunlu.ZTreeVo;
import com.weixin.vo.httprs.BaseMsg;
import com.weixin.vo.sdeyinterface.WXSendBalanceVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 资源接口
 * 
 * @author liudo
 *
 */
@Controller
@RequestMapping(value = "/wxassets")
public class WXAssetsController {
	/**
	 * 1.获取企业应用
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getqyApp")
	public @ResponseBody
    ResultMsg create() throws JsonProcessingException {
		ResultMsg msg = new ResultMsg();
		String accessToken = Access_token.getAccessToken();
		String url = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.qy_getAppInfo), accessToken,
				"1");
		String result = HttpClientUtil.getInstance().sendHttpGet(url);
		System.out.println(result);
		GetqyApp ga = JsonUtil.getObjectByJSON(result, GetqyApp.class);
		msg.setData(ga);
		return msg;
	}

	/**
	 * 2.设置企业应用
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/setqyApp")
	public @ResponseBody
    ResultMsg setqyApp(SetqyApp set) throws JsonProcessingException {
		ResultMsg msg = new ResultMsg();
		String accessToken = Access_token.getAccessToken();
		String url = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.qy_setAppInfo),
				accessToken);
		set.setAgentid(1);
		set.setName("测试应用");
		String result = HttpClientUtil.getInstance().sendHttpPostJson(url, JsonUtil.parseToJson(set));
		BaseMsg baseMsg = JsonUtil.getObjectByJSON(result, BaseMsg.class);
		msg.setData(baseMsg);
		return msg;
	}

	/**
	 * 3.企业应用列表
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getqyAppList")
	public @ResponseBody
    ResultMsg getqyAppList() throws JsonProcessingException {
		ResultMsg msg = new ResultMsg();
		String accessToken = Access_token.getAccessToken();
		String url = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.qy_getAppList),
				accessToken);
		String result = HttpClientUtil.getInstance().sendHttpGet(url);
		GetqyAppList gal = JsonUtil.getObjectByJSON(result, GetqyAppList.class);
		msg.setData(gal);
		return msg;
	}

	/////////////////////////// 管理通讯录
	//// 单独搜索
	@RequestMapping(value = "/getAllDepartFromInternal")
	public @ResponseBody
    ResultMsg getAllDepartFromInternal(HttpServletRequest request, String searchKSName) {
		ResultMsg msg = new ResultMsg();
		///// 只需要科室名字的科室列表
		if (StringUtils.isNotBlank(searchKSName)) {
			List<WXDepartment> list = (List<WXDepartment>) request.getSession()
					.getAttribute(WeixinConstant.session_WXDepartment);
			if (null != list) {
				List<WXDepartment> rsList = new ArrayList<>();
				rsList.addAll(list);
				// 集合的过滤器工具
				CollectionUtils.filter(rsList, new Predicate() {
					@Override
					public boolean evaluate(Object object) {
						WXDepartment userInfo = (WXDepartment) object;
						String ksName = userInfo.getName();
						if (ksName.indexOf(searchKSName) != -1) {
							return true; // 保留的
						}
						return false;
					}
				});
				msg.setData(rsList);
			}
		}
		/////
		return msg;
	}

	/**
	 * 3.企业应用列表
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getAllDepart")
	public @ResponseBody
    ResultMsg getAllDepart(HttpServletRequest request) throws JsonProcessingException {
		ResultMsg msg = new ResultMsg();
		String accessToken = Access_token.getAccessToken();
		String url = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.qy_getAllDepart),
				accessToken);
		String result = HttpClientUtil.getInstance().sendHttpGet(url);
		DepartmentVo gal = JsonUtil.getObjectByJSON(result, DepartmentVo.class);
		List<WXDepartment> list = gal.getDepartment();
		request.getSession().setAttribute(WeixinConstant.session_WXDepartment, list);
		BootStrapTree rootEle = getRootTreeEle(list);
		BootStrapTree tree = getTree(list, rootEle);
		List<BootStrapTree> treeList = new ArrayList<>();
		treeList.add(tree);
		msg.setData(treeList);
		return msg;
	}

	private static BootStrapTree getRootTreeEle(List<WXDepartment> list) {
		for (WXDepartment dp : list) {
			Integer id = dp.getParentid();
			if (id == 0) {
				BootStrapTree rootTree = new BootStrapTree();
				rootTree.setId(dp.getId());
				rootTree.setText(dp.getName());
				rootTree.setLevel(0);
				rootTree.setPid(dp.getParentid());
				return rootTree;
			}
		}
		return null;
	}

	private static BootStrapTree getTree(List<WXDepartment> list, BootStrapTree rootEle) {
		List<BootStrapTree> nodes = new ArrayList<>();
		for (WXDepartment dp : list) {
			int id = rootEle.getId();
			int pid = dp.getParentid();
			int rootLevel = rootEle.getLevel();
			if (id == pid) {
				BootStrapTree tempTree = new BootStrapTree();
				tempTree.setId(dp.getId());
				tempTree.setText(dp.getName());
				tempTree.setPid(dp.getParentid());
				tempTree.setLevel(rootLevel + 1);
				nodes.add(tempTree);
				getTree(list, tempTree);
			}
		}
		rootEle.setNodes(nodes);
		return rootEle;
	}

	/**
	 * ztree模型展示
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getAllDepartForzTreeNodes")
	public @ResponseBody
    ResultMsg getAllDepartForzTreeNodes() throws JsonProcessingException {
		ResultMsg msg = new ResultMsg();
		String accessToken = Access_token.getAccessToken();
		String url = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.qy_getAllDepart),
				accessToken);
		String result = HttpClientUtil.getInstance().sendHttpGet(url);
		DepartmentVo gal = JsonUtil.getObjectByJSON(result, DepartmentVo.class);
		List<WXDepartment> list = gal.getDepartment();
		ZTreeVo rootEle = getRootTreeEleForZtree(list);
		ZTreeVo tree = getTreeForZtree(list, rootEle);
		List<ZTreeVo> treeList = new ArrayList<>();
		treeList.add(tree);
		msg.setData(treeList);
		return msg;
	}

	private static ZTreeVo getRootTreeEleForZtree(List<WXDepartment> list) {
		for (WXDepartment dp : list) {
			Integer id = dp.getParentid();
			if (id == 0) {
				ZTreeVo rootTree = new ZTreeVo();
				rootTree.setId(dp.getId());
				rootTree.setName(dp.getName());
				rootTree.setLevel(0);
				rootTree.setPid(dp.getParentid());
				return rootTree;
			}
		}
		return null;
	}

	private static ZTreeVo getTreeForZtree(List<WXDepartment> list, ZTreeVo rootEle) {
		List<ZTreeVo> nodes = new ArrayList<>();
		for (WXDepartment dp : list) {
			int id = rootEle.getId();
			int pid = dp.getParentid();
			int rootLevel = rootEle.getLevel();
			if (id == pid) {
				ZTreeVo tempTree = new ZTreeVo();
				tempTree.setId(dp.getId());
				tempTree.setName(dp.getName());
				tempTree.setPid(dp.getParentid());
				tempTree.setLevel(rootLevel + 1);
				nodes.add(tempTree);
				getTreeForZtree(list, tempTree);
			}
		}
		rootEle.setChildren(nodes);
		return rootEle;
	}

	/**
	 * ztree模型展示 simpledata
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getAllSimpleDataDepartForzTreeNodes")
	public @ResponseBody
    ResultMsg getAllSimpleDataDepartForzTreeNodes() throws JsonProcessingException {
		ResultMsg msg = new ResultMsg();
		String accessToken = Access_token.getAccessToken();
		String url = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.qy_getAllDepart),
				accessToken);
		String result = HttpClientUtil.getInstance().sendHttpGet(url);
		DepartmentVo gal = JsonUtil.getObjectByJSON(result, DepartmentVo.class);
		List<WXDepartment> list = gal.getDepartment();
		msg.setData(list);
		return msg;
	}

	//////////////////////////////// 根据部门获取人员
	@RequestMapping(value = "/getEmployeeByDepartMentID")
	public @ResponseBody
    ResultMsg getEmployeeByDepartMentID(Integer departmentId) throws JsonProcessingException {
		ResultMsg msg = new ResultMsg();
		String accessToken = Access_token.getAccessToken();
		String url = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.getEmployeeByDepartMentID),
				accessToken, departmentId, 1, 1);
		String result = HttpClientUtil.getInstance().sendHttpGet(url);
		System.out.println(result);
		SimpleEmpList emplist = JsonUtil.getObjectByJSON(result, SimpleEmpList.class);
		msg.setData(emplist);
		return msg;
	}

	/**
	 * 详细的人员列表
	 * 
	 * @param departmentId
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getDetailEmployeeByDepartMentID")
	public @ResponseBody
    ResultMsg getDetailEmployeeByDepartMentID(Integer departmentId)
			throws JsonProcessingException {
		ResultMsg msg = new ResultMsg();
		String accessToken = Access_token.getAccessToken();
		String url = MessageFormat.format(
				PropertiesUtil.weixinPropertiesVal(WeixinConstant.GETXIANGXIEMPLOYEEBYDEPARTMENTID), accessToken,
				departmentId, 1, 1);
		String result = HttpClientUtil.getInstance().sendHttpGet(url);
		DetailEmpList emplist = JsonUtil.getObjectByJSON(result, DetailEmpList.class);
		msg.setData(emplist);
		return msg;
	}

	//////////////////////////////////// 为企业员工发送信息
	@RequestMapping(value = "/SendMsgToEmployee")
	public @ResponseBody
    ResultMsg SendMsgToEmployee(String employeeID) throws JsonProcessingException {
		ResultMsg msg = new ResultMsg();
		String accessToken = Access_token.getAccessToken();
		String sendMSGRUL = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.SENDMSG),
				accessToken);
		String jsonStr = JsonUtil.parseToJson(WX_Util.getNews(8, employeeID, "测试发送", "描述"));
		String result = HttpClientUtil.getInstance().sendHttpPostJson(sendMSGRUL, jsonStr);
		msg.setData(result);
		return msg;
	}

	/**
	 * 
	 * @param employeeID
	 *            工号
	 * @param type
	 *            类型 1.充值 2.消费
	 * @param balance
	 *            金钱数
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/SendMsgToEmployeeForSDEY")
	public @ResponseBody
    ResultMsg SendMsgToEmployeeForSDEY(WXSendBalanceVo wxbalance) throws JsonProcessingException {
		ResultMsg msg = new ResultMsg();
		if (!wxbalance.validate()) {
			msg.setErrorCode(101);
			msg.setErrorMsg("缺少参数！");
			return msg;
		}
		String accessToken = Access_token.getAccessToken();
		String sendMSGRUL = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.SENDMSG),
				accessToken);
		StringBuilder sendMsg = new StringBuilder();
		switch (wxbalance.getType()) {
		case 1:
			sendMsg.append("流水号：").append(wxbalance.getSerialNumber()).append("  您的充值金额为：")
					.append(wxbalance.getBalance()).append(" 操作时间：").append(wxbalance.getDate()).append("  当前余额：")
					.append(wxbalance.getCurrentBalance());
			if (wxbalance.getRemarks() != null) {
				sendMsg.append("   备注：").append(wxbalance.getRemarks());
			}
			break;
		case 2:
			sendMsg.append("流水号：").append(wxbalance.getSerialNumber()).append("  您的消费金额为：")
					.append(wxbalance.getBalance()).append(" 操作时间：").append(wxbalance.getDate()).append("  当前余额：")
					.append(wxbalance.getCurrentBalance());
			if (wxbalance.getRemarks() != null) {
				sendMsg.append("   备注：").append(wxbalance.getRemarks());
			}
			break;
		default:

			break;
		}
		if (sendMsg.length() != 0) {
			String jsonStr = JsonUtil
					.parseToJson(WX_Util.getNews(8, wxbalance.getEmployeeID(), "我的账单", sendMsg.toString()));
			String result = HttpClientUtil.getInstance().sendHttpPostJson(sendMSGRUL, jsonStr);
			BaseMsg rtmsg = JsonUtil.getObjectByJSON(result, BaseMsg.class);
			rtmsg.setErrcode(rtmsg.getErrcode());
			rtmsg.setErrmsg(rtmsg.getErrmsg());
			msg.setData(result);
		} else {
			msg.setErrorCode(100);
			msg.setErrorMsg("发送失败！  类型错误");
		}
		return msg;
	}
}
