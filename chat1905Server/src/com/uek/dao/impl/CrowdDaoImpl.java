package com.uek.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.uek.dao.inter.ICrowdDao;
import com.uek.model.Crowd;
import com.uek.utils.DBUtil;

public class CrowdDaoImpl implements ICrowdDao{

	@Override
	public void add(Crowd crowd){
		Connection conn = null;
		Statement stat = null;
		
		try {
			conn = DBUtil.getConnection();
			
			stat = conn.createStatement();
			
			//4 拼接sql语句
			String sql = "insert into t_crowd(crowd_name,description,create_time,creater_id) values('"+crowd.getCrowdName()+"','"+crowd.getDescription()+"','"+new Date().toLocaleString()+"',"+crowd.getCreaterId()+")";
		
			//5 推过去到mysql执行并返回结果 只不过有时候不接收结果
			stat.executeUpdate(sql);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.freeResource(conn, stat);
		}
		
		
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Crowd crowd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Crowd load(int id) {
		
		Crowd crowd = null;
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			
			stat = conn.createStatement();
			
			//4 拼接sql语句
			String sql = "select * from t_crowd where id="+id;
		
			rs = stat.executeQuery(sql);
			
			if(rs.next())
			{
				crowd = new Crowd();
			
				crowd.setId((Integer)rs.getInt("id"));		
				crowd.setCrowdName(rs.getString("crowd_name"));
				crowd.setDescription(rs.getString("description"));
				crowd.setCreateTime(rs.getDate("create_time"));
				crowd.setCreaterId(rs.getInt("creater_id"));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.freeResource(conn, stat,rs);
		}
		
					
		return crowd;
	}

	@Override
	public List<Crowd> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Crowd loadByName(String name) {
		

		
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
			String sql = "select * from t_crowd where crowd_name='"+name+"'";
		
			System.out.println(sql);
			//5 推过去到mysql执行并返回结果 只不过有时候不接收结果
			ResultSet rs = stat.executeQuery(sql);
			
			//最多有一条 
			if(rs.next())
			{
				Crowd crowd = new Crowd();
				
				crowd.setId((Integer)rs.getInt("id"));		
				crowd.setCrowdName(rs.getString("crowd_name"));
				crowd.setDescription(rs.getString("description"));
				crowd.setCreateTime(rs.getDate("create_time"));
				crowd.setCreaterId(rs.getInt("creater_id"));
	
				return crowd;
			}
			
			//6 释放资源 过河拆桥
			stat.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		return null;
	}

	@Override
	public List<Crowd> search(String nameKey) {
		
		ArrayList<Crowd> crowds = new ArrayList<Crowd>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();

			//3 利用connection建立一个小车，准备把sql语句推送到桥对面mysql服务器
			stat = conn.createStatement();
			
			
			//4 拼接sql语句
			String sql = "select * from t_crowd where crowd_name like '%"+nameKey+"%'";
		
			System.out.println(sql);
			//5 推过去到mysql执行并返回结果 只不过有时候不接收结果
			rs = stat.executeQuery(sql);
			
			//最多有一条 
			while(rs.next())
			{
				Crowd crowd = new Crowd();
				
				crowd.setId((Integer)rs.getInt("id"));		
				crowd.setCrowdName(rs.getString("crowd_name"));
				crowd.setDescription(rs.getString("description"));
				crowd.setCreateTime(rs.getDate("create_time"));
				crowd.setCreaterId(rs.getInt("creater_id"));
	
				crowds.add(crowd);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			DBUtil.freeResource(conn, stat, rs);
		}
	
		
		
		return crowds;
	}

	@Override
	public List<Crowd> search(int memberId) {
	
		ArrayList<Crowd> crowds = new ArrayList<Crowd>();
		
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_chat?useUnicode=true&characterEncoding=utf-8", "root", "");
	

			//3 利用connection建立一个小车，准备把sql语句推送到桥对面mysql服务器
			stat = conn.createStatement();
			
			
			//4 拼接sql语句
			String sql = "select t2.* from t_crowd_member t1 join t_crowd t2 on t1.crowd_id=t2.id where t1.member_id="+memberId;
		
			System.out.println(sql);
			//5 推过去到mysql执行并返回结果 只不过有时候不接收结果
			rs = stat.executeQuery(sql);
			
			//最多有一条 
			while(rs.next())
			{
				Crowd crowd = new Crowd();
				
				crowd.setId((Integer)rs.getInt("id"));		
				crowd.setCrowdName(rs.getString("crowd_name"));
				crowd.setDescription(rs.getString("description"));
				crowd.setCreateTime(rs.getDate("create_time"));
				crowd.setCreaterId(rs.getInt("creater_id"));
	
				crowds.add(crowd);
			}
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.freeResource(conn, stat, rs);
		}
	
		
		
		return crowds;
		
	}

	
}
