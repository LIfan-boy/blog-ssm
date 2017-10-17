package com.meng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meng.dao.VisitorDao;
import com.meng.entity.Visitor;
import com.meng.service.VisitorService;

@Service
public class VisitorServiceImpl implements VisitorService {
	
	@Autowired
	private VisitorDao visitorDao;
	
	@Override
	public Visitor getVisitorById(int visitorId) {
		return visitorDao.getById(visitorId);
	}

}
