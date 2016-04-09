package com.netease.course.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netease.course.meta.Person;
import com.netease.course.meta.Trx;
import com.netease.course.service.TrxService;
import com.netease.course.web.utils.ResponseJson;

/**
 * 用户订单相关Controller
 * 
 * @author 公猴脖子男
 */
@Controller
public class TrxController extends BaseController {

	@Autowired
	private TrxService trxService;

	@RequestMapping(value = "/font/api/buy", method = RequestMethod.POST)
	public ModelMap buy(String id, HttpSession session, ModelMap model) {
		try {
			Person user = (Person) session.getAttribute("user");
			if (user != null && user.getId() != null) {
				int i = trxService.buy(id, user);
				if (i > 0) {
					// 数据库插入成功，购买成功
					ResponseJson.responseSucess(model, "购买成功！");
				} else {
					// 数据库未更新数据，购买失败
					ResponseJson.responseFail(model, "购买失败！");
				}
			} else {
				// 用户未登录，购买失败
				ResponseJson.responseFail(model, "请登录！");
			}
		} catch (Exception e) {
			// 数据库异常
			ResponseJson.responseFail(model, "购买失败！");
			e.printStackTrace();
		}
		return model;
	}

	/*******************************************************
	 * 用户账目展示页
	 * 
	 * @param model
	 * @return
	 *******************************************************/
	@RequestMapping(value = "/font/account", method = RequestMethod.GET)
	public String account(HttpSession session, ModelMap model) {
		// Session中取出当前用户信息
		Person user = (Person) session.getAttribute("user");
		if (user != null && user.getId() != null) {
			// 根据用户查询账目信息
			List<Trx> buyList = trxService.getBuyList(user);
			model.addAttribute("buyList", buyList);
		}
		return "account";
	}
}
