package com.weixin.vo.sdeyinterface;

import org.apache.commons.lang3.StringUtils;

public class WXSendBalanceVo {
	private String employeeID;
	private String serialNumber;// 流水号
	private Integer type; // 1.充值 2.消费
	private Double balance;// 充值或消费金额
	private String date; // 操作时间 yyyy-MM-dd HH:mm:ss
	private Double currentBalance; // 当前剩余金额
	private String remarks; // 备注

	/**
	 * 验证通过返回true
	 * 
	 * @return
	 */
	public boolean validate() {
		if (StringUtils.isBlank(employeeID) || StringUtils.isBlank(serialNumber) || type == null || balance == null
				|| StringUtils.isBlank(date) || currentBalance == null) {
			return false;
		}
		return true;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Override
	public String toString() {
		return "WXSendBalanceVo [employeeID=" + employeeID + ", serialNumber=" + serialNumber + ", type=" + type
				+ ", currentBalance=" + currentBalance + ", balance=" + balance + ", date=" + date + ", remarks="
				+ remarks + "]";
	}

}
