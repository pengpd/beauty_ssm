package com.yingjun.ssm.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yingjun.ssm.common.CommonController;
import com.yingjun.ssm.common.Page;
import com.yingjun.ssm.entity.SysAuthority;
import com.yingjun.ssm.service.MenuService;

@Controller
@RequestMapping("/menu.htm")
public class MenuController extends CommonController{
	
	@Resource
	private MenuService menuService;
	
	@RequestMapping(params = "act=list")
	public void list(Page page,HttpServletResponse response) throws IOException{
		List<SysAuthority> list = menuService.list(page);
		writeJson(response, list);
	}

	@RequestMapping(params = "act=add")
	public void add(SysAuthority sysAuthority,HttpServletResponse response) throws IOException{
		menuService.add(sysAuthority);
		returnSucc(response);
	}
	
	
}
