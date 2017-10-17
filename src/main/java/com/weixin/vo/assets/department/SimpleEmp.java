package com.weixin.vo.assets.department;

import java.util.List;

public class SimpleEmp {
	private String userid;// 企业号内的唯一标识
	private String name;// 姓名
	private List<Integer> department;// 所属部门

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getDepartment() {
		return department;
	}

	public void setDepartment(List<Integer> department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "SimpleEmp [userid=" + userid + ", name=" + name + ", department=" + department + "]";
	}

}
