package com.meng.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.meng.util.ResponseUtil;

@Controller
@RequestMapping("admin/list")
public class FileLIstController {
	
	@RequestMapping("/image")
	public ModelAndView ListImage(HttpServletRequest request, HttpServletResponse response) {
		
		List<String> imageList = new ArrayList<>();
		// 获取 web 根路径
		//String filePath = System.getProperty("tansungWeb.root");
		String filePath = request.getSession().getServletContext().getRealPath("/");
		// 获取图片路径
		String path = filePath + "upload/article/image";
		System.out.println(path);
		// 根据路径获取指定的文件
		File file = new File(path);
		// 获取文件列表
		File[] filelist = file.listFiles();
		// 取出每个文件
		for(File onlyfile: filelist) {
			// 如果是文件夹，遍历文件夹下的文件
			if (onlyfile.isDirectory()) {
				File filedb = new File(path + "/" + onlyfile.getName());
				File[] flielistdb = filedb.listFiles();
				for(File db : flielistdb) {
					System.out.println(db.getName());
					imageList.add("/upload/article/image/" + onlyfile.getName() + "/" + db.getName());
				}
			} else {
				// 获取到每个文件名
				String name = onlyfile.getName();
				// 将文件名放入到 imageList
				imageList.add("/upload/article/image/" + name);
				System.out.println(name);
			}
			
		}
		
		ModelAndView model = new ModelAndView();
		model.addObject("imageList", imageList);
		model.setViewName("html/list");
		return model;
	}
	
	/**
	 * 删除图片
	 * @param request
	 * @param response
	 * @param url
	 */
	@RequestMapping("/deleteImg")
	public void deleteImg(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("url")String url) {
		// 获取文件路径
		String filePath = request.getSession().getServletContext().getRealPath("/");
		// 查找指定索引
		int index = url.indexOf("blog/");
		// 截取路径
		String path = url.substring(index + 5, url.length());
		
		System.out.println(path);
		
		JSONObject json = new JSONObject();
		
		System.out.println(filePath + path);
		File file = new File(filePath + path);
		if (file.exists()) {
			file.delete();
			if (!file.exists()) {
				json.put("flag", true);
			} else {
				json.put("flag", false);
			}
		}
		try {
			ResponseUtil.write(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
