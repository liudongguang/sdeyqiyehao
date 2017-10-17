package com.baidumap.vo;

import java.util.List;

public class JingWeiToAddress_Result {
	private AddressToJingWei_Result_location location;
	private String formatted_address;
	private String business;
	private JingWeiToAddress_AddressComponent addressComponent;
	private List<JingWeiToAddress_Result_pois> pois;
	private List<JingWeiToAddress_Result_poiRegions> poiRegions;
	private String sematic_description;
	private Integer cityCode;

	public AddressToJingWei_Result_location getLocation() {
		return location;
	}

	public void setLocation(AddressToJingWei_Result_location location) {
		this.location = location;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public JingWeiToAddress_AddressComponent getAddressComponent() {
		return addressComponent;
	}

	public void setAddressComponent(JingWeiToAddress_AddressComponent addressComponent) {
		this.addressComponent = addressComponent;
	}

	public List<JingWeiToAddress_Result_pois> getPois() {
		return pois;
	}

	public void setPois(List<JingWeiToAddress_Result_pois> pois) {
		this.pois = pois;
	}

	public List<JingWeiToAddress_Result_poiRegions> getPoiRegions() {
		return poiRegions;
	}

	public void setPoiRegions(List<JingWeiToAddress_Result_poiRegions> poiRegions) {
		this.poiRegions = poiRegions;
	}

	public String getSematic_description() {
		return sematic_description;
	}

	public void setSematic_description(String sematic_description) {
		this.sematic_description = sematic_description;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	@Override
	public String toString() {
		return "JingWeiToAddress_Result [location=" + location + ", formatted_address=" + formatted_address
				+ ", business=" + business + ", addressComponent=" + addressComponent + ", pois=" + pois
				+ ", poiRegions=" + poiRegions + ", sematic_description=" + sematic_description + ", cityCode="
				+ cityCode + "]";
	}

}
