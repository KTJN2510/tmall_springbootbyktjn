package com.tmall.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tmall.pojo.Category;
import com.tmall.pojo.Product;
import com.tmall.pojo.ProductImage;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	Page<Product> findByCategory(Category c,Pageable pageable);

}
