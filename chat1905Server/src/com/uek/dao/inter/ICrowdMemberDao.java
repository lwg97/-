package com.uek.dao.inter;

import java.util.List;

import com.uek.model.CrowdMember;

/**
 * dao层是和数据库交互的 也就是增删改查数据库
 * 
 * 规定CrowdMember的数据库的操作 基本就是5个 增删改查（查单个 查所有）
 * @author 86139
 *
 */
public interface ICrowdMemberDao {

	void add(CrowdMember crowdMember);
	void delete(int id);
	void update(CrowdMember crowdMember);
	CrowdMember load(int id);
	List<CrowdMember> list();
	
	
}
