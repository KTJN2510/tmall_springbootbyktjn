package com.tmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AdminPageController {
	
	@GetMapping(value="/admin")
	public String admin() {
		return "redirect:admin_category_list";
	}
	
	 
	@GetMapping(value="/admin_category_list")
	public String listcategory() {
		return "admin/listCategory";
	}
	
	@GetMapping(value="/admin_category_edit")
	public String editcategory() {
		return "admin/editCategory";
	}
	
	@GetMapping(value="/admin_property_list")
	public String listproperty() {
		return "admin/listProperty";
	}
	
	@GetMapping(value="/admin_property_edit")
	public String editproperty() {
		return "admin/editProperty";
	}
	
	@GetMapping(value="/admin_product_list")
	public String listproduct() {
		return "admin/listProduct";
	}
	
	@GetMapping(value="/admin_product_edit")
	public String editproduct() {
		return "admin/editProduct";
	}
	
	@GetMapping(value="/admin_productImage_list")
	public String listproductimage() {
		return "admin/listProductImage";
	}
	
	@GetMapping(value="/admin_propertyValue_edit")
	public String editpropertyvalue() {
		return "admin/editPropertyValue";
	}
	
	@GetMapping(value="/admin_user_list")
	public String listuser() {
		return "admin/listUser";
	}
}
