package com.uek.junit;

import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uek.model.Friend;
import com.uek.model.Member;
import com.uek.service.impl.MemberServiceImpl;
import com.uek.service.inter.IMemberService;

public class TestMemberServiceImpl {
	
	private IMemberService memberService;
	
	@Before
	public void testInit()
	{
		memberService = new MemberServiceImpl();
	}
	
	@Test
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

		memberService.add(member);
	}
	
	@Test
	public void testLogin()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("输入名字");
		String name = sc.next();
		
		System.out.println("输入密码");
		String password = sc.next();
		

		Member member = memberService.login(name, password);
		
		System.out.println(member);
		System.out.println(member.toString());
	}
	
	@Test
	public void testLoadFriends()
	{
		List<Member> friends = memberService.loadFriends("zl");
		
		System.out.println(friends.size());

	
		
	}
	
	@Test
	public void testAddFriend()
	{
		Friend friend = new Friend();
		friend.setSelfId(1);
		friend.setFriendId(4);
		memberService.addFriend(friend);
		
		
	}
	
	@Test
	public void testSearch()
	{
		List<Member> friends = memberService.search("z");
		
		System.out.println(friends.size());

	
		
	}
	
	@After
	public void testDestory()
	{
		memberService = null;
	}
	
}
