package com.meng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meng.dao.BlogDao;
import com.meng.dao.CommentDao;
import com.meng.entity.Blog;
import com.meng.entity.PageBean;
import com.meng.service.BlogService;

/**
* @Description 博客Service实现类
* @author xp
*
*/
@Service
public class BlogServiceImpl implements BlogService {

	@Resource
	private BlogDao blogDao;
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public PageBean<Blog> listBlog(String title, PageBean<Blog> pageBean) {
		Map<String,Object> map = new HashMap<String,Object>();
		//设置查询条件
		map.put("title",title);
		map.put("start",pageBean.getStart());
		map.put("end",pageBean.getEnd());
		//把分页结果放入pageBean
		pageBean.setResult(blogDao.listBlog(map));
		//总记录放入pageBean
		pageBean.setTotal(blogDao.getTotal(title));
		return pageBean;
	}
	
	@Override
	public Integer getBlogByTypeId(Integer typeId) {
		return blogDao.getBlogByTypeId(typeId);
	}

	/**
	 * 删除博客及该博客相关评论
	 */
	@Override
	public Integer deleteBlogById(Integer id) {
		if ((commentDao.deleteCommentByBlogId(id) > 0) || (blogDao.deleteBlogById(id) > 0)) {
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public Integer addBlog(Blog blog) {
		return blogDao.addBlog(blog);
	}

	@Override
	public Integer updateBlog(Blog blog) {
		return blogDao.updateBlog(blog);
	}

	@Override
	public Blog getById(Integer id) {
		return blogDao.getById(id);
	}

	@Override
	public List<Blog> getBlogByMun(Integer num) {
		return blogDao.getBlogByMun(num);
	}

	@Override
	public List<Blog> getBlogByFuzzy(String str) {
		return blogDao.getBlogByFuzzy(str);
	}

	@Override
	public int updateClickPraise(Blog blog, Integer praiseNum) {
		// 设置更新数值
		blog.setClickPraise(praiseNum + 1); 
		return blogDao.updateBlog(blog);
	}

}
