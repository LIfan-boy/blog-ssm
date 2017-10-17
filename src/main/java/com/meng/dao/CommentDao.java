package com.meng.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meng.entity.Comment;

@Repository
public interface CommentDao {
	
	/**
	 * 添加评论
	 * @return
	 */
	int addComment(Comment comment);
	
	/**
	 * 查询对应博客内的评论
	 * @param blog
	 * @return
	 */
	List<Comment> getCommentData(Integer blogId);
	
	/**
	 * 获取评论的总显示条数
	 * @param blog
	 * @return
	 */
	int getTotal(Integer blogId);
	
	/**
	 * 删除评论根据相关博客 Id
	 * @param blogId
	 * @return
	 */
	int deleteCommentByBlogId(Integer blogId);
} 
