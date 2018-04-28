package com.yingjun.ssm.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opensymphony.module.sitemesh.Page;
import com.yingjun.ssm.common.CommonController;
import com.yingjun.ssm.service.MenuService;

@Controller
@RequestMapping("/menu.htm")
public class MenuController extends CommonController{
	
	@Resource
	private MenuService menuService;
	
	@RequestMapping(value = "/list")
	public void list(Page page){
		
	}

	
	public void add(){
		
	}
	
	
}
