package com.uek.dao.inter;

import java.util.List;

import com.uek.model.Friend;

/**
 * dao层是和数据库交互的 也就是增删改查数据库
 * 
 * 规定Friend的数据库的操作 基本就是5个 增删改查（查单个 查所有）
 * @author 86139
 *
 */
public interface IFriendDao {

	void add(Friend friend);
	void delete(int id);
	void update(Friend friend);
	Friend load(int id);
	List<Friend> list();
	
	
}
