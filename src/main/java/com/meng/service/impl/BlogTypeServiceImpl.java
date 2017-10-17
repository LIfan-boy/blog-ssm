package com.meng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meng.dao.BlogTypeDao;
import com.meng.entity.BlogType;
import com.meng.entity.PageBean;
import com.meng.service.BlogTypeService;

@Service
public class BlogTypeServiceImpl implements BlogTypeService {
	
	@Resource
    private BlogTypeDao blogTypeDao;

    public PageBean<BlogType> listByPage(PageBean<BlogType> pageBean) {
        //查询分页结果
        pageBean.setResult(blogTypeDao.listByPage(pageBean.getStart(),pageBean.getEnd()));
        //查询记录总数
        pageBean.setTotal(blogTypeDao.getTotal());
        return pageBean;
    }
	
    public Long getTotal() {
        return blogTypeDao.getTotal();
    }

    public Integer addBlogType(BlogType blogType) {
        return blogTypeDao.addBlogType(blogType);
    }

    public Integer updateBlogType(BlogType blogType) {
        return blogTypeDao.updateBlogType(blogType);
    }

    public Integer deleteBlogType(Integer id) {
        return blogTypeDao.deleteBlogType(id);
    }

	@Override
	public List<BlogType> getBlogTypeData() {
		return blogTypeDao.getBlogTypeData();
	}
}
