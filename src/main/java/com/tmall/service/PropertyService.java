package com.tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tmall.dao.CategoryDAO;
import com.tmall.dao.PropertyDAO;
import com.tmall.pojo.Category;
import com.tmall.pojo.Property;
import com.tmall.util.Page4Navigator;

@Service
public class PropertyService {
	
	@Autowired PropertyDAO propertydao;
	
	@Autowired CategoryDAO categorydao;
	
	public void add(Property p) {
		propertydao.save(p);
	}
	
	public void delete(int id) {
		propertydao.deleteById(id);
	}
	
	public Property get(int id) {
		return propertydao.getOne(id);
	}
	
	public void update(Property p) {
		propertydao.save(p);
	}
	
	public Page4Navigator<Property> list(int start,int size,int navigatePages,int cid){
		Sort sort=new Sort(Sort.Direction.DESC,"id");
		Pageable pageable=PageRequest.of(start, size, sort);
		Category c=categorydao.getOne(cid);
		Page<Property> pageFROMJPA=propertydao.findByCategory(c, pageable);
		return new Page4Navigator<>(pageFROMJPA, navigatePages);
	}
	
	public List<Property> listByCategory(int cid){
        Category category = categorydao.getOne(cid);
        return propertydao.findByCategory(category);
    }
}
