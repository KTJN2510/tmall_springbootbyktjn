package com.tmall.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmall.pojo.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer>{

}
