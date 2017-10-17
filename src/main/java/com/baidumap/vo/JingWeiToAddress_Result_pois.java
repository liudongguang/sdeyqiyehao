package com.baidumap.vo;

public class JingWeiToAddress_Result_pois {
	private String name;
	private String addr;
	private String cp;
	private String direction;
	private Integer distance;
	private String poiType;
	private String tag;
	private String tel;
	private String uid;
	private String zip;
	private JingWeiToAddress_Result_pois_point point;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getPoiType() {
		return poiType;
	}

	public void setPoiType(String poiType) {
		this.poiType = poiType;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public JingWeiToAddress_Result_pois_point getPoint() {
		return point;
	}

	public void setPoint(JingWeiToAddress_Result_pois_point point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "JingWeiToAddress_Result_pois [name=" + name + ", addr=" + addr + ", cp=" + cp + ", direction="
				+ direction + ", distance=" + distance + ", poiType=" + poiType + ", tag=" + tag + ", tel=" + tel
				+ ", uid=" + uid + ", zip=" + zip + ", point=" + point + "]";
	}

}
