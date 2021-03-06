package com.yingjun.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.yingjun.ssm.dao.SysUserExtMapper;
import com.yingjun.ssm.entity.SysUser;
import com.yingjun.ssm.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private SysUserExtMapper sysUserExtMapper;

	@Override
	public List<SysUser> getUserList(int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysUser getUser(String username) {
		return sysUserExtMapper.selectByUserName(username);
	}

	@Override
	public SysUser getUserById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
