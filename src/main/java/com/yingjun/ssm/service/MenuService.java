package com.yingjun.ssm.service;


import java.util.List;

import com.yingjun.ssm.common.Page;
import com.yingjun.ssm.entity.SysAuthority;

public interface MenuService {
	
	public List<SysAuthority> list(Page page);

	public void add(SysAuthority sysAuthority);
}
