package com.tmall.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.tmall.pojo.Category;
import com.tmall.service.CategoryService;
import com.tmall.util.ImageUtil;
import com.tmall.util.Page4Navigator;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryservice;
	
	@GetMapping("/categories")
	public Page4Navigator<Category> list(@RequestParam(value="start",defaultValue="0") int start,
										 @RequestParam(value="size",defaultValue="5") int size) throws Exception{
		start = start<0?0:start;
		Page4Navigator<Category> page=categoryservice.list(start, size, 5);
		return page;
	}
	
	@PostMapping("/categories")
	public Object add(Category c,HttpServletRequest req,MultipartFile image) throws IOException{
		categoryservice.add(c);
		saveOrUpdateImageFile(c, image, req);
		return c;
	}
	
	@DeleteMapping("/categories/{id}")
	public String delete(@PathVariable(value="id") int id,HttpServletRequest req) throws Exception{
		categoryservice.delete(id);
		File image=new File(req.getServletContext().getRealPath("img/category"));
		File file=new File(image,id+".jpg");
		file.delete();
		return null;
	}
	
	@GetMapping("/categories/{id}")
	public Category get(@PathVariable(value="id") int id) throws Exception{
		Category c=categoryservice.get(id);
		return c;
	}
	
	@PutMapping("/categories/{id}")
	public Object update(Category c,HttpServletRequest req,MultipartFile image) throws Exception{
		c.setName(req.getParameter("name"));
		categoryservice.update(c);
		if(image!=null) {
			saveOrUpdateImageFile(c, image, req);
		}
		return c;
		
	}
	public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request) throws IOException {
        //接受上传图片，并保存到img/category目录下
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        //文件名使用新增分类的id
        File file = new File(imageFolder,bean.getId()+".jpg");
        //如果目录不存在，就创建
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        //文件复制
        image.transferTo(file);
        //调用工具类方法，将文件强制转成jpg格式
        BufferedImage img = ImageUtil.change2jpg(file);
        //保存图片
        ImageIO.write(img, "jpg", file);
    }
}
