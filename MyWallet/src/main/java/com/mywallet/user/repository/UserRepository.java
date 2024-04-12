package com.mywallet.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mywallet.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	 
}
