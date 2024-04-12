package com.mywallet.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mywallet.exception.NotFoundException;
import com.mywallet.user.entity.User;
import com.mywallet.user.service.IUserService;
import com.mywallet.user.service.RegistrationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@GetMapping("/get-users")
	public List<User> getUsers(){
		List<User> users = this.userService.getAllUsers();
		return users;
	}
	
	@GetMapping("/get-user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id){
		User user = this.userService.getUser(id);
		if (user != null) {
			return ResponseEntity.ok(user);
		} 
		else {
			throw new NotFoundException("No se encontró usuario con id: " + id); 
		}
	}
	
	
	@PostMapping("/create-user")
	public ResponseEntity<User> createNewUser(@Valid @RequestBody User user) {
		System.out.println(this.registrationService.validateUserInfo(user));
		User newUser = this.registrationService.createUser(user);
		this.registrationService.hashUserPassword(newUser);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);		
	}
	
	
	@PutMapping("/edit-user/{id}")
	public ResponseEntity<Map<String, User>> editUser(@PathVariable Integer id){
		User user = this.userService.getUser(id);
		HashMap<String, User> response = new HashMap<>();
		user.setEmail(user.getEmail());
		user.setPassword(user.getPassword());
		user.setPhone(user.getPhone());
		user.setUsername(user.getUsername());
		User editedUser = this.userService.addUser(user);
		response.put("Usuario creado: ", editedUser);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<HashMap<String, Boolean>> deleteUser(@PathVariable Integer id){
		User user = this.userService.getUser(id);
		if (user != null) {
			this.userService.deleteUser(id);
			HashMap<String, Boolean> deletedUser = new HashMap<>();
			deletedUser.put("Eliminado", Boolean.TRUE);
			return ResponseEntity.ok(deletedUser);
		}
		else {
			throw new NotFoundException("No se encontró usuario con id: " + id);
		}
	}
	
	
}
