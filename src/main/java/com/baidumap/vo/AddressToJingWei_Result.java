package com.baidumap.vo;

public class AddressToJingWei_Result {
	private String level;
	private Integer confidence;
	private Integer precise;
	private AddressToJingWei_Result_location location;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getConfidence() {
		return confidence;
	}

	public void setConfidence(Integer confidence) {
		this.confidence = confidence;
	}

	public Integer getPrecise() {
		return precise;
	}

	public void setPrecise(Integer precise) {
		this.precise = precise;
	}

	public AddressToJingWei_Result_location getLocation() {
		return location;
	}

	public void setLocation(AddressToJingWei_Result_location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "AddressToJingWei_Result [level=" + level + ", confidence=" + confidence + ", precise=" + precise
				+ ", location=" + location + "]";
	}

}
