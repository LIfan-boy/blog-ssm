package com.meng.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meng.entity.Blogger;

@Repository // 注册为持久层的bean
public interface BloggerDao {
	/**
	 * 查询博主信息
	 * 
	 * @return
	 */
	Blogger getBloggerData();
	
	int updateInfo(Blogger blogger);
	
	Blogger getBloggerByUserName(@Param("username")String  username);
}
