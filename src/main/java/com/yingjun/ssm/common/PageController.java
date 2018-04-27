package com.yingjun.ssm.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p> Title: 打开jsp页面公共方法 </p>
 * <p> Description:  </p>
 */
@Controller
public class PageController extends CommonController {

	@RequestMapping("/jsp.htm")
	protected void jsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder page = new StringBuilder("WEB-INF/page/");
		page.append(request.getParameter("page"));
		request.getRequestDispatcher(page.toString()).forward(request, response);
	}
	
	@RequestMapping("/btl.htm")
	protected ModelAndView btl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return new ModelAndView(request.getParameter("page"));
	}
	
	@RequestMapping("/index.htm")
	protected ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return new ModelAndView("index");
	}

}
