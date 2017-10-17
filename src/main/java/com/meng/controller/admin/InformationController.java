package com.meng.controller.admin;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.meng.entity.Blogger;
import com.meng.service.BloggerService;
import com.meng.util.MD5Util;
import com.meng.util.ResponseUtil;

/**
 * 个人信息相关 Controller
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/admin/info")
public class InformationController {
	
	@Autowired
	private BloggerService bloggerService;
	
	@RequestMapping(value= "/openInfo")
	public ModelAndView openInfo(Model model) {
		
		ModelAndView nav = new ModelAndView();
		model.addAttribute("info", bloggerService.getBloggerData());
		nav.setViewName("admin/adminInfo");
		return nav;
		
	}
	
	/**
	 * 修改个人信息
	 * @param request
	 * @param response
	 * @param userName
	 * @param profile
	 * @param nickName
	 * @param sign
	 * @param psssword
	 * @param imageName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateInfo")
	public String updateInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "username", required = false)String userName,
			@RequestParam(value = "profile", required = false)String profile,
			@RequestParam(value = "nick", required = false)String nickName,
			@RequestParam(value = "sign", required = false)String sign,
			@RequestParam(value = "dbPass", required = false)String password,
			@RequestParam(value = "imagename", required = false)String imageName) throws Exception {
		
		Blogger blogger;
		
		if (password == null) {
			blogger = new Blogger(userName, profile, nickName, sign, imageName);
		} else {
			blogger = new Blogger(userName, MD5Util.getMD5String(password), profile, nickName, sign, imageName);
		}
		
		JSONObject json = new JSONObject();
		
		if (bloggerService.updateInfo(blogger) > 0) {
			json.put("flag", true);
		} else {
			json.put("flag", false);
		}
		
		ResponseUtil.write(response, json);
		
		return null;
		
	}
	
	/**
	 * Ajax 密码验证
	 * @param password
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkpass")
	public String ajaxCheckPassword(@RequestParam("yuanPass")String password, 
			HttpServletResponse response) throws Exception {
		String pass = MD5Util.getMD5String(password);
		JSONObject json = new JSONObject();
		if (pass.equals(bloggerService.getBloggerData().getPassword())) {
			json.put("flag", true);
		} else {
			json.put("flag", false);
		}
		ResponseUtil.write(response, json);
		return null;
	}
	
	/**
	 * 文件上传
	 * @param multipartFile
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/uploadfile")
	public String uploadfile(@RequestParam("fileName")MultipartFile multipartFile,
			HttpServletRequest request, HttpServletResponse response) {
		
		JSONObject result = new JSONObject();
		
		String path = request.getSession().getServletContext().getRealPath("/");
		
		if (!multipartFile.isEmpty()) {
			// 获取源文件名
			//String filename = multipartFile.getOriginalFilename();
			// 获取文件后缀，并转换成小写
			//String suffix = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
			
			String time = ((Long)System.currentTimeMillis()).toString();
			
			String saveName = "upload\\photo\\" + time + ".png";
			String name = "/upload/photo/" + time + ".png";
			// 上传文件操作
			try {
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), 
						new File(path + saveName));
				result.put("flag", true);
				result.put("filepath", name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
