package com.baidumap.vo;

public class AddressToJingWei_Result_location {

	private Double lng;
	private Double lat;

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "AddressToJingWei_Result_location [lng=" + lng + ", lat=" + lat + "]";
	}

}
