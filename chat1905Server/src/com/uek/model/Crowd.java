package com.uek.model;

import java.io.Serializable;
import java.util.Date;

public class Crowd implements Serializable {
	private int id;
	private String crowdName;
	private Date createTime;
	private String description;

	private int createrId;

	public Crowd() {
		super();
	}

	public Crowd(String crowdName, Date createDate, String description,
			int createrId) {
		super();
		this.crowdName = crowdName;
		this.createTime = createDate;
		this.description = description;
		this.createrId = createrId;
	}

	public Crowd(int id, String crowdName, Date createDate, String description,
			int createrId) {
		super();
		this.id = id;
		this.crowdName = crowdName;
		this.createTime = createDate;
		this.description = description;
		this.createrId = createrId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCrowdName() {
		return crowdName;
	}

	public void setCrowdName(String crowdName) {
		this.crowdName = crowdName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCreaterId() {
		return createrId;
	}

	public void setCreaterId(int createrId) {
		this.createrId = createrId;
	}

	@Override
	public String toString() {
		return "Crowd [id=" + id + ", crowdName=" + crowdName + ", createDate="
				+ createTime + ", description=" + description + ", createrId="
				+ createrId + "]";
	}

}
