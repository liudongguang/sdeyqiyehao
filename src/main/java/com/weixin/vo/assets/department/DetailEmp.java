package com.weixin.vo.assets.department;

public class DetailEmp extends SimpleEmp {
	private String avatar;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return super.toString() + "   DetailEmp [avatar=" + avatar + "]";
	}

}
