package com.meng.dao;

import org.springframework.stereotype.Repository;

import com.meng.entity.Visitor;

@Repository
public interface VisitorDao {
	
	Visitor getById(int id);
	
}
