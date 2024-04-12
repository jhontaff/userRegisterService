package com.mywallet.user.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mywallet.exception.ValidateUserException;
import com.mywallet.user.entity.User;
import com.mywallet.user.repository.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public boolean emailExist(String email) {
		List<User> users = this.userRepository.findAll();
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	public boolean passwordMatcher(String password, String confirmPassword){
		if (password.equals(confirmPassword)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public void hashUserPassword(User user) {
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		String hashedConfirmPassword = passwordEncoder.encode(user.getConfirmPassword());
		user.setConfirmPassword(hashedConfirmPassword);
	}
	
	
	public boolean validateUserInfo(User user) {
		if (this.emailExist(user.getEmail())){
			throw new ValidateUserException("El email " + user.getEmail() + " ya se encuentra registrado");
		} else if (!this.passwordMatcher(user.getPassword(), user.getConfirmPassword())){
			throw new ValidateUserException("Las contraseñas no coinciden");
		} else {
			return Boolean.TRUE;
		}
		
	}
	@Override
	public User createUser(User user){
		this.validateUserInfo(user);
		user.setRegistrationDate(LocalDateTime.now());
		return this.userRepository.save(user);
	}



}
