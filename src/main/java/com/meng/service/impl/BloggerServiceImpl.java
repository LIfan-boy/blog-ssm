package com.meng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meng.dao.BloggerDao;
import com.meng.entity.Blogger;
import com.meng.service.BloggerService;

/**
 * Created by xp on 2017/4/13.
 */
@Service
public class BloggerServiceImpl implements BloggerService{
	
	@Autowired
	private BloggerDao bloggerDao;
	
	@Override
	public Blogger getBloggerData() {
		return bloggerDao.getBloggerData();
	}

	@Override
	public int updateInfo(Blogger blogger) {
		return bloggerDao.updateInfo(blogger);
	}

	@Override
	public Blogger getBloggerByName(String username) {
		return bloggerDao.getBloggerByUserName(username);
	}
	
}
	
