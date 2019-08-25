## 更新记录
2019-8-23 增加了地区的三级联动选择功能，可以访问JSP页面查看，但并没有加入到用户信息中。

详细介绍：https://blog.csdn.net/sinat_42483341/article/details/100043927

2019-8-24 使用了EL表达式，JSTL

2019-8-25 使用了Filter过滤器设置请求、响应字符集

## 信息管理系统开发文档

**技术需求**
Servlet+jsp+mvc+jdbc

**软件需求**
开发工具：Eclipse
数据库: mySql
服务器：tomcat
浏览器：Firefox

**功能需求**
完成用户登录。
完成用户注册。
完成用户退出。
完成查看个人信息。
完成修改密码。
完成查询所有用户信息。

**数据库设计**
创建用户表
表名:t_user

**表设计**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190822204942928.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NpbmF0XzQyNDgzMzQx,size_1,color_FFFFFF,t_70)

## 代码规范
**命名规范**
包名:com.bjsxt.*
类名:首字母大写，见名知意
变量名和方法名:驼峰原则，见名知意

**注释规范**
方法功能注释。
方法体核心位置必须有说明注释。

**日志规范**
使用log4j 进行日志输出(可选)。
数据流转的位置必须有后台输出语句。

