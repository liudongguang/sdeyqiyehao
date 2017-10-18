package com.weixin.api.service;

import com.github.pagehelper.Page;
import com.weixin.api.po.MessageboardHuifu;
import com.weixin.api.po.MessageboardMessage;
import com.weixin.vo.MessageboardMessageSuper;
import com.weixin.vo.MessageboardSearchParam;
import com.weixin.vo.PageParam;

public interface MessageBoardService {

	int saveLiuyanContent(MessageboardMessage message);

	Page<MessageboardMessageSuper> liuYanList(PageParam pageParam, MessageboardSearchParam searchParam);

	MessageboardMessage getLiuYanByID(Integer id);

	int addLiuYanHuiFu(MessageboardHuifu msghuifu);

	Page<MessageboardHuifu> getLiuYanHuiFuList(PageParam pageParam, MessageboardSearchParam searchParam);

	int deleteLiuYanById(Integer messageid);

	int deletepinglunById(Integer id);	
}
