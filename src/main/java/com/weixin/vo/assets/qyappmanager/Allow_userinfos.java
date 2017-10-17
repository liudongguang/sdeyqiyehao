package com.weixin.vo.assets.qyappmanager;

import java.util.List;

public class Allow_userinfos {
	private List<Allow_userinfos_User> user;

	public List<Allow_userinfos_User> getUser() {
		return user;
	}

	public void setUser(List<Allow_userinfos_User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Allow_userinfos [user=" + user + "]";
	}

}
