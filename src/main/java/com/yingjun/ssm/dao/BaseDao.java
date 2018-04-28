package com.yingjun.ssm.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.opensymphony.module.sitemesh.Page;

@Repository
public class BaseDao<T,E> {
	
	@Resource
	private BaseMapper<T, E> baseMapper;
	
	
	public void list(Page page){
	}

}
