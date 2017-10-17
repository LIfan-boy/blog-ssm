package com.meng.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meng.entity.BlogType;

@RunWith(SpringJUnit4ClassRunner.class) //使用spring测试
@ContextConfiguration(locations = {
		"classpath*:spring/spring-dao.xml",
		"classpath*:spring/spring-service.xml",
		"classpath*:spring/spring-mvc.xml",
		"classpath*:spring/spring-captcha.xml"}) //设置spring配置文件路径
public class BlogTypeDaoTest {
	
	@Resource
    private BlogTypeDao blogTypeDao;


    @Test
    public void addBlogType() throws Exception {
        BlogType blogType = new BlogType("Mysql",10);
        int result = blogTypeDao.addBlogType(blogType);
        System.out.println(result);
    }

    @Test
    public void deleteBlogType() throws Exception {
        int result = blogTypeDao.deleteBlogType(16);
        System.out.println(result);
    }

    @Test
    public void updateBlogType() throws Exception {
        // 先查询出要更新的记录然后修改
        BlogType blogType = blogTypeDao.getById(17);
        //然后提交更新
        blogType.setTypeName("更新mysql");
        //更新
        blogTypeDao.updateBlogType(blogType);
        //查询更新后的值 并且打印
        System.out.println(blogTypeDao.getById(17));

    }

    @Test
    public void getById() throws Exception {
        BlogType blogType = blogTypeDao.getById(17);
        System.out.println(blogType);
    }

    @Test
    public void  listByPage(){
        Integer page = 1;  //第一页
        Integer pageSize = 2;  //一页显示两条
        Integer start =(page-1)*pageSize;
        Integer end = page*pageSize;
        List<BlogType> blogTypeList = blogTypeDao.listByPage(start,end);
        for (BlogType b: blogTypeList) {
            System.out.println(b);
        }
    }

    @Test
    public void getTotal(){
        long total = blogTypeDao.getTotal();
        System.out.println(total);
    }
}
