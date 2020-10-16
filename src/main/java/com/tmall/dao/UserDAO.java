package com.tmall.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmall.pojo.User;

public interface UserDAO extends JpaRepository<User, Integer>{
	

}
