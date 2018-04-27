package com.yingjun.ssm.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yingjun.ssm.common.CommonController;

@Controller
@RequestMapping("/login.htm")
public class LoginController extends CommonController{
	
	
	@RequestMapping(params = "act=login", method = RequestMethod.POST)
	public void login(String username , String password , HttpServletResponse response , HttpServletRequest request) throws IOException{
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		String msg = "" ;
		boolean isFail = false;
		try {
			subject.login(token);
			request.getSession().setAttribute("user", subject.getPrincipal());
		} catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
            isFail=true;
		} catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
            isFail=true;
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
            isFail=true;
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
            isFail=true;
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
            isFail=true;
        } catch (UnknownAccountException e) {
            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
            isFail=true;
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
            isFail=true;
        } catch (Exception e) {
        	msg = "系统异常！" + e.getMessage();
            isFail=true;
		}
		if( isFail ){
			returnFail(response,msg);
		}else{
			returnSucc(response);
		}
		
	}
	
	@RequestMapping(params = "act=logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		Subject subject = SecurityUtils.getSubject();  
	    if (subject.isAuthenticated()) { 
	        subject.logout();
	        request.getSession().invalidate();
	    }  
	    return "redirect:/login.jsp";
	}
	
	

}
