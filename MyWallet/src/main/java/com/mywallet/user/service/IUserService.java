package com.mywallet.user.service;

import java.util.List;

import com.mywallet.user.entity.User;

public interface IUserService {

	public List<User> getAllUsers();
	
	public User getUser(Integer id);
	
	public User addUser(User user);
	
	public void deleteUser(Integer id);
}
