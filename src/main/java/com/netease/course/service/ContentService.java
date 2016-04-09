package com.netease.course.service;

import java.util.List;

import com.netease.course.meta.Content;
import com.netease.course.meta.Product;

/**
 * 产品详情Service层接口
 * 
 * @author 公猴脖子男
 */
public interface ContentService extends BaseService<Content> {

	/**
	 * 查询商品列表
	 * 
	 * @param type
	 *            查询类型，1已购买，0未购买
	 * @return
	 */
	List<Product> getProductList(Integer type);

	/**
	 * 根据商品编号查询商品详情
	 * 
	 * @param id
	 *            商品ID
	 * @return
	 */
	Product getProductById(Integer id);

}
