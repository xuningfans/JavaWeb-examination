package com.netease.course.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.netease.course.meta.Person;
import com.netease.course.service.PersonService;
import com.netease.course.web.utils.WebUtil;

/**
 * 用户权限拦截器，进行权限验证，对用户访问资源控制 /font/* 普通用户可以访问 /back/* 管理员可以访问 / 未登录可以访问
 * 
 * @author 公猴脖子男
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private PersonService personService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 获取Session
		HttpSession session = request.getSession();

		// 获取拦截地址
		// 是否拦截 http://localhost:8080/back/delete
		String requestURI = request.getRequestURI();

		// 调用WebUtil中getUserBySession方法验证登录信息
		Person user = null;
		user = WebUtil.getPersonBySession(session);
		if (user == null) {

			// Session中没有登录信息，从Cookies中取
			Person cookieUser = WebUtil.getPersonByCookie(request);

			// 通过数据库验证Cookie中用户信息是否合法
			if (cookieUser != null) {
				if (!(StringUtils.isBlank(cookieUser.getUserName()) && StringUtils.isBlank(cookieUser.getPassword()))) {
					user = personService.login(cookieUser.getUserName(), cookieUser.getPassword());
					if (user != null) {
						// 用户名密码合法
						session.setAttribute("user", user);
					}
				}
			}
		}

		if (user == null) {
			// 用户未登录访问无需登录页面 ，直接放行
			if (!(requestURI.startsWith("/font/") || requestURI.startsWith("/back/"))) {
				
				// 访问主页，进行特殊处理，不允许有查询参数  ?type=1
				if (requestURI.equals("/") && request.getQueryString()!=null){
					response.sendRedirect("/login");
					return false;
				} else {
					return true;
				}
			}
		}

		// 用户已登录
		if (user != null) {

			// 卖家，允许访问所有页面
			if (user.getUsertype() == 1) {
				return true;
			}

			// 买家，允许访问非后台页面
			if (user.getUsertype() == 0) {
				if (!requestURI.startsWith("/back/")) {
					return true;
				}
			}
		}

		// 用户未登录且无权访问该页面，重定向至登录页
		if (user == null) {
			response.sendRedirect("/login");
			return false;
		} else {
			// 用户已登录但无权访问该页面，重定向至主页
			response.sendRedirect("/");
			return false;
		}

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
