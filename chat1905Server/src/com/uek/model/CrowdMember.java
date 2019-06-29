package com.uek.model;

import java.util.Date;

public class CrowdMember {
	private int id;
	private int memberId;
	private int crowdId;
	private Date joinTime;
	private Date exitTime;
	
	public CrowdMember() {
		super();
	}
	public CrowdMember(int memberId, int crowdId, Date joinTime, Date exitTime) {
		super();
		this.memberId = memberId;
		this.crowdId = crowdId;
		this.joinTime = joinTime;
		this.exitTime = exitTime;
	}
	public CrowdMember(int id, int memberId, int crowdId, Date joinTime,
			Date exitTime) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.crowdId = crowdId;
		this.joinTime = joinTime;
		this.exitTime = exitTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getCrowdId() {
		return crowdId;
	}
	public void setCrowdId(int crowdId) {
		this.crowdId = crowdId;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public Date getExitTime() {
		return exitTime;
	}
	public void setExitTime(Date exitTime) {
		this.exitTime = exitTime;
	}
	
	//打印一個對象，默认就是调用对象的toString方法，默认的toString
	//方法是继承自Object，是com.uek.CrowdMember@1231321 看不懂 建议改写
	@Override
	public String toString() {
		return "CrowdMember [id=" + id + ", memberId=" + memberId
				+ ", crowdId=" + crowdId + ", joinTime=" + joinTime
				+ ", exitTime=" + exitTime + "]";
	}
	
	
}
