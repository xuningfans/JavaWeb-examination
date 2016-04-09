package com.netease.course.dao;

import java.util.List;

import com.netease.course.meta.Content;
import com.netease.course.meta.Product;

/**
 * 商品Dao
 * 
 * @author 公猴脖子男
 */
public interface ContentDao extends BaseDao<Content> {

	/** 查询商品列表，type==1返回未购买商品 */
	List<Product> getProductList(Integer type);

	/** 根据ID查询商品商品详情 */
	Product getProductById(Integer id);
}