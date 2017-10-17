package com.weixin.api.service;

import com.github.pagehelper.Page;
import com.weixin.api.po.YzcxQxusers;
import com.weixin.vo.PageParam;

public interface YZCXService {
   int saveQXUser(YzcxQxusers qxuser);

	Page<YzcxQxusers> selectQXUsers(PageParam pageParam);

	int delQXUserByID(Integer id);

	int delQXUserByIDS(String ids);

	YzcxQxusers getQXUserById(Integer id);
}
