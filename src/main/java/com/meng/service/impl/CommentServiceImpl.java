package com.meng.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meng.dao.BlogDao;
import com.meng.dao.CommentDao;
import com.meng.entity.Blog;
import com.meng.entity.Comment;
import com.meng.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private BlogDao blogDao;
	
	/**
	 * 插入评论，并更新博客中的评论总数
	 */
	@Override
	public int addComment(Comment comment, Integer blogId) {
		// 插入评论操作成功
		if (commentDao.addComment(comment) > 0) {
			// 获取到评论总数
			Integer total = commentDao.getTotal(blogId);
			// 获取到相关博客
			Blog blog = blogDao.getById(blogId);
			// 向博客中设置评论数
			blog.setReplyHit(total);
			// 更新博客的评论数
			blogDao.updateBlog(blog);
			
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Map<String, Object> getCommentAndTotal(Blog blog) {
		
		Map<String, Object> map = new HashMap<>();
		// 将该博客相关评论信息放入 map
		map.put("data", commentDao.getCommentData(blog.getId()));
		// 将该博客的评论总数放入 map
		map.put("total", commentDao.getTotal(blog.getId()));
		
		return map;
	}
	
}
