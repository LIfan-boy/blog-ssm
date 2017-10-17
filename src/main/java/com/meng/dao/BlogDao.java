package com.meng.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meng.entity.Blog;

@Repository
public interface BlogDao {
	
	/**
	 * 分页查询博客
	 * @param map
	 * @return
	 */
	public List<Blog> listBlog(Map<String, Object> map);
	
	/**
	 * 获取总记录数
	 * @param title
	 * @return
	 */
	public long getTotal(@Param("title")String title);
	
	/**
	 * 根据博客类型的id查询该类型下的博客数量
	 * @param typeId
	 * @return
	 */
	public Integer getBlogByTypeId(Integer typeId);
	
	/**
	 * 删除博客
	 * @param id
	 * @return
	 */
	public Integer deleteBlogById(Integer id);
	
	/**
	 * 添加新的博客内容
	 * @param blog
	 * @return
	 */
	public Integer addBlog(Blog blog);
	
	/**
	 * 更新博客内容信息
	 * @param blog
	 * @return
	 */
	public Integer updateBlog(Blog blog);
	
	/**
	 * 通过id获取博客
	 * @param id
	 * @return
	 */
    public Blog getById(Integer id);
    
    /**
     * 查询指定数量的博客
     * @param num
     * @return
     */
    public List<Blog> getBlogByMun(@Param("num")Integer num);
    
    /**
     * 模糊查询
     * @param str
     * @return
     */
    public List<Blog> getBlogByFuzzy(@Param("str")String str);
  
    
}
