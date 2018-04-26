package com.yingjun.ssm.service;

import java.util.List;

import com.yingjun.ssm.entity.SysUser;

public interface UserService {

	List<SysUser> getUserList(int offset, int limit);

	SysUser getUser(String username);

	SysUser getUserById(Long userId);
	 
}
