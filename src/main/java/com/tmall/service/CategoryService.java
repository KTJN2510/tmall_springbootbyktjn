package com.tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tmall.dao.CategoryDAO;
import com.tmall.pojo.Category;
import com.tmall.util.Page4Navigator;

@Service
@CacheConfig(cacheNames="categories")
public class CategoryService {
	
	@Autowired
	CategoryDAO categorydao;
	@Cacheable(key="'categories-page-'+#p0+ '-' + #p1")
	public Page4Navigator<Category> list(int start,int size,int navigatePages){
		Sort sort=new Sort(Sort.Direction.DESC,"id");
		Pageable pageable=PageRequest.of(start, size, sort);
		Page<Category> pageFROMJPA=categorydao.findAll(pageable);
		return new Page4Navigator<>(pageFROMJPA, navigatePages);
	}
	
	public void add(Category c) {
        categorydao.save(c);
    }
	
	public void delete(int id) {
		categorydao.deleteById(id);
	}
	
	public Category get(int id) {
		return categorydao.getOne(id);
	}
	
	public void update(Category c) {
		categorydao.save(c);
	}
}
