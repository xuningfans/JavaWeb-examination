package com.netease.course.web.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netease.course.meta.Person;

/**
 * @author 公猴脖子男
 */
public class WebUtil {

	/**
	 * JSON转换器
	 */
	private static final ObjectMapper objectMapper = new ObjectMapper();

	/**************************************************
	 * 从Cookie中验证用户权限
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 ***************************************************/
	public static Person getPersonByCookie(HttpServletRequest request) {
		// 从Cookie中验证用户名密码
		Person cookiePerson = null;
		// 将Cookie转换成Bean
		cookiePerson = jsonCookie2Bean(request.getCookies(), Person.class);
		if (cookiePerson != null) {
			if (!(StringUtils.isBlank(cookiePerson.getUserName()) && StringUtils.isBlank(cookiePerson.getPassword()))) {
				return cookiePerson;
			}
		}
		return null;
	}

	/**************************************************
	 * 从Session中验证用户权限
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 ***************************************************/
	public static Person getPersonBySession(HttpSession session) {
		// 从Session中验证用户名密码
		Person loginPerson = null;
		loginPerson = (Person) session.getAttribute("user");
		if (loginPerson != null) {
			return loginPerson;
		}
		return null;
	}

	/************************************************************
	 * 为客户端设置Cookie，此处并未加密Cookie，实际情况按需加密
	 * 
	 * @param response
	 * @param Person
	 * @throws JsonProcessingException
	 * @throws UnsupportedEncodingException
	 ************************************************************/
	public static <T> void setCookie(HttpServletResponse response, T obj, Integer age) {

		StringWriter stringWriter = new StringWriter();
		try {
			// 将对象写进流
			String cookieName = obj.getClass().getSimpleName();
			objectMapper.writeValue(stringWriter, obj);
			// 将流中的数据放进新建的Cookie
			Cookie cookie = new Cookie(cookieName, stringWriter.toString());

			// 设置Cookie有效期及作用域
			cookie.setMaxAge(age);
			cookie.setPath("/");

			// 将设置好的Cookie添加至response
			response.addCookie(cookie);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (stringWriter != null)
				try {
					stringWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	/***********************************************************
	 * 将Cookie中的json字符串转化成对象返回
	 * 
	 * @param cookies
	 * @param clazz
	 * @return
	 ***********************************************************/
	public static <T> T jsonCookie2Bean(Cookie[] cookies, Class<T> clazz) {
		if (cookies != null && cookies.length > 0) {

			String cookieName = clazz.getSimpleName();
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {

					// 将获取的Cookie字符串转换成对象
					try {
						T obj = objectMapper.readValue(cookie.getValue(), clazz);
						return obj;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
}