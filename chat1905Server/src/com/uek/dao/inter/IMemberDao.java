package com.uek.dao.inter;

import java.sql.SQLException;
import java.util.List;

import com.uek.model.Member;

/**
 * dao层是和数据库交互的 也就是增删改查数据库
 * 
 * 规定Member的数据库的操作 基本就是5个 增删改查（查单个 查所有）
 * @author 86139
 *
 */
public interface IMemberDao {

//	void add(Member member);
	
	void add(Member member) /*throws SQLException*/;
	void delete(int id);
	void update(Member member);
	Member load(int id);
	List<Member> list();
	
	
	/**
	 * 根据用户名查找单个用户
	 * @param name
	 * @return 找到返回，没找到返回null
	 */
	Member loadByName(String name);
	
	/**
	 * 根据用户名关键字查找所有用户
	 * @param name
	 * @return 找到返回，没找到返回null
	 */
	List<Member> search(String nameKey);
	
	
	/**
	 * 根据用户名查找好友列表
	 * @param name
	 * @return
	 */
	List<Member> loadFriends(String name);
	
	/**
	 * 根据用户id查找好友列表
	 * @param name
	 * @return
	 */
	List<Member> loadFriends(int selfId);
	
	
	/**
	 * 根据群id查找所有群成员
	 * @param crowdId
	 * @return 找到返回ArrayList
	 */
	List<Member> listMembersByCrowdId(int crowdId);
}
