package com.yingjun.ssm.web;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yingjun.ssm.common.CommonController;
import com.yingjun.ssm.common.Page;
import com.yingjun.ssm.dto.BaseResult;
import com.yingjun.ssm.entity.SysAuthority;
import com.yingjun.ssm.service.MenuService;
import com.yingjun.ssm.util.TimeUtils;

@Controller
@RequestMapping("/menu.htm")
public class MenuController extends CommonController{
	
	@Resource
	private MenuService menuService;
	
	@RequestMapping(params = "act=list")
	@ResponseBody
	public BaseResult<Collection<?>> list(Page page,HttpServletResponse response) throws IOException{
		List<SysAuthority> list = menuService.list(page);
		return wrapperResult(list);
	}

	@RequestMapping(params = "act=add")
	public void add(SysAuthority sysAuthority,HttpServletResponse response) throws IOException{
		sysAuthority.setCreateTime(TimeUtils.getTime(System.currentTimeMillis(), TimeUtils.DATE_FORMAT_DATE_S));
		menuService.add(sysAuthority);
		returnSucc(response);
	}
	
	
}
