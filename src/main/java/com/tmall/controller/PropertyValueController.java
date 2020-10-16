package com.tmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.tmall.pojo.Product;
import com.tmall.pojo.PropertyValue;
import com.tmall.service.ProductService;
import com.tmall.service.PropertyValueService;

@RestController
public class PropertyValueController {
 
    @Autowired
    PropertyValueService propertyValueService;
 
    @Autowired
    ProductService productService;
 
    @GetMapping("/products/{pid}/propertyValues")
    public List<PropertyValue> list(@PathVariable("pid") int pid) {
        Product product = productService.get(pid);
        propertyValueService.init(product);
        List<PropertyValue> propertyValues = propertyValueService.list(product);
        return propertyValues;
    }
 
    @PutMapping("/propertyValues")
    public Object update(@RequestBody PropertyValue bean) {
        propertyValueService.update(bean);
        return bean;
 
    }
}