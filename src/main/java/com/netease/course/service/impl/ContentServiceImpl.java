package com.netease.course.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.netease.course.meta.Content;
import com.netease.course.meta.Product;
import com.netease.course.service.ContentService;

/**
 * 产品详情Service层实现类
 * 
 * @author 公猴脖子男
 */
@Service
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

	/**
	 * 查询商品列表
	 * 
	 * @param type
	 *            查询类型，1已购买，0未购买
	 * @return
	 */
	@Override
	public List<Product> getProductList(Integer type) {
		return contentDao.getProductList(type);
	}

	/**
	 * 根据商品编号查询商品详情
	 * 
	 * @param id
	 *            商品ID
	 * @return
	 */
	@Override
	public Product getProductById(Integer id) {
		return contentDao.getProductById(id);
	}

}
