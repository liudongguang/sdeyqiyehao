package com.baidumap.vo;

public class AddressToJingWei {
	private Integer status;
	private AddressToJingWei_Result result;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public AddressToJingWei_Result getResult() {
		return result;
	}

	public void setResult(AddressToJingWei_Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "AddressToJingWei [status=" + status + ", result=" + result + "]";
	}

}
