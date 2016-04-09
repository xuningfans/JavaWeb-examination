# JavaWeb-examination 简单介绍


####1. 引入了SpringMvc拦截器进行登录权限管理。
	1.1. 修改了ftl及js中默认请求路径，前台为/font/*,后台为/back/* , /* 无需登录。
####2. 使用Cookie进行免登陆，Cookie默认有效期一小时，但Cookie并未加密。
####3. 数据库使用课件提供SQL脚本并未修改。
####4. Dao层和Service层均使用了一个Base基类进行增删改查。
####5. 事务全部采用注解进行控制，查询方法使用readOnly=true。
####6. 全局使用了异常处理，写在BaseController中，默认消息为“内部错误”。
