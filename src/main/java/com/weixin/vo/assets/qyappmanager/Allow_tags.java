package com.weixin.vo.assets.qyappmanager;

import java.util.List;

public class Allow_tags {
	private List<Integer> tagid;

	public List<Integer> getTagid() {
		return tagid;
	}

	public void setTagid(List<Integer> tagid) {
		this.tagid = tagid;
	}

	@Override
	public String toString() {
		return "Allow_tags [tagid=" + tagid + "]";
	}

}
