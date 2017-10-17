package com.weixin.vo.httprs;

import java.util.List;

public class QY_MemberInfo extends BaseMsg {
	private String userid;
	private String name;
	private List<Integer> department;
	private String mobile;
	private String gender;
	private String avatar;
	private Integer status;
	private Extattr extattr;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Extattr getExtattr() {
		return extattr;
	}

	public void setExtattr(Extattr extattr) {
		this.extattr = extattr;
	}

	@Override
	public String toString() {
		return super.toString()+"  QY_MemberInfo [userid=" + userid + ", name=" + name + ", department=" + department + ", mobile="
				+ mobile + ", gender=" + gender + ", avatar=" + avatar + ", status=" + status + ", extattr=" + extattr
				+ "]";
	}
	
}
