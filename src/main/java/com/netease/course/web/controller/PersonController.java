package com.netease.course.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.course.meta.Person;
import com.netease.course.service.PersonService;
import com.netease.course.web.utils.ResponseJson;
import com.netease.course.web.utils.WebUtil;

/**
 * 用户相关Controller
 * 
 * @author 公猴脖子男
 */
@Controller
public class PersonController extends BaseController {

	@Autowired
	private PersonService personService;

	/*******************************************************
	 * 用户登录请求地址
	 * 
	 * @param model
	 * @return
	 *******************************************************/
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request, HttpSession session, ModelMap model) {
		return "login";
	}

	/*******************************************************
	 * 用户登录提交地址接口
	 * 
	 * @param model
	 * @return
	 *******************************************************/
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap Login(String userName, String password, HttpSession session, HttpServletResponse response,
			ModelMap model) {

		Person person = personService.login(userName, password);
		if (person != null) {

			// 默认Cookie时间一小时
			WebUtil.setCookie(response, person, 60 * 60);
			session.setAttribute("user", person);

			ResponseJson.responseSucess(model, "登录成功！");
		} else {
			ResponseJson.responseFail(model, "登录失败！");
		}

		return model;
	}

	/*******************************************************
	 * 用户注销请求地址
	 * 
	 * @param model
	 * @return
	 *******************************************************/
	@RequestMapping(value = "/font/logout", method = RequestMethod.GET)
	public String logout(HttpServletResponse response, HttpSession session) {

		// 注销，清空Cookies，从Session中移除用户详情
		session.removeAttribute("user");
		// session.invalidate();
		Cookie cookie = new Cookie("Person", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "login";
	}
}
