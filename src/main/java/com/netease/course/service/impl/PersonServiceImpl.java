package com.netease.course.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netease.course.meta.Person;
import com.netease.course.service.PersonService;

/**
 * 用户Service层实现类
 * 
 * @author 公猴脖子男
 */
@Service
@Transactional
public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {

	/**
	 * 用户登录验证方法
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 验证成功返回用户详细信息
	 */
	@Override
	@Transactional(readOnly = true) // 事务控制 -->只读
	public Person login(String username, String password) {
		// 用户名不为空则返回用户信息
		if (!(StringUtils.isBlank(username) && StringUtils.isBlank(password))) {
			Person user = new Person();
			user.setUserName(username);
			user.setPassword(password);
			return personDao.select(user);
		}
		return null;
	}

}
