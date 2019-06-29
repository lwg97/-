package com.uek.service.impl;

import java.util.Date;
import java.util.List;

import com.uek.dao.impl.CrowdDaoImpl;
import com.uek.dao.impl.CrowdMemberDaoImpl;
import com.uek.dao.impl.FriendDaoImpl;
import com.uek.dao.inter.ICrowdDao;
import com.uek.dao.inter.ICrowdMemberDao;
import com.uek.dao.inter.IFriendDao;
import com.uek.model.Crowd;
import com.uek.model.CrowdMember;
import com.uek.service.inter.ICrowdService;

public class CrowdServiceImpl implements ICrowdService{

	//servie上一层调用dao下一层的服务，就通过持有下一层对象的实例，调用该实例的方法实现 
	ICrowdDao crowdDao = new CrowdDaoImpl();
	IFriendDao friendDao = new FriendDaoImpl();
	ICrowdMemberDao crowdMemberDao = new CrowdMemberDaoImpl();
	
	/* 
	 *  添加群的业务逻辑 1 添加群 2 把群主加入到群成员中去 
	 */
	@Override
	public void add(Crowd crowd) {
		//1 添加群
		crowdDao.add(crowd);
		
		//2 把群主加入到群成员中去 
		
		//必须再查一次数据库，否则crowd的id是0
		crowd = crowdDao.loadByName(crowd.getCrowdName());
		
		CrowdMember crowdMember = new CrowdMember();
		crowdMember.setCrowdId(crowd.getId());
		crowdMember.setMemberId(crowd.getCreaterId());//把群的创建人加入群成员，ID作为群成员id
		crowdMember.setJoinTime(new Date());
		
		crowdMemberDao.add(crowdMember);
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
	
		return crowdDao.load(id);
	}

	@Override
	public List<Crowd> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Crowd loadByName(String name) {
		
		return crowdDao.loadByName(name);
	}

	

	@Override
	public List<Crowd> search(String nameKey) {
		
		return crowdDao.search(nameKey);
	}

	@Override
	public List<Crowd> search(int memberId) {
		
		return crowdDao.search(memberId);
	}

	
}
