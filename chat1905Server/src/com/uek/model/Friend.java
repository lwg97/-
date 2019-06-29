package com.uek.model;

public class Friend {
	private int id;
	private int selfId;
	private int friendId;
	
	
	public Friend() {
		super();
	}


	public Friend(int selfId, int friendId) {
		super();
		this.selfId = selfId;
		this.friendId = friendId;
	}


	public Friend(int id, int selfId, int friendId) {
		super();
		this.id = id;
		this.selfId = selfId;
		this.friendId = friendId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getSelfId() {
		return selfId;
	}


	public void setSelfId(int selfId) {
		this.selfId = selfId;
	}


	public int getFriendId() {
		return friendId;
	}


	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	
	
	
}
