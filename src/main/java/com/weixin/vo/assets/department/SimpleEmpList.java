package com.weixin.vo.assets.department;

import java.util.List;

public class SimpleEmpList {
	private List<SimpleEmp> userlist;

	public List<SimpleEmp> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<SimpleEmp> userlist) {
		this.userlist = userlist;
	}

	@Override
	public String toString() {
		return "SimpleEmpList [userlist=" + userlist + "]";
	}

}
