package com.dayuan.javabean;

import java.util.Date;

public class Transfer {
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private Integer outCard;
	private Integer inCard;
	private Date operTime;
	private String inUserName;
	private Integer streamId;
	
	public Integer getOutCard() {
		return outCard;
	}
	public void setOutCard(Integer outCard) {
		this.outCard = outCard;
	}
	public Integer getInCard() {
		return inCard;
	}
	public void setInCard(Integer inCard) {
		this.inCard = inCard;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public String getInUserName() {
		return inUserName;
	}
	public void setInUserName(String inUserName) {
		this.inUserName = inUserName;
	}
	public Integer getStreamId() {
		return streamId;
	}
	public void setStreamId(Integer streamId) {
		this.streamId = streamId;
	}
	
}
