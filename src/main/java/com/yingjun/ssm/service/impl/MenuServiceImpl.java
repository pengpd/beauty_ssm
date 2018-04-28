package com.yingjun.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yingjun.ssm.common.Page;
import com.yingjun.ssm.dao.SysAuthorityMapper;
import com.yingjun.ssm.entity.SysAuthority;
import com.yingjun.ssm.entity.SysAuthorityExample;
import com.yingjun.ssm.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Resource
	private SysAuthorityMapper sysAuthorityMapper;
	
	@Override
	public List<SysAuthority> list(Page page) {
		SysAuthorityExample example = new SysAuthorityExample();
		example.setLimit(page.getLimit());
		example.setOffset(page.getStart());
		return sysAuthorityMapper.selectByExample(example);
	}

	@Override
	public void add(SysAuthority sysAuthority) {
		sysAuthorityMapper.insert(sysAuthority);
	}

}
