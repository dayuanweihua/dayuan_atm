package com.dayuan.javabean;

import java.util.Date;

public class MoneyStream {
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private Date operTime;
	private String operType;
	private Integer operMoney;
	
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
	public Integer getOperMoney() {
		return operMoney;
	}
	public void setOperMoney(Integer operMoney) {
		this.operMoney = operMoney;
	}
}
