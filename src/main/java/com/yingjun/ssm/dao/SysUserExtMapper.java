package com.yingjun.ssm.dao;

import com.yingjun.ssm.entity.SysUser;

public interface SysUserExtMapper{
    SysUser selectByUserName(String username);
}