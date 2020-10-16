package com.tmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmall.pojo.Category;
import com.tmall.pojo.User;
import com.tmall.service.UserService;
import com.tmall.util.Page4Navigator;

@RestController
public class UserController {

	@Autowired UserService userservice;
	
	@GetMapping("/users")
	public Page4Navigator<User> listuser(@RequestParam(value="start",defaultValue="0") int start,
										 @RequestParam(value="size",defaultValue="5") int size){
		start = start<0?0:start;
		Page4Navigator<User> page=userservice.list(start, size, 5);
		return page;
	}
}
