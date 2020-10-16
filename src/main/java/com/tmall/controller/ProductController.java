package com.tmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmall.pojo.Product;
import com.tmall.service.ProductService;
import com.tmall.util.Page4Navigator;

@RestController
public class ProductController {

	@Autowired ProductService productservice;
	
	@GetMapping("/categories/{cid}/products")
	public Page<Product> list(@PathVariable(value="cid") int cid,
										@RequestParam(value="start",defaultValue="0") int start,
										@RequestParam(value="size",defaultValue="5") int size)throws Exception{
		start = start<0?0:start;
		Page<Product> page=productservice.list(start, size,  cid);
		return page;
	}
	
	@PostMapping("/products")
	public Object add(@RequestBody Product bean) {
		productservice.add(bean);
		return bean;
	}
	
	@DeleteMapping("/products/{id}")
	public String delete(@PathVariable(value="id") int id) {
		productservice.delete(id);
		return null;
	}
	
	@GetMapping("/products/{id}")
	public Product get(@PathVariable(value="id") int id) {
		Product bean=productservice.get(id);
		return bean;
	}
	
	@PutMapping("/products/{id}")
	public Object update(@RequestBody Product bean) {
		productservice.update(bean);
		return bean;
	}
}
