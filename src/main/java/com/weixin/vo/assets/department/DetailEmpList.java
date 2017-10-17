package com.weixin.vo.assets.department;

import java.util.List;

public class DetailEmpList {
	private List<DetailEmp> userlist;

	public List<DetailEmp> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<DetailEmp> userlist) {
		this.userlist = userlist;
	}

	@Override
	public String toString() {
		return "DetailEmpList [userlist=" + userlist + "]";
	}

}
