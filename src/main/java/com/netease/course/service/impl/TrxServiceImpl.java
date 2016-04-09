package com.netease.course.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netease.course.meta.Content;
import com.netease.course.meta.Person;
import com.netease.course.meta.Trx;
import com.netease.course.service.TrxService;

/**
 * 订单Service层实现类
 * 
 * @author 公猴脖子男
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TrxServiceImpl extends BaseServiceImpl<Trx> implements TrxService {

	/**
	 * 购买商品，生成订单
	 * 
	 * @param id
	 *            商品编号
	 * @param user
	 *            购买人
	 * @throws Exception
	 */
	@Override
	public int buy(String contentId, Person user) throws Exception {
		// 将参数转换为整型
		Integer tmpId = Integer.parseInt(contentId);
		// 新建查询条件
		Content query = new Content();
		query.setId(tmpId);
		// 查询商品详细信息
		Content content = contentDao.select(query);

		// 生成订单
		Trx trx = new Trx();
		trx.setContent(content);
		trx.setPerson(user);
		trx.setBuyPrice(content.getPrice());
		trx.setTime(new Date().getTime());
		// 插入订单
		int i = trxDao.insert(trx);
//		throwException();
		return i;
	}

	/**
	 * 测试事务
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void throwException() throws Exception {
		throw new Exception();
	}

	/**
	 * 获取账目列表
	 * 
	 * @param user
	 *            获取用户对象
	 * @return 账目列表
	 */
	@Override
	@Transactional(readOnly = true) // 事务控制 -->只读
	public List<Trx> getBuyList(Person user) {
		return trxDao.getBuyList(user);
	}

}
