# [JavaWeb-examination 简单介绍][1]




####1. 引入了SpringMvc拦截器进行登录权限管理。
> **Note:此处修改了ftl以及js中原请求路径**

> - 前台为-->/font/*
> - 后台为-->/back/* 
> - 无需登录-->/* 
> - 未购商品列表权限特殊处理-->/?type=1
	
####2. 使用Cookie进行免登陆，Cookie默认有效期一小时.
> **Note:此处Cookie并未加密**

####3. 数据表使用课件中提供的SQL脚本并未修改。
####4. Dao层和Service层均使用了一个Base基类提供基本增删改查。
####5. 事务全部采用xml配置式进行控制。
####6. 全局使用了异常处理，写在BaseController中，默认消息为“内部错误”。
####7. 默认404页面配置在web.xml中，默认跳转/html/error.html。



[1]: http://106.2.111.86:8080/
