package com.uek.junit;

import java.util.Scanner;

import org.junit.Test;

import com.uek.dao.impl.MemberDaoImpl;
import com.uek.dao.inter.IMemberDao;
import com.uek.model.Member;

public class TestMemberDaoImpl {
	
	@Test//Test标注是第三方提供的，jdk没有，所以需要引入一个jar包
	public void testAdd()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("输入名字");
		String name = sc.next();
		
		System.out.println("输入昵称");
		String nickname = sc.next();
		
		System.out.println("输入签名");
		String signature = sc.next();
		
		System.out.println("输入密码");
		String password = sc.next();
		
		System.out.println("输入头像");
		String header = sc.next();
		
		Member member = new Member(name , nickname , signature , password , header);

		IMemberDao memberDao = new MemberDaoImpl();
		
		memberDao.add(member);
		
		
	}
	

}
