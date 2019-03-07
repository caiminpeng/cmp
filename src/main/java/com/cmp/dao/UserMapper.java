package com.cmp.dao;

import java.util.List;

import com.cmp.model.User;

public interface UserMapper {
	List<User>queryAll();
	boolean add(User user);
	List<User> select(User user);
	User selectBycode(User user);
	boolean update(User user);
	boolean delete(User user);
}
