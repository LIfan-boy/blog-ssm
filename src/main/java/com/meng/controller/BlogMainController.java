package com.meng.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.meng.entity.Blog;
import com.meng.entity.BlogType;
import com.meng.entity.Blogger;
import com.meng.service.BlogService;
import com.meng.service.BlogTypeService;
import com.meng.service.BloggerService;
import com.meng.service.CommentService;
import com.meng.util.MD5Util;
import com.meng.util.ResponseUtil;

@Controller
@RequestMapping(value = "/")
public class BlogMainController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogTypeService blogTypeService;
	@Autowired
	private Producer kaptchaProducer;
	@Autowired
	private BloggerService bloggerService;
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = {"/", "/index.html"})
	public ModelAndView index(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 获取博主信心
		Blogger blogger = bloggerService.getBloggerData();
		// 获取前 num 篇博客
		List<Blog> blog = blogService.getBlogByMun(10);
		// 获取博客类型列表
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();

		session.setAttribute("blogger", blogger);
		session.setAttribute("blog", blog);
		session.setAttribute("blogTypeList", blogTypeList);
		
		return new ModelAndView("index");
		
	}
	
	@RequestMapping("/login")
	public ModelAndView login() {
		
		ModelAndView nav = new ModelAndView();
		nav.setViewName("login");
		return nav;
	}
	
	@RequestMapping("/articles/{num}.html")
	public ModelAndView art30(@PathVariable("num")Integer num, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		Blog blog = blogService.getById(num);
		
		Map<String, Object> commentMap = commentService.getCommentAndTotal(blog);
		
		session.setAttribute("blog", blog);
		session.setAttribute("comment", commentMap.get("data"));
		session.setAttribute("total", commentMap.get("total"));
		
		return new ModelAndView("html/html");
	}
	
	/**
	 * 验证码生成
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/captcha-image")
	public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 从 session 获取验证码的 code 值
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		System.out.println(code);
		
		// 设置清除浏览器的缓存
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/png");
		
		// 设置 session 
		String capText = kaptchaProducer.createText();
		System.out.println("设置session：" + capText);
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		
		// 输出验证码
		BufferedImage bi = kaptchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "png", out);
		out.flush();
		out.close();
		
		return null;
	}
	
	/**
	 * 验证码校验
	 * @param request
	 * @param response
	 * @param code_j
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/codeCheck")
	public String codeCheck(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("code")String code_j) throws Exception {
		
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		JSONObject json = new JSONObject();
		if (code_j.equals(code)) {
			json.put("ok", true);
		} else {
			json.put("ok", false);
		}
		ResponseUtil.write(response, json);
		return null;
	}
	
	/**
	 * Ajax 校验用户名的存在与否
	 * @param response
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/match")
	public String match(HttpServletResponse response, 
			@RequestParam("name")String name) throws Exception {
		
		JSONObject json = new JSONObject();
		if (bloggerService.getBloggerData().getUserName().equals(name)) {
			json.put("flag", true);
		} else {
			json.put("flag", false);
		}
		ResponseUtil.write(response, json);
		return null;
	}
	
	/**
	 * 登录校验
	 * @param request
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("/loginCheck")
	public ModelAndView loginCheck(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("userName")String userName, 
			@RequestParam("password")String passWord,
			@RequestParam(value= "boxName", required = false)String boxName) {
		
		HttpSession session = request.getSession();
		
		String password = MD5Util.getMD5String(passWord);
		
		System.out.println(userName + "------" +  password + "-------" + boxName);
		
		ModelAndView nav = new ModelAndView();
//		JSONObject json = new JSONObject();
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		try {
			
			if (boxName != null) {
				token.setRememberMe(true);
			} else {
				token.setRememberMe(false);
			}
			subject.login(token);
			nav.setViewName("admin/main");
			session.setAttribute("nike", bloggerService.getBloggerData().getNickName());
		} catch (Exception e) {
			e.printStackTrace();
			nav.setViewName("login");
		}
		return nav;
	}
	
	/**
	 * 退出登录状态
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView logout() {
		ModelAndView model = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		model.setViewName("login");
		return model;
	}

}
