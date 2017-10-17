package com.baidumap.vo;

public class JingWeiToAddress_Result_poiRegions {
	private String name;
	private String direction_desc;
	private String tag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirection_desc() {
		return direction_desc;
	}

	public void setDirection_desc(String direction_desc) {
		this.direction_desc = direction_desc;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "JingWeiToAddress_Result_poiRegions [name=" + name + ", direction_desc=" + direction_desc + ", tag="
				+ tag + "]";
	}

}
