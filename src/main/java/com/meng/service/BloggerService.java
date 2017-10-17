package com.meng.service;

import com.meng.entity.Blogger;

/**
 * Created by xp on 2017/4/13.
 */

public interface BloggerService {
	
	public Blogger getBloggerData();
	
	public int updateInfo(Blogger blogger);
	
	public Blogger getBloggerByName(String username);
	
}
