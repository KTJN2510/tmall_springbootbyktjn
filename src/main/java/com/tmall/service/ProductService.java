package com.tmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tmall.dao.CategoryDAO;
import com.tmall.dao.ProductDAO;
import com.tmall.pojo.Category;
import com.tmall.pojo.Product;
import com.tmall.pojo.ProductImage;
import com.tmall.util.Page4Navigator;

@Service
public class ProductService {

	@Autowired CategoryDAO categorydao;
	@Autowired ProductDAO productdao;
	@Autowired ProductImageService productimageservice;
	
	public void add(Product bean) {
		productdao.save(bean);
	}
	
	public void delete(int id) {
		productdao.deleteById(id);
	}
	
	public Product get(int id) {
		return productdao.getOne(id);
	}
	
	public void update(Product bean) {
		productdao.save(bean);
	}
	
	public Page<Product> list(int start,int size,int cid){
		Sort sort=new Sort(Sort.Direction.DESC,"id");
		Pageable pageable=PageRequest.of(start, size, sort);
		Category c=categorydao.getOne(cid);
		Page<Product> page=productdao.findByCategory(c, pageable);
		productimageservice.setFirstProductImages(page.getContent());
		return  page;
	}
}
