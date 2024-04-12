package com.mywallet.user.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywallet.user.entity.User;
import com.mywallet.user.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
		List<User> users = this.userRepository.findAll();
		return users;
	}

	@Override
	public User getUser(Integer id) {
		User user = this.userRepository.findById(id).orElse(null);
		return user;
	}

	@Override
	public User addUser(User user) {
		User newUser = this.userRepository.save(user);
		newUser.setRegistrationDate(LocalDateTime.now());
		return newUser;
	}

	@Override
	public void deleteUser(Integer id) {
		this.userRepository.deleteById(id);
	}

}
