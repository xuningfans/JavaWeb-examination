package com.netease.course.web.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Controller基类
 * 
 * @author 公猴脖子男
 */
public class BaseController {

	@Autowired
	protected ServletContext application;

}
