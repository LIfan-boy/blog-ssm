package com.meng.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.meng.entity.Blog;
import com.meng.entity.Comment;
import com.meng.service.BlogService;
import com.meng.service.CommentService;
import com.meng.service.VisitorService;
import com.meng.util.ResponseUtil;

/**
 * 评论相关 Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private VisitorService visitorService;
	
	/**
	 * 插入评论
	 * @param request
	 * @param response
	 * @param content
	 * @param visitorId
	 * @param blogId
	 * @throws Exception
	 */
	@RequestMapping("/add/{visitorid}/{blogid}")
	public void addComment(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("content")String content,
			@PathVariable("visitorid")Integer visitorId,
			@PathVariable("blogid")Integer blogId) throws Exception {
		
		Comment comment = new Comment();
		comment.setCommentContent(content);
		comment.setBlogId(blogService.getById(blogId));
		comment.setVisitorId(visitorService.getVisitorById(visitorId));
		int result = commentService.addComment(comment, blogId);
		
		JSONObject json = new JSONObject();
		
		if (result > 0) {
			json.put("flag", true);
		} else {
			json.put("flag", false);
		}
		
		ResponseUtil.write(response, json);
	}
	
	/**
	 * 跟新点赞数
	 * @param request
	 * @param response
	 * @param blogid
	 * @param praiseNum
	 * @throws Exception
	 */
	@RequestMapping("/updatepraise/{blogId}")
	public void updateClickPraise(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("blogId")Integer blogid,
			@RequestParam("praise")Integer praiseNum) throws Exception {
		
		Blog blog = blogService.getById(blogid);
		int result = blogService.updateClickPraise(blog, praiseNum);
		
		JSONObject json = new JSONObject();
		
		if (result > 0) {
			json.put("flag", true);
		} else {
			json.put("flag", false);
		}
		
		ResponseUtil.write(response, json);
		
	}
	
}
