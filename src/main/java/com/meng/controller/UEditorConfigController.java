package com.meng.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.ueditor.ActionEnter;

/**
 * Ueditor 的配置入口
 * @author Administrator
 *
 */
@Controller
public class UEditorConfigController {

	@RequestMapping("/editorConfig")
	public void config(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			request.setCharacterEncoding( "utf-8" );
			response.setHeader("Content-Type" , "text/html");
			
			String rootPath = request.getServletContext().getRealPath( "/" );
			
			out = response.getWriter();
			
			out.write( new ActionEnter( request, rootPath ).exec() );
			
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("UEditor 初始化失败！"); 
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
