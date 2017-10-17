package com.meng.service;

import java.util.List;

import com.meng.entity.BlogType;
import com.meng.entity.PageBean;

public interface BlogTypeService {
	
	//分页查询
    PageBean<BlogType> listByPage(PageBean<BlogType> pageBean);
    
    // 添加博客类别
    public Integer addBlogType(BlogType blogType);

    // 更新博客类别
    public Integer updateBlogType(BlogType blogType);

    // 删除博客类别
    public Integer deleteBlogType(Integer id);
    
    // 类型数据列表
	List<BlogType> getBlogTypeData();
}
