package com.meng.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.meng.entity.Blog;
import com.meng.entity.BlogType;
import com.meng.entity.PageBean;
import com.meng.service.BlogService;
import com.meng.service.BlogTypeService;
import com.meng.util.ResponseUtil;

/**
 * @Description 管理员博客Controller层
 *
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogController {

	@Resource
	private BlogService blogService;
	@Resource
	private BlogTypeService blogTypeService;
	
	@RequestMapping("/openListBlog")
	public String blogList() {
		return "admin/blogManage";
	}
	
	@RequestMapping("/openWriteBlog")
	public String writeBlog(Model model) {
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();
		model.addAttribute(blogTypeList);
		return "admin/writeBlog";
	}
	
	@RequestMapping("/openModifyBlog")
	public ModelAndView modifyBlog(@PathParam("id")Integer id) {
		ModelAndView model = new ModelAndView();
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();
		Blog blogs = blogService.getById(id);
		model.addObject("blogTypeList", blogTypeList);
		model.addObject("blogs", blogs);
		model.setViewName("admin/modifyBlog");
		return model;
	}
	
	//后台分页查询博客信息
	@RequestMapping("/listBlog")
	public String listBlog(
			@RequestParam(value="page", required=false)String page,
			@RequestParam(value="rows", required=false)String rows,
			Blog s_blog,
			HttpServletResponse response) throws Exception {
		
		PageBean<Blog> pageBean = new PageBean<Blog>(Integer.parseInt(page), Integer.parseInt(rows));

		pageBean = blogService.listBlog(s_blog.getTitle(), pageBean);

		//创建json对象
		JSONObject result = new JSONObject();
		//设置json序列化日期格式
		JSON.DEFFAULT_DATE_FORMAT="yyyy-MM-dd";
		//禁止对象循环引用
		//使用默认日期格式化
		String jsonStr = JSONObject.toJSONString(pageBean.getResult(),
				SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteDateUseDateFormat);
		//得到json数组
		JSONArray array = JSON.parseArray(jsonStr);
		//把结果放入json
		result.put("rows", array);
		result.put("total", pageBean.getTotal());
		//返回
		ResponseUtil.write(response, result);
		return "admin/blogManage";
	}
	
	// 博客信息删除
    @RequestMapping(value = "/delete")
    public String deleteBlog(
            @RequestParam(value = "ids", required = false) String ids,
            HttpServletResponse response) throws Exception {
        //分割字符串得到id数组
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
             //其实在这里我们要判断该博客类别下面是否有博客 如果有就禁止删除博客类别 ，等我们完成博客对应的操作再来完善
            blogService.deleteBlogById(id);
        }
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
    
    // 添加和更新博客
    @RequestMapping("/save")
    public String saveBlog(HttpServletResponse response, HttpServletRequest request)
            throws Exception {
    	
    	Blog blog = new Blog();
    	blog.setId(Integer.valueOf(request.getParameter("id")));
    	blog.setTitle(request.getParameter("title"));
    	blog.setBlogType(new BlogType(Integer.valueOf(request.getParameter("blogTypeId")), null, null));
    	blog.setContent(request.getParameter("content"));
    	blog.setSummary(request.getParameter("summary"));
    	blog.setKeyWord(request.getParameter("keyWord"));
    	blog.setContentNoTag(request.getParameter("contentNoTag"));
    	
    	System.out.println(blog.toString());
    	
        int resultTotal = 0; // 接收返回结果记录数
        if (blog.getId() != null) { // 说明是第一次插入，增加新的内容
            resultTotal = blogService.updateBlog(blog);
        } else { // 有id表示修改
            resultTotal = blogService.addBlog(blog);
        }

        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("flag", true);
        } else {
            result.put("flag", false);
        }
        ResponseUtil.write(response, result);
        return null;
        
    }
    
}
