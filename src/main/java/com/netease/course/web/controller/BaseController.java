package com.netease.course.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller基类
 * 
 * @author 公猴脖子男
 */
public class BaseController {

	@Autowired
	protected ServletContext application;

	/**
	 * 默认错误处理
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { Exception.class })
	public String handlerException(Exception e, HttpServletRequest request) {
		// 记录日志
		e.printStackTrace();
		System.out.println(e.getMessage());
		request.setAttribute("message", "内部错误!");
		return "error";
	}

}
