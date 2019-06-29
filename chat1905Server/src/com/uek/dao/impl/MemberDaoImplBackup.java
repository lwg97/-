package com.uek.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.uek.dao.inter.IMemberDao;
import com.uek.model.Member;

public class MemberDaoImplBackup implements IMemberDao{

	@Override
	public void add(Member member) {
		
		
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
			String sql = "insert into t_member(name,nickname,signature,password,header) values('"+member.getName()+"','"+member.getNickname()+"','"+member.getSignature()+"','"+member.getPassword()+"','"+member.getHeader()+"')";
		
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
	public void update(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Member load(int id) {
		
		Member member = null;
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
					String sql = "select * from t_member where id="+id;
				
					ResultSet rs = stat.executeQuery(sql);
					
					if(rs.next())
					{
						member = new Member();
						//Object强转成Integer ，对象不可以强转成基本数据类型int
						//jdk1.5之后 自动拆箱 ，Integer可以自动转成int，反过来自动装箱
//						member.setId((Integer)rs.getObject(0));		方式1
//						member.setId((Integer)rs.getObject("id"));	方式2
						member.setId((Integer)rs.getInt("id"));		//最常用方式
						member.setName(rs.getString("name"));
						member.setNickname(rs.getString("nickname"));
						member.setSignature(rs.getString("signature"));
						member.setPassword(rs.getString("password"));
						member.setHeader(rs.getString("header"));
						
						//以上的代码非常类似，没有什么技术含量，怎么能减少呢？如果只是写以上类似代码，码农
						//这里我们会教大家利用教高级的知识消除冗余代码，增强扩展性！！！合适是反射！！！
						//没有反射就没有框架，所有的后续框架 ssh ssm springboot 反射是基础
						
					}
					
					//6 释放资源 过河拆桥
					stat.close();
					conn.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				return member;
	}

	@Override
	public List<Member> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member loadByName(String name) {
		

		
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
			String sql = "select * from t_member where name='"+name+"'";
		
			System.out.println(sql);
			//5 推过去到mysql执行并返回结果 只不过有时候不接收结果
			ResultSet rs = stat.executeQuery(sql);
			
			//最多有一条 
			if(rs.next())
			{
				Member member = new Member();
				//Object强转成Integer ，对象不可以强转成基本数据类型int
				//jdk1.5之后 自动拆箱 ，Integer可以自动转成int，反过来自动装箱
//				member.setId((Integer)rs.getObject(0));		方式1
//				member.setId((Integer)rs.getObject("id"));	方式2
				member.setId((Integer)rs.getInt("id"));		//最常用方式
				member.setName(rs.getString("name"));
				member.setNickname(rs.getString("nickname"));
				member.setSignature(rs.getString("signature"));
				member.setPassword(rs.getString("password"));
				member.setHeader(rs.getString("header"));
				
				//以上的代码非常类似，没有什么技术含量，怎么能减少呢？如果只是写以上类似代码，码农
				//这里我们会教大家利用教高级的知识消除冗余代码，增强扩展性！！！合适是反射！！！
				//没有反射就没有框架，所有的后续框架 ssh ssm springboot 反射是基础
				
				return member;
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
	public List<Member> loadFriends(String name) {
		//返回列表的这种方法，先定义一个空的ArrayList集合
		ArrayList<Member> friends = new ArrayList<Member>();
		
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
			String sql = "SELECT friend.* FROM t_friend t1, t_member self, t_member friend WHERE t1.self_id = self.id AND t1.friend_id = friend.id AND self. NAME = '"+name+"'";
		
			System.out.println(sql);
			//5 推过去到mysql执行并返回结果 只不过有时候不接收结果
			ResultSet rs = stat.executeQuery(sql);
			
			//最多有一条 
			while(rs.next())
			{
				Member member = new Member();
				//Object强转成Integer ，对象不可以强转成基本数据类型int
				//jdk1.5之后 自动拆箱 ，Integer可以自动转成int，反过来自动装箱
//				member.setId((Integer)rs.getObject(0));		方式1
//				member.setId((Integer)rs.getObject("id"));	方式2
				member.setId((Integer)rs.getInt("id"));		//最常用方式
				member.setName(rs.getString("name"));
				member.setNickname(rs.getString("nickname"));
				member.setSignature(rs.getString("signature"));
				member.setPassword(rs.getString("password"));
				member.setHeader(rs.getString("header"));
				
				//以上的代码非常类似，没有什么技术含量，怎么能减少呢？如果只是写以上类似代码，码农
				//这里我们会教大家利用教高级的知识消除冗余代码，增强扩展性！！！合适是反射！！！
				//没有反射就没有框架，所有的后续框架 ssh ssm springboot 反射是基础
				
				//有一个好友，加入到ArrayList当中
				friends.add(member);
			}
			
			//6 释放资源 过河拆桥
			stat.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		return friends;
	}

	@Override
	public List<Member> loadFriends(int selfId) {
		//返回列表的这种方法，先定义一个空的ArrayList集合
				ArrayList<Member> friends = new ArrayList<Member>();
				
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
					String sql = "select t2.* from t_friend t1 join t_member t2 on t1.friend_id=t2.id where t1.self_id="+selfId;
				
					System.out.println(sql);
					//5 推过去到mysql执行并返回结果 只不过有时候不接收结果
					ResultSet rs = stat.executeQuery(sql);
					
					//最多有一条 
					while(rs.next())
					{
						Member member = new Member();
						//Object强转成Integer ，对象不可以强转成基本数据类型int
						//jdk1.5之后 自动拆箱 ，Integer可以自动转成int，反过来自动装箱
//						member.setId((Integer)rs.getObject(0));		方式1
//						member.setId((Integer)rs.getObject("id"));	方式2
						member.setId((Integer)rs.getInt("id"));		//最常用方式
						member.setName(rs.getString("name"));
						member.setNickname(rs.getString("nickname"));
						member.setSignature(rs.getString("signature"));
						member.setPassword(rs.getString("password"));
						member.setHeader(rs.getString("header"));
						
						//以上的代码非常类似，没有什么技术含量，怎么能减少呢？如果只是写以上类似代码，码农
						//这里我们会教大家利用教高级的知识消除冗余代码，增强扩展性！！！合适是反射！！！
						//没有反射就没有框架，所有的后续框架 ssh ssm springboot 反射是基础
						
						//有一个好友，加入到ArrayList当中
						friends.add(member);
					}
					
					//6 释放资源 过河拆桥
					stat.close();
					conn.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return friends;
	}

	@Override
	public List<Member> search(String nameKey) {
		
		//返回列表的这种方法，先定义一个空的ArrayList集合
		ArrayList<Member> members = new ArrayList<Member>();
		
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
			String sql = "SELECT * from t_member where name like '%"+nameKey+"%'";
		
			System.out.println(sql);
			//5 推过去到mysql执行并返回结果 只不过有时候不接收结果
			ResultSet rs = stat.executeQuery(sql);
			
			//最多有一条 
			while(rs.next())
			{
				Member member = new Member();
				//Object强转成Integer ，对象不可以强转成基本数据类型int
				//jdk1.5之后 自动拆箱 ，Integer可以自动转成int，反过来自动装箱
//				member.setId((Integer)rs.getObject(0));		方式1
//				member.setId((Integer)rs.getObject("id"));	方式2
				member.setId((Integer)rs.getInt("id"));		//最常用方式
				member.setName(rs.getString("name"));
				member.setNickname(rs.getString("nickname"));
				member.setSignature(rs.getString("signature"));
				member.setPassword(rs.getString("password"));
				member.setHeader(rs.getString("header"));
				
				//以上的代码非常类似，没有什么技术含量，怎么能减少呢？如果只是写以上类似代码，码农
				//这里我们会教大家利用教高级的知识消除冗余代码，增强扩展性！！！合适是反射！！！
				//没有反射就没有框架，所有的后续框架 ssh ssm springboot 反射是基础
				
				//有一个好友，加入到ArrayList当中
				members.add(member);
			}
			
			//6 释放资源 过河拆桥
			stat.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return members;
	}

	@Override
	public List<Member> listMembersByCrowdId(int crowdId) {
		// TODO Auto-generated method stub
		return null;
	}

}
