package com.meng.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //使用spring测试
@ContextConfiguration(locations = {"classpath*:spring/spring-dao.xml",
		"classpath*:spring/spring-service.xml"}) //设置spring配置文件路径
public class BlogDaoTest {
	
	@Autowired
	private BlogDao blogdao;
	
	@Test
	public void getBlogByMunTest() {
		
		Integer num = 5;
		System.out.println(blogdao.getBlogByMun(num).toString());
		
	}
	
	@Test
	public void getBlogByFuzzyTest() {
		String str = new String("A");
		System.out.println(blogdao.getBlogByFuzzy(str));
	}
}
