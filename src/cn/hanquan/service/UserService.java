package cn.hanquan.service;

import java.util.List;

import cn.hanquan.orm.po.User;

public interface UserService {
	/**
	 * 校验用户登录
	 * 
	 * @param uname 用户名
	 * @param pwd   密码
	 * @return 返回查询到的用户信息，如果用户名或错误则为null
	 */
	User checkUserLoginService(String uname, String pwd);

	/**
	 * 修改用户密码
	 * 
	 * @param newPwd 新密码
	 * @param uid 用户id
	 * @return 执行后影响行数
	 */
	int userChangePwdService(String newPwd, int uid);

	/**
	 * 获取所有的用户信息
	 * 
	 * @return 所有用户的集合
	 */
	List<User> userShowService();

	/**
	 * 用户注册
	 * 
	 * @param u 要注册的用户信息
	 * @return 执行后影响行数
	 */
	int userRegService(User u);
}
