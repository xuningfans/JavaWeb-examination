package com.netease.course.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;

/**
 * 将状态码和message添加至model
 * 
 * @author 公猴脖子男
 */
public class ResponseJson {

	// 失败消息，及404状态码
	public static void responseFail(ModelMap model, String message) {
		model.addAttribute("code", HttpStatus.BAD_REQUEST.value());
		if (!StringUtils.isBlank(message)) {
			model.addAttribute("message", message);
		} else {
			model.addAttribute("message", Constant.FAIL);
		}
		model.addAttribute("result", false);
	}

	// 成功消息，及200状态码
	public static void responseSucess(ModelMap model, String message) {
		model.addAttribute("code", HttpStatus.OK.value());
		if (!StringUtils.isBlank(message)) {
			model.addAttribute("message", message);
		} else {
			model.addAttribute("message", Constant.SUCESS);
		}
		model.addAttribute("result", true);
	}

}
