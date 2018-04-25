package com.yingjun.ssm.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yingjun.ssm.common.CommonController;

@Controller
@RequestMapping("/login.htm")
public class LoginController extends CommonController{
	
	@RequestMapping(params = "act=login", method = RequestMethod.POST)
	public void login(String username , String password , HttpServletResponse response) throws IOException{
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
		} catch (Exception e) {
			returnFail(response, e.getMessage());
		}
		
		returnSucc(response);
	}
	
	

}
