package com.meng.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meng.entity.Blog;
import com.meng.entity.Comment;
import com.meng.entity.Visitor;

@RunWith(SpringJUnit4ClassRunner.class) //使用spring测试
@ContextConfiguration(locations = {"classpath*:spring/spring-service.xml", 
		"classpath*:spring/spring-dao.xml"}) //设置spring配置文件路径
public class CommentDaoTest {
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Test
	public void addComment() {
		
		Blog blog = blogDao.getById(30);
		Visitor visitor = new Visitor();
		visitor.setVisitorId(1);
		
		Comment comment = new Comment(visitor, new Date(), "第一条评论", 0, blog);
		
		int result = commentDao.addComment(comment);
		
		System.out.println(result);
		
	}
		
}
