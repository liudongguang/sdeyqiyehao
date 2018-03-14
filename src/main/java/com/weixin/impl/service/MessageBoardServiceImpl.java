package com.weixin.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.weixin.api.po.MessageboardHuifu;
import com.weixin.api.po.MessageboardMessage;
import com.weixin.api.service.MessageBoardService;
import com.weixin.impl.mapper.MessageboardHuifuMapper;
import com.weixin.impl.mapper.MessageboardMessageMapper;
import com.weixin.vo.MessageboardMessageSuper;
import com.weixin.vo.MessageboardSearchParam;
import com.weixin.vo.PageParam;

import java.util.List;

@Service
public class MessageBoardServiceImpl implements MessageBoardService {
	@Autowired
	private MessageboardMessageMapper messageDao;
	@Autowired
	private MessageboardHuifuMapper msgHFDao;

	@Override
	public int saveLiuyanContent(MessageboardMessage message) {
		return messageDao.insertSelective(message);
	}


	@Override
	public Page<MessageboardMessageSuper> liuYanList(PageParam pageParam, MessageboardSearchParam searchParam) {
		if (pageParam != null) {
			PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true);
		}
		Page<MessageboardMessageSuper> page = messageDao.liuYanList(searchParam);
		for (MessageboardMessageSuper msg : page) {
			//Integer plcount=msgHFDao.getPingLunCountByMsgID(msg.getId());
			List<MessageboardHuifu> pingLunByLY = msgHFDao.getPingLunByLYID(msg.getId());
			msg.setPingLunByLY(pingLunByLY);
		}
		return page;
	}

	@Override
	public MessageboardMessageSuper getLiuYanByID(Integer id) {
		MessageboardMessageSuper messageboardMessageSuper = messageDao.selectByPrimaryKeyForSuper(id);
		messageboardMessageSuper.setPingLunByLY(msgHFDao.getPingLunByLYID(messageboardMessageSuper.getId()));
		return messageboardMessageSuper;
	}

	@Override
	public int addLiuYanHuiFu(MessageboardHuifu msghuifu) {
		return msgHFDao.insertSelective(msghuifu);
	}

	@Override
	public Page<MessageboardHuifu> getLiuYanHuiFuList(PageParam pageParam, MessageboardSearchParam searchParam) {
		if (pageParam != null) {
			PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true);
		}
		return msgHFDao.getLiuYanHuiFuList(searchParam);
	}

	@Override
	public int deleteLiuYanById(Integer messageid) {
		return messageDao.deleteByPrimaryKey(messageid);
	}

	@Override
	public int deletepinglunById(Integer id) {
		// TODO Auto-generated method stub
		return msgHFDao.deleteByPrimaryKey(id);
	}

}
