package com.cmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmp.dao.UserMapper;
import com.cmp.model.User;
import com.cmp.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	 @Autowired
    private UserMapper userMapper;
    

	@Override
	public List<User> queryAll() {
		// TODO Auto-generated method stub	
		return userMapper.queryAll();
	}


	@Override
	public boolean add(User user) {
		// TODO Auto-generated method stub
		return userMapper.add(user);
	}


	@Override
	public List<User> select(User user) {
		// TODO Auto-generated method stub
		return userMapper.select(user);
	}


	@Override
	public User selectBycode(User user) {
		// TODO Auto-generated method stub
		return userMapper.selectBycode(user);
	}


	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return userMapper.update(user);
	}


	@Override
	public boolean delete(User user) {
		// TODO Auto-generated method stub
		return userMapper.delete(user);
	}

}
