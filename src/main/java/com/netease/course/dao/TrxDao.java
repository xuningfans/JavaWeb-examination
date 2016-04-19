package com.netease.course.dao;

import java.util.List;

import com.netease.course.meta.Content;
import com.netease.course.meta.Person;
import com.netease.course.meta.Trx;

/**
 * 订单Dao
 * 
 * @author 公猴脖子男
 */
public interface TrxDao extends BaseDao<Trx> {

	/** 根据用户查询购买列表 */
	List<Trx> getBuyList(Person user);

	/** 根据商品查询订单数量 */
	Integer getTrxCountByContentId(Content content);
}