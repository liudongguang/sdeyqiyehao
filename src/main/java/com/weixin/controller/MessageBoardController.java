package com.weixin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.ldg.api.vo.ResultMsg;
import com.weixin.api.po.MessageboardHuifu;
import com.weixin.api.po.MessageboardMessage;
import com.weixin.api.service.MessageBoardService;
import com.weixin.constant.WeixinConstant;
import com.weixin.util.Access_token;
import com.weixin.util.HttpClientUtil;
import com.weixin.util.PropertiesUtil;
import com.weixin.vo.MessageboardMessageSuper;
import com.weixin.vo.MessageboardSearchParam;
import com.weixin.vo.PageParam;
import com.weixin.vo.ResponseStream;

@Controller
@RequestMapping(value = "/messageboard")
public class MessageBoardController {
	@Autowired
	private MessageBoardService messagesv;

	/**
	 * 跳转页面
	 * 
	 * @param pageUrl
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/jumpPage")
	public String jumpPage(String pageUrl) throws IOException {
		System.out.println(pageUrl);
		return pageUrl;
	}

	/**
	 * 增加留言
	 * 
	 * @param message
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/addLiuYan")
	public String addLiuYan(HttpServletRequest request, MessageboardMessage message) throws IOException {
		message.setCreatetime(new Date());
		String wximages = message.getMessageimages();
		String[] imgArr = wximages.split(",");
		String accessToken = Access_token.getAccessToken();
		StringBuilder newImgs = new StringBuilder();
		for (String wximgServerID : imgArr) {
			String url = MessageFormat.format(PropertiesUtil.weixinPropertiesVal(WeixinConstant.getLinShiSC),
					accessToken, wximgServerID);
			ResponseStream rsm = HttpClientUtil.getInstance().sendHttpGetForInputStream(url);
			switch (rsm.getContentType()) {
			case WeixinConstant.TYPE_jpeg:
				StringBuilder dbIMGPath = new StringBuilder(WeixinConstant.uploadPath_liuyan);
				String serverRealPath = request.getServletContext().getRealPath(WeixinConstant.uploadPath_liuyan);
				File severFileMdr = new File(serverRealPath);
				if (!severFileMdr.exists()) {
					severFileMdr.mkdirs();
				}
				StringBuilder saveFile = new StringBuilder(serverRealPath);
				StringBuilder fileName = new StringBuilder(UUID.randomUUID().toString());
				fileName.append(".jpg");
				dbIMGPath.append("/").append(fileName);
				saveFile.append("/").append(fileName);
				newImgs.append(dbIMGPath).append(",");
				InputStream ins = rsm.getIn();
				File file = new File(saveFile.toString());
				FileOutputStream fos = new FileOutputStream(file);
				IOUtils.copy(ins, fos);
				IOUtils.closeQuietly(fos);
				break;
			default:
				break;
			}
		}
		if (newImgs.length() != 0) {
			if (newImgs.lastIndexOf(",") != -1) {
				message.setMessageimages(newImgs.substring(0, newImgs.length() - 1));
			} else {
				message.setMessageimages(newImgs.toString());
			}
		}
		int i = messagesv.saveLiuyanContent(message);
		return "/messageboard/liuYanList";
	}

	/**
	 * 留言列表
	 * 
	 * @param request
	 * @param pageParam
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/liuYanList")
	public String liuYanList(HttpServletRequest request, PageParam pageParam,MessageboardSearchParam searchParam)
			throws UnsupportedEncodingException {
		Page<MessageboardMessageSuper> page = messagesv.liuYanList(pageParam, searchParam);
		request.setAttribute("page", page.toPageInfo());
		return "/messageboard2/list.jsp";
	}

	@RequestMapping(value = "/liuYanListForMore")
	public String liuYanListForMore(HttpServletRequest request, PageParam pageParam,MessageboardSearchParam searchParam)
			throws UnsupportedEncodingException {
		Page<MessageboardMessageSuper> page = messagesv.liuYanList(pageParam, searchParam);
		request.setAttribute("page", page.toPageInfo());
		return "/messageboard2/load.jsp";
	}

	/**
	 * 获取留言详情
	 * 
	 * @param request	 *
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getLiuYanByID")
	public String getLiuYanByID(HttpServletRequest request, Integer id) throws UnsupportedEncodingException {
		MessageboardMessageSuper msg = messagesv.getLiuYanByID(id);
		request.setAttribute("object", msg);
		return "/messageboard2/blog_show.jsp";
	}

	/**
	 * 回复留言板 评论
	 * 
	 * @param request
	 * @param msghuifu
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/addLiuYanHuiFu")
	public @ResponseBody ResultMsg addLiuYanHuiFu(HttpServletRequest request, MessageboardHuifu msghuifu)
			throws UnsupportedEncodingException {
		ResultMsg msg = new ResultMsg();
		msghuifu.setCreatetime(new Date());
		int i = messagesv.addLiuYanHuiFu(msghuifu);
		msg.setData(msghuifu);
		return msg;
	}

	/**
	 * 评论列表
	 * 
	 * @param request	 *
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getLiuYanHuiFuList")
	public String getLiuYanHuiFuList(HttpServletRequest request,  MessageboardSearchParam searchParam, PageParam pageParam)
			throws UnsupportedEncodingException {
		Page<MessageboardHuifu> page = messagesv.getLiuYanHuiFuList(pageParam, searchParam);
		request.setAttribute("page", page.toPageInfo());
		return "/messageboard/listshow_pinglun.jsp";
	}

	/**
	 * 评论列表 更多
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getLiuYanHuiFuListForMore")
	public String getLiuYanHuiFuListForMore(HttpServletRequest request,  MessageboardSearchParam searchParam, PageParam pageParam)
			throws UnsupportedEncodingException {
		Page<MessageboardHuifu> page = messagesv.getLiuYanHuiFuList(pageParam, searchParam);
		request.setAttribute("morepage", page.toPageInfo());
		return "/messageboard/listshow_pinglun_more.jsp";
	}
	
	
	

	@RequestMapping(value = "/deleteLiuYanById")
	public String deleteLiuYanById(HttpServletRequest request, Integer messageid)
			throws UnsupportedEncodingException {
		messagesv.deleteLiuYanById(messageid);
		return "/messageboard/managerLiuYan";
	}
	@RequestMapping(value = "/deletepinglunById")
	public String deletepinglunById(HttpServletRequest request, Integer id, Integer messageid)
			throws UnsupportedEncodingException {
		messagesv.deletepinglunById(id);
		return "/messageboard/getLiuYanPingLunByID?messageid="+messageid;
	}
	
	
	@RequestMapping(value = "/managerLiuYan")
	public String managerLiuYan(HttpServletRequest request, MessageboardSearchParam searchParam, PageParam pageParam)
			throws UnsupportedEncodingException {
		Page<MessageboardMessageSuper> page = messagesv.liuYanList(pageParam, searchParam);
		request.setAttribute("page", page.toPageInfo());	
		return "/messageboard/manager/liuyangl.jsp";
	}
	@RequestMapping(value = "/getLiuYanPingLunByID")
	public String getLiuYanPingLunByID(HttpServletRequest request,  MessageboardSearchParam searchParam, PageParam pageParam)
			throws UnsupportedEncodingException {
		Page<MessageboardHuifu> page = messagesv.getLiuYanHuiFuList(pageParam, searchParam);
		request.setAttribute("page", page.toPageInfo());	
		return "/messageboard/manager/pinglun.jsp";
	}
	
}
