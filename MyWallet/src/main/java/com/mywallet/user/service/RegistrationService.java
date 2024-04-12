package com.mywallet.user.service;

import com.mywallet.user.entity.User;

public interface RegistrationService {
	
	public void hashUserPassword(User user); 
	
	public User createUser(User user);
	
	public boolean validateUserInfo(User user);
	
}
