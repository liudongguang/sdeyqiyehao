package com.baidumap.vo;

public class JingWeiToAddress {
	private Integer status;
	private JingWeiToAddress_Result result;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public JingWeiToAddress_Result getResult() {
		return result;
	}

	public void setResult(JingWeiToAddress_Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "JingWeiToAddress [status=" + status + ", result=" + result + "]";
	}

}
