package com.uek.service.impl;

import java.util.List;

import com.uek.dao.impl.CrowdMemberDaoImpl;
import com.uek.dao.inter.ICrowdMemberDao;
import com.uek.model.CrowdMember;
import com.uek.service.inter.ICrowdMemberService;

public class CrowdMemberServiceImpl implements ICrowdMemberService{

	ICrowdMemberDao crowdMemberDao = new CrowdMemberDaoImpl();
	
	@Override
	public void add(CrowdMember crowdMember) {
		
		crowdMemberDao.add(crowdMember);
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
