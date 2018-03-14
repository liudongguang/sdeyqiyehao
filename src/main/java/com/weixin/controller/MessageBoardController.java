package com.weixin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.ldg.api.util.RequestFileUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
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
import org.springframework.web.multipart.MultipartFile;

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
	public @ResponseBody ResultMsg addLiuYan(HttpServletRequest request, MessageboardMessage message) throws IOException {
		message.setCreatetime(new Date());
		List<MultipartFile> files = RequestFileUtil.getUploadFile(request);
		StringBuilder images=null;
		System.out.println("图片信息。。。。。。。。。。。。。。{}"+files.size());
		if (!CollectionUtils.isEmpty(files)) {
			images=new StringBuilder();
			File savePath=new File(request.getServletContext().getRealPath(WeixinConstant.uploadPath_liuyan));
			if(!savePath.exists()){
				savePath.mkdirs();
			}
			for (int i = 0; i < files.size(); i++) {
				MultipartFile mfile = files.get(i);
				String fileName = RequestFileUtil.getSaveFileName(mfile.getOriginalFilename());
				File localFile = new File(request.getServletContext().getRealPath(WeixinConstant.uploadPath_liuyan) + "/" + fileName);
				mfile.transferTo(localFile);
				images.append(WeixinConstant.uploadPath_liuyan).append("/").append(fileName).append(",");
			}
		}
		if (null!=images&&images.length() != 0) {
			if (images.lastIndexOf(",") != -1) {
				message.setMessageimages(images.substring(0, images.length() - 1));
			} else {
				message.setMessageimages(images.toString());
			}
		}
		int i = messagesv.saveLiuyanContent(message);
		ResultMsg msg=new ResultMsg();
		return msg;
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
