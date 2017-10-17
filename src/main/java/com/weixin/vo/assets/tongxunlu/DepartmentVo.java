package com.weixin.vo.assets.tongxunlu;

import com.weixin.vo.httprs.BaseMsg;

import java.util.List;

public class DepartmentVo extends BaseMsg {
	private List<WXDepartment> department;

	public List<WXDepartment> getDepartment() {
		return department;
	}

	public void setDepartment(List<WXDepartment> department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "DepartmentVo [department=" + department + "]";
	}

}
