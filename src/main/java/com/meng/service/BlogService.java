package com.meng.service;

import java.util.List;

import com.meng.entity.Blog;
import com.meng.entity.PageBean;

/**
 * @Description 博客Service接口
 * @author xp
 *
 */
public interface BlogService {

	// 分页查询博客
	public PageBean<Blog> listBlog(String title, PageBean<Blog> pageBean);

	// 根据博客类型的id查询该类型下的博客数量
	public Integer getBlogByTypeId(Integer typeId);

	// 删除博客根据id
	public Integer deleteBlogById(Integer id);

	// 添加
	public Integer addBlog(Blog blog);

	// 更新
	public Integer updateBlog(Blog blog);
	
	//通过id获取博客
    public Blog getById(Integer id);
    
    // 获取指定数目的结果
    public List<Blog> getBlogByMun(Integer num);
    
    // 模糊查询 根据 blog 的 title
    public List<Blog> getBlogByFuzzy(String str);
    
    /**
     * 更新博客点赞数
     * @param blog
     * @param praiseMun 点赞数
     * @return
     */
    public int updateClickPraise(Blog blog, Integer praiseMun);
}