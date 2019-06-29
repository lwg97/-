package com.uek.dao.inter;

import java.sql.SQLException;
import java.util.List;

import com.uek.model.Crowd;

/**
 * dao层是和数据库交互的 也就是增删改查数据库
 * 
 * 规定Crowd的数据库的操作 基本就是5个 增删改查（查单个 查所有）
 * @author 86139
 *
 */
public interface ICrowdDao {

//	void add(Crowd crowd);
	
	void add(Crowd crowd) /*throws SQLException*/;
	void delete(int id);
	void update(Crowd crowd);
	Crowd load(int id);
	List<Crowd> list();
	
	
	/**
	 * 根据群名查找单个群
	 * @param name
	 * @return 找到返回，没找到返回null
	 */
	Crowd loadByName(String name);
	
	/**
	 * 根据群名关键字查找所有群
	 * @param name
	 * @return 找到返回，没找到返回null
	 */
	List<Crowd> search(String nameKey);
	
	/**
	 * 根据会员id查找加入过的所有群
	 * @param name
	 * @return 找到返回，没找到返回null
	 */
	List<Crowd> search(int memberId);
	
	
}
