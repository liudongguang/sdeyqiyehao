package com.weixin.impl.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.weixin.api.po.YzcxQxlist;
import com.weixin.api.po.YzcxQxusers;
import com.weixin.api.service.YZCXService;
import com.weixin.impl.mapper.YzcxQxlistMapper;
import com.weixin.impl.mapper.YzcxQxusersMapper;
import com.weixin.vo.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YZCXServiceImpl implements YZCXService {
	@Autowired
	private YzcxQxusersMapper yzcxqx;
	@Autowired
	private YzcxQxlistMapper qxlistDao;

	@Override
	public int saveQXUser(YzcxQxusers qxuser) {
		Integer id = yzcxqx.selectByWXUserID(qxuser.getUserid());
		int exint = 0;
		if (id != null) {
			exint = yzcxqx.updateByByWXUserIDSelective(qxuser);
		} else {
			qxuser.setCreatetime(new Date());
			exint = yzcxqx.insertSelective(qxuser);
		}

		return exint;
	}

	@Override
	public Page<YzcxQxusers> selectQXUsers(PageParam pageParam) {
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true);
		Page<YzcxQxusers> list = yzcxqx.selectQXUsers();
		Map<String, String> map = getQxMap();
		for (YzcxQxusers user : list) {
			StringBuilder str = new StringBuilder();
			String qxids = user.getQxids();
			String[] qxidsArr = qxids.split(",");
			for (String qx : qxidsArr) {
				str.append(map.get(qx)).append(",");
			}
			user.setQxids(str.substring(0, str.length() - 1));
		}
		return list;
	}

	private Map<String, String> getQxMap() {
		Map<String, String> map = new HashMap<String, String>();
		List<YzcxQxlist> quList = qxlistDao.selectAllList();
		for (YzcxQxlist qx : quList) {
			map.put(qx.getKeynum(), qx.getName());
		}
		return map;
	}

	@Override
	public int delQXUserByID(Integer id) {
		return yzcxqx.deleteByPrimaryKey(id);
	}

	@Override
	public int delQXUserByIDS(String ids) {
		return yzcxqx.delQXUserByIDS(ids.split(","));
	}

	@Override
	public YzcxQxusers getQXUserById(Integer id) {
		return yzcxqx.selectByPrimaryKey(id);
	}

}
