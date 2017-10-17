package com.meng.service;

import java.util.Map;

import com.meng.entity.Blog;
import com.meng.entity.Comment;

public interface CommentService {
	/**
	 * 添加一条新的评论
	 * @param comment
	 * @return
	 */
	public int addComment(Comment comment, Integer blogId);
	
	/**
	 * 获取评论列表和评论总数
	 * @param blog
	 * @return
	 */
	public Map<String, Object> getCommentAndTotal(Blog blog);
	
}
