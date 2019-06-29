package com.uek.service.impl;

import java.util.List;

import com.uek.dao.impl.FriendDaoImpl;
import com.uek.dao.impl.MemberDaoImpl;
import com.uek.dao.inter.IFriendDao;
import com.uek.dao.inter.IMemberDao;
import com.uek.exception.ChatException;
import com.uek.model.Friend;
import com.uek.model.Member;
import com.uek.service.inter.IMemberService;

public class MemberServiceImpl implements IMemberService{

	//servie上一层调用dao下一层的服务，就通过持有下一层对象的实例，调用该实例的方法实现 
	IMemberDao memberDao = new MemberDaoImpl();
	IFriendDao friendDao = new FriendDaoImpl();
	
	@Override
	public void add(Member member) {
		
		memberDao.add(member);
		
		//业务逻辑层可能后续还有代码比如发送电子邮件到注册用户的邮箱
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
	
		return memberDao.load(id);
	}

	@Override
	public List<Member> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member loadByName(String name) {
		
		return memberDao.loadByName(name);
	}

	@Override
	public Member login(String name, String password) {
		
		Member member = memberDao.loadByName(name);
		
		if(member == null)
		{
			throw new ChatException("用户名不存在");//程序就中断了
		}
		
		if(!password.equals(member.getPassword()))
		{
			throw new ChatException("密码不正确!");//程序就中断了
		}
		
		return member;
	}

	@Override
	public List<Member> loadFriends(String name) {
		
		return memberDao.loadFriends(name);
	}

	@Override
	public List<Member> loadFriends(int selfId) {
		
		return memberDao.loadFriends(selfId);
	}

	@Override
	public List<Member> search(String nameKey) {
		
		return memberDao.search(nameKey);
	}

	/**
	 * 
	 * 交好友的业务方法，调用底层firend  add，但是需要双向 
	 */
	@Override
	public void addFriend(Friend friend) {
		
		friendDao.add(friend);
		
		Friend fri = new Friend();
		fri.setSelfId(friend.getFriendId());
		fri.setFriendId(friend.getSelfId());
		
		friendDao.add(fri);
		
	}

	@Override
	public List<Member> listMembersByCrowdId(int crowdId) {
		
		return memberDao.listMembersByCrowdId(crowdId);
	}

}
