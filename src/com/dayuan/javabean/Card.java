package com.dayuan.javabean;

public class Card {
	public static final int CARD_LENGTH=19;
	private Integer id;
	private String cardNo;
	private String password;
	private Integer version;
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private Integer money;
	private Integer personId;
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String string) {
		this.cardNo = string;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	
}
