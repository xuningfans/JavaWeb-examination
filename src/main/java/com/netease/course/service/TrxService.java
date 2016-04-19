package com.netease.course.service;

import java.util.List;

import com.netease.course.meta.Content;
import com.netease.course.meta.Person;
import com.netease.course.meta.Trx;

/**
 * 订单Service层接口
 * 
 * @author 公猴脖子男
 */
public interface TrxService extends BaseService<Trx> {

	/**
	 * 购买商品，生成订单
	 * 
	 * @param id
	 *            商品编号
	 * @param user
	 *            购买人
	 * @throws Exception
	 */
	int insertTrx(String contentId, Person user) throws Exception;

	/**
	 * 获取账目列表
	 * 
	 * @param user
	 *            获取用户对象
	 * @return 账目列表
	 */
	List<Trx> getBuyList(Person user);

	/** 根据商品查询订单数量 */
	Integer getTrxCountByContentId(Content query);

}
