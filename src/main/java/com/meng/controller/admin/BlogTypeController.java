package com.meng.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meng.entity.BlogType;
import com.meng.entity.PageBean;
import com.meng.service.BlogTypeService;
import com.meng.util.ResponseUtil;

/**
 * @Description 博客分类控制器
 */
@Controller
@RequestMapping(value = "/admin/blogType")
public class BlogTypeController {
	
	@Resource
    private BlogTypeService blogTypeService;
	
	@RequestMapping("/openBlogTypeManage")
	public String BlogType() {
		return "admin/blogTypeManage";
	}
    
    // 分页查询博客类别
    @RequestMapping("/listBlogType")
    public String listBlogType1(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            HttpServletResponse response) throws Exception {
        //定义分页bean
        PageBean<BlogType> pageBean = new PageBean<BlogType>(Integer.parseInt(page)
                ,Integer.parseInt(rows));
        //拿到分页结果已经记录总数的pageBean
        pageBean = blogTypeService.listByPage(pageBean);
        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();
        //通过fastJson序列化list为jsonArray
        String jsonArray = JSON.toJSONString(pageBean.getResult());
        JSONArray array = JSONArray.parseArray(jsonArray);
        //将序列化结果放入json对象中
        result.put("rows", array);
        result.put("total", pageBean.getTotal());

        //使用自定义工具类向response中写入数据
        ResponseUtil.write(response, result);
        return null;
    }
    
    // 添加和更新博客类别
    @RequestMapping("/save")
    public String save(HttpServletResponse response,
    		@RequestParam(value = "orderNum") Integer orderNum, @RequestParam("typeName") String typeName,
    		@RequestParam(value = "id", required = false) Integer id)
            throws Exception {
    	
    	BlogType blogType = new BlogType(id, typeName, orderNum);
        int resultTotal = 0; // 接收返回结果记录数
        if (blogType.getId() == null) { // 说明是第一次插入
        	
            resultTotal = blogTypeService.addBlogType(blogType);
        } else { // 有id表示修改
        	
            resultTotal = blogTypeService.updateBlogType(blogType);
        }

        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    // 博客类别信息删除
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
                blogTypeService.deleteBlogType(id);
        }
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
    
}
