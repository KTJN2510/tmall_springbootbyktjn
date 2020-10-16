package com.tmall.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.dao.ProductDAO;
import com.tmall.dao.ProductImageDAO;
import com.tmall.pojo.Product;
import com.tmall.pojo.ProductImage;

@Service
public class ProductImageService {

	@Autowired ProductImageDAO productimagedao;
	@Autowired ProductService productservice;
	
	public static final String type_single = "single";
    public static final String type_detail = "detail";

  //前台根据pid以及type请求图片.这里我不知道为何要分开
    public List<ProductImage> listSingleProductImages(int pid) {
        Product product1=productservice.get(pid);
        return productimagedao.findByProductAndTypeOrderByIdDesc(product1, type_single);
    }

    public List<ProductImage> listDetailProductImages(int pid) {
        Product product1=productservice.get(pid);
        return productimagedao.findByProductAndTypeOrderByIdDesc(product1, type_detail);
    }
    
    public void add(ProductImage bean) {
        productimagedao.save(bean);
    }
    public void delete(int id) {
        productimagedao.deleteById(id);
    }
    public ProductImage get(int id) {
       return productimagedao.getOne(id);
    }
    
    public void setFirstProductImage(Product product) {
        List<ProductImage> singleImages = listSingleProductImages(product.getId());
        if (!singleImages.isEmpty()) {
            product.setFirstProductImage(singleImages.get(0));
        }
        else {
            product.setFirstProductImage(new ProductImage());
        }
    }
 
    public void setFirstProductImages(List<Product> products) {
        for (Product product : products) {
            setFirstProductImage(product);
        }
    }

}
