package com.tmall.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmall.pojo.Property;
import com.tmall.service.PropertyService;
import com.tmall.util.Page4Navigator;

@RestController
public class PropertyController {

	@Autowired PropertyService propertyservice;
	
	@GetMapping("/categories/{cid}/properties")
	public Page4Navigator<Property> list(@PathVariable(value="cid") int cid,
										 @RequestParam(value="start",defaultValue="0") int start,
										 @RequestParam(value="size",defaultValue="5") int size) throws Exception{
		start = start<0?0:start;
		Page4Navigator<Property> page=propertyservice.list(start, size, 5, cid);
		return page;
	}
	
	@PostMapping("/properties")
	public Object add(@RequestBody Property bean) {
		propertyservice.add(bean);
		return bean;
	}
	
	@DeleteMapping("/properties/{id}")
	public String delete(@PathVariable(value="id") int id) {
		propertyservice.delete(id);
		return null;
	}
	
	@GetMapping("/properties/{id}")
	public Property get(@PathVariable(value="id") int id) {
		Property p=propertyservice.get(id);
		return p;
	}
	
	@PutMapping("/properties/{id}")
	public Object update(@RequestBody Property p) {
		propertyservice.update(p);
		return p;
	}
}
