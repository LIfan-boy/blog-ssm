package com.meng.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meng.service.BloggerService;

/**
 * 跳转后台管理主页面测试类
 */
@Controller
@RequestMapping(value = "/admin")
public class MainController {
	
	@Autowired
	private BloggerService bloggerService;
	
    @RequestMapping(value = "/menu")
    public String toMenuPage(HttpSession session){
    	String nike = bloggerService.getBloggerData().getNickName();
    	session.setAttribute("nike", nike);
        return "admin/main";
    }
    
}
