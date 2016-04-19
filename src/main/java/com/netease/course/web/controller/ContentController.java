package com.netease.course.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.course.entity.Product;
import com.netease.course.meta.Content;
import com.netease.course.service.ContentService;
import com.netease.course.service.TrxService;
import com.netease.course.web.utils.ResponseJson;

/**
 * 商品Controller
 * 
 * @author 公猴脖子男
 */
@Controller
public class ContentController extends BaseController {

	@Autowired
	private ContentService contentService;

	@Autowired
	private TrxService trxService;

	/*******************************************************
	 * 主页
	 * 
	 * @param model
	 * @return
	 *******************************************************/
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String index(@RequestParam(value = "type", required = false) Integer type, HttpSession session,
			ModelMap model) {
		List<Product> productList = contentService.getProductList(type);
		model.addAttribute("productList", productList);
		return "index";
	}

	/*******************************************************
	 * 单品展示页
	 * 
	 * @param model
	 * @return
	 *******************************************************/
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String toShow(Integer id, ModelMap model) {
		if (id != null) {
			Product product = contentService.getProductById(id);
			model.addAttribute("product", product);
		}
		return "show";
	}

	/*******************************************************
	 * 产品修改请求地址
	 * 
	 * @param model
	 * @return
	 *******************************************************/
	@RequestMapping(value = "/back/edit", method = RequestMethod.GET)
	public String toEdit(Integer id, ModelMap model) {
		if (id != null) {
			// 查询条件
			Content query = new Content();
			query.setId(id);
			Content product = contentService.select(query);
			model.addAttribute("product", product);
		}
		return "edit";
	}

	/*******************************************************
	 * 产品修改提交地址
	 * 
	 * @param model
	 * @return
	 *******************************************************/
	@RequestMapping(value = "/back/editSubmit", method = RequestMethod.POST)
	public String editSubmit(String id, String title, String image, String detail, String price, String summary,
			ModelMap model) {
		try {
			Double tmpPrice = null;
			Integer tmpId = null;
			// 验证数据，不通过直接返回
			if (StringUtils.isBlank(id)) {
				return "editSubmit";
			} else {
				tmpId = Integer.parseInt(id);
			}
			if (StringUtils.isBlank(title)) {
				return "editSubmit";
			}
			if (StringUtils.isBlank(image)) {
				return "editSubmit";
			}
			if (StringUtils.isBlank(detail)) {
				return "editSubmit";
			}
			if (StringUtils.isBlank(price)) {
				return "editSubmit";
			} else {
				tmpPrice = Double.parseDouble(price);
			}
			if (StringUtils.isBlank(summary)) {
				return "editSubmit";
			}

			// 更新商品
			Content product = new Content();
			product.setId(tmpId);
			product.setTitle(title);
			product.setImage(image);
			product.setDetail(detail);
			product.setPrice(tmpPrice);
			product.setSummary(summary);
			contentService.update(product);
			model.addAttribute("product", product);
			return "editSubmit";
		} catch (Exception e) {
			e.printStackTrace();
			return "editSubmit";
		}
	}

	/*******************************************************
	 * 产品发布请求地址
	 * 
	 * @param model
	 * @return
	 *******************************************************/
	@RequestMapping(value = "/back/public", method = RequestMethod.GET)
	public String toPublic() {
		return "public";
	}

	/*******************************************************
	 * 产品发布提交地址
	 * 
	 * @param model
	 * @return
	 *******************************************************/
	@RequestMapping(value = "/back/publicSubmit", method = RequestMethod.POST)
	public String publicSubmit(String title, String image, String detail, String price, String summary,
			ModelMap model) {
		try {
			// 数据校验
			Double tmpPrice = null;

			if (StringUtils.isBlank(title)) {
				return "publicSubmit";
			}
			if (StringUtils.isBlank(image)) {
				return "publicSubmit";
			}
			if (StringUtils.isBlank(detail)) {
				return "publicSubmit";
			}
			if (StringUtils.isBlank(price)) {
				return "publicSubmit";
			} else {
				tmpPrice = Double.parseDouble(price);
			}
			if (StringUtils.isBlank(summary)) {
				return "publicSubmit";
			}

			// 增加商品
			Content product = new Content();
			product.setTitle(title);
			product.setImage(image);
			product.setDetail(detail);
			product.setPrice(tmpPrice);
			product.setSummary(summary);
			contentService.insert(product);
			model.addAttribute("product", product);
			return "publicSubmit";
		} catch (Exception e) {
			e.printStackTrace();
			return "publicSubmit";
		}
	}

	/*******************************************************
	 * 产品发布提交地址
	 * 
	 * @param model
	 * @return
	 *******************************************************/
	@RequestMapping(value = "/back/api/delete", method = RequestMethod.POST)
	public @ResponseBody ModelMap delete(String id, ModelMap model) {
		try {
			if (!StringUtils.isBlank(id)) {
				Integer tmpId = Integer.parseInt(id);
				Content query = new Content();
				// 商品Id不空，且商品Id为数字
				query.setId(tmpId);
				Integer count = trxService.getTrxCountByContentId(query);
				// 商品订单数量<1可以删除
				if (count == null || count < 1) {
					int i = contentService.delete(query);
					if (i > 0) {
						ResponseJson.responseSucess(model, "删除成功！");
					}
				} else {
					ResponseJson.responseFail(model, "删除失败，商品已出售！");
				}
			} else {
				ResponseJson.responseFail(model, "商品不存在，删除失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseJson.responseFail(model, "内部错误，删除失败！");
		}
		return model;
	}
}
