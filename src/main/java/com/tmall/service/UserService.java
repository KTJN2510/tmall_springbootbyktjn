package com.tmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tmall.dao.UserDAO;
import com.tmall.pojo.User;
import com.tmall.util.Page4Navigator;

@Service
public class UserService {

	@Autowired UserDAO userdao;
	
	public Page4Navigator<User> list(int start,int size,int navigatePages){
		Sort sort=new Sort(Sort.Direction.DESC,"id");
		Pageable pageable=PageRequest.of(start, size, sort);
		Page<User> pageFROMJPA=userdao.findAll(pageable);
		return new Page4Navigator<>(pageFROMJPA, navigatePages);
	}
	
	public void add(User bean) {
		userdao.save(bean);
	}
	
	public void delete(int id) {
		userdao.deleteById(id);
	}
	
	public User get(int id) {
		return userdao.getOne(id);
	}
	
	public void update(User bean) {
		userdao.save(bean);
	}
}
