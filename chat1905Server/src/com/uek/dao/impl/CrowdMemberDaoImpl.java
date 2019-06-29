package com.uek.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.uek.dao.inter.ICrowdMemberDao;
import com.uek.model.CrowdMember;

public class CrowdMemberDaoImpl implements ICrowdMemberDao{

	@Override
	public void add(CrowdMember crowdMember) {
		
		
		//1 加载mysql数据库的驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
		
		//2 利用驱动管理器建立到数据库的连接 connection 就好比建立java程序和数据库的中间的桥梁
	
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_chat?useUnicode=true&characterEncoding=utf-8", "root", "");
	

			//3 利用connection建立一个小车，准备把sql语句推送到桥对面mysql服务器
			Statement stat = conn.createStatement();
			
			
			//4 拼接sql语句
			String sql = "insert into t_crowd_member(crowd_id,member_id,join_time) values("+crowdMember.getCrowdId()+","+crowdMember.getMemberId()+",'"+crowdMember.getJoinTime().toLocaleString()+"')";
		
			System.out.println(sql);
			//5 推过去到mysql执行并返回结果 只不过有时候不接收结果
			stat.executeUpdate(sql);
			//6 释放资源 过河拆桥
			stat.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CrowdMember crowdMember) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CrowdMember load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrowdMember> list() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
