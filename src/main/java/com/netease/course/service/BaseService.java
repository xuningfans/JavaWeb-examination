package com.netease.course.service;

import java.util.List;

/**
 * Service层基类接口提供基本增删改查
 * 
 * @author 公猴脖子男
 *
 * @param <T>
 */
public interface BaseService<T> {

	/**
	 * 添加单个对象
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int insert(T bean) throws Exception;

	/**
	 * 添加多个对象
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertList(List<T> list) throws Exception;

	/**
	 * 修改单个对象
	 * 
	 * @param entity
	 * @return 影响行数
	 * @throws Exception
	 */
	public int update(T bean) throws Exception;

	/**
	 * 删除单个对象
	 * 
	 * @param entity
	 * @return 删除列数
	 * @throws Exception
	 */
	public int delete(T bean) throws Exception;

	/**
	 * 通过id删除多个对象
	 * 
	 * @param ids
	 *            主键数组
	 * @return
	 * @throws Exception
	 */
	public int deleteListById(String[] ids) throws Exception;

	/**
	 * 查询单个对象
	 * 
	 * @param entity
	 * @return 查询结果
	 */
	public T select(T bean);

	/**
	 * 有条件查询多个对象
	 * 
	 * @param entity
	 * @return 结果List集合
	 */
	public List<T> selectList(T bean);

	/**
	 * 无条件查询多个对象
	 * 
	 * @return 结果List集合
	 */
	public List<T> selectList();
}
