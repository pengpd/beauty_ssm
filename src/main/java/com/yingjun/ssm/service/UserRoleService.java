package com.yingjun.ssm.service;

import java.util.List;

import com.yingjun.ssm.entity.SysRole;

public interface UserRoleService {

	List<SysRole> findRoles(Long userId);

}
