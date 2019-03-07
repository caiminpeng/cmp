package com.cmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmp.dao.RoleMapper;
import com.cmp.model.Role;
import com.cmp.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper rolemapper;

	@Override
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return rolemapper.getAll();
	}
	
	

}
