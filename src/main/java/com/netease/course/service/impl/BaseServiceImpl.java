package com.netease.course.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.netease.course.dao.BaseDao;
import com.netease.course.dao.ContentDao;
import com.netease.course.dao.PersonDao;
import com.netease.course.dao.TrxDao;
import com.netease.course.service.BaseService;

/**
 * Service层基类接口实现类
 * 
 * @author 公猴脖子男
 *
 * @param <T>
 */
// @Transactional(rollbackFor=Exception.class)
public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	protected PersonDao personDao;

	@Autowired
	protected ContentDao contentDao;

	@Autowired
	protected TrxDao trxDao;

	protected BaseDao<T> baseDao;

	/**
	 * 通过泛型自动装配对应Dao给BaseDao，实现通用调用， 该方法需对项目本身进行命名与使用规范
	 * 
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@PostConstruct
	private void initBaseDao()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// 获取当前对象（this）的父类和泛型信息
		// 当前对象（this）是由Spring Ioc自动装配的实现类
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();

		// 获取泛型参数第一个参数的字节码
		// PersonDao extends BaseDao<Person>中的person字节码
		Class<?> clazz = (Class<?>) type.getActualTypeArguments()[0];

		// 通过泛型名称（getSimpleName）组装当前对象属性名（相关Dao子类的引用名）PersonDao --> personDao
		String targetFieldName = clazz.getSimpleName().substring(0, 1).toLowerCase()
				+ clazz.getSimpleName().substring(1) + "Dao";

		// getDeclaredField:可以获取私有、默认、受保护、公共属性，但是不包括继承属性
		// 通过名称（targetFieldName）获取当前对象（this）对应属性，protected PersonDao personDao
		Field targetField = this.getClass().getSuperclass().getDeclaredField(targetFieldName);

		// 通过名称（baseDao）获取当前对象（this）对应属性，protected BaseDao<T> baseDao;
		Field baseField = this.getClass().getSuperclass().getDeclaredField("baseDao");

		// 打开属性访问权限
		baseField.setAccessible(true);

		// 将拿到的实现类Dao装配给BaseDao调用
		// 如，PersonService对象中，BaseDao所指向的实际对象为PersonDao
		// 实际对象已被Spring实例化于Ioc容器中
		// targetField.get(this) 获取当前对象（this）的targetField的值
		baseField.set(this, targetField.get(this));

	}

	/**
	 * 添加单个对象
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insert(T bean) throws Exception {
		int i = baseDao.insert(bean);
		return i;
	}

	/**
	 * 添加多个对象
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertList(List<T> bean) throws Exception {
		return baseDao.insertList(bean);
	}

	/**
	 * 删除单个对象
	 * 
	 * @param bean
	 * @return 删除列数
	 * @throws Exception
	 */
	@Override
	public int delete(T bean) throws Exception {
		return baseDao.delete(bean);
	}

	/**
	 * 通过id删除多个对象
	 * 
	 * @param ids
	 *            主键数组
	 * @return
	 * @throws Exception
	 */
	@Override
	public int deleteListById(String[] ids) throws Exception {
		return baseDao.deleteListById(ids);
	}

	/**
	 * 修改单个对象
	 * 
	 * @param bean
	 * @return 影响行数
	 * @throws Exception
	 */
	@Override
	public int update(T bean) throws Exception {
		return baseDao.update(bean);
	}

	/**
	 * 查询单个对象
	 * 
	 * @param bean
	 * @return 查询结果
	 */
	@Override
	// @Transactional(readOnly=true) // 事务控制-->只读
	public T select(T bean) {
		return baseDao.select(bean);
	}

	/**
	 * 有条件查询多个对象
	 * 
	 * @param bean
	 * @return 结果List集合
	 */
	@Override
	// @Transactional(readOnly=true) // 事务控制-->只读
	public List<T> selectList(T bean) {
		return (List<T>) baseDao.selectList(bean);
	}

	/**
	 * 无条件查询多个对象
	 * 
	 * @return 结果List集合
	 */
	@Override
	// @Transactional(readOnly=true) // 事务控制-->只读
	public List<T> selectList() {
		return baseDao.selectList(null);
	}

}
