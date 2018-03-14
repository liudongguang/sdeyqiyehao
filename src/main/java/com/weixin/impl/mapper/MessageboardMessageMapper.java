package com.weixin.impl.mapper;

import com.github.pagehelper.Page;
import com.weixin.api.po.MessageboardMessage;
import com.weixin.vo.MessageboardMessageSuper;
import com.weixin.vo.MessageboardSearchParam;
import org.apache.ibatis.annotations.Param;

public interface MessageboardMessageMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table messageboard_message
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table messageboard_message
	 * @mbg.generated
	 */
	int insert(MessageboardMessage record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table messageboard_message
	 * @mbg.generated
	 */
	int insertSelective(MessageboardMessage record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table messageboard_message
	 * @mbg.generated
	 */
	MessageboardMessage selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table messageboard_message
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(MessageboardMessage record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table messageboard_message
	 * @mbg.generated
	 */
	int updateByPrimaryKey(MessageboardMessage record);

	Page<MessageboardMessageSuper> liuYanList(MessageboardSearchParam searchParam);


	MessageboardMessageSuper selectByPrimaryKeyForSuper(Integer id);
}