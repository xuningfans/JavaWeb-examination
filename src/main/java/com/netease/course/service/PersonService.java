package com.netease.course.service;

import com.netease.course.meta.Person;

/**
 * 用户Service层接口
 * 
 * @author 公猴脖子男
 */
public interface PersonService extends BaseService<Person> {

	/**
	 * 用户登录验证方法
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 验证成功返回用户详细信息
	 */
	Person login(String username, String password);

}
