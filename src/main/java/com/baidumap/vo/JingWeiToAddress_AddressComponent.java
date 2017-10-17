package com.baidumap.vo;

public class JingWeiToAddress_AddressComponent {
	private String country;
	private Integer country_code;
	private String province;
	private String city;
	private String district;
	private String adcode;
	private String street;
	private String street_number;
	private String direction;
	private String distance;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getCountry_code() {
		return country_code;
	}

	public void setCountry_code(Integer country_code) {
		this.country_code = country_code;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet_number() {
		return street_number;
	}

	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "JingWeiToAddress_AddressComponent [country=" + country + ", country_code=" + country_code
				+ ", province=" + province + ", city=" + city + ", district=" + district + ", adcode=" + adcode
				+ ", street=" + street + ", street_number=" + street_number + ", direction=" + direction + ", distance="
				+ distance + "]";
	}

}
