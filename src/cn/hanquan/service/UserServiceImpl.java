package cn.hanquan.service;

import java.util.List;

import org.apache.log4j.Logger;

import cn.hanquan.orm.core.Query;
import cn.hanquan.orm.core.QueryFactory;
import cn.hanquan.orm.po.User;

public class UserServiceImpl implements UserService{
	Logger logger=Logger.getLogger(UserServiceImpl.class);

	/**
	 * 用户登录
	 */
	@Override
	public User checkUserLoginService(String uname, String pwd) {
		if (uname == null || pwd == null) {
			logger.debug("没有提交登录信息：uname=" + uname + " pwd=" + pwd);
			return null;
		} else {
			User u = new User();
			u.setUname(uname);
			u.setPwd(pwd);
			Query q = QueryFactory.createQuery();
			Object result = q.queryUniqueRow("select * from user where uname=? and pwd=?", User.class, new String[] { uname, pwd });
			logger.debug("根据用户名密码查找用户结果：" + result);
			return (User)result;
		}
	}

	/**
	 * 修改密码
	 */
	@Override
	public int userChangePwdService(String newPwd, int uid) {
		Query q = QueryFactory.createQuery();
		User u = (User) q.queryUniqueRow("select * from user where uid = ?", User.class, new String[] { "" + uid });
		u.setPwd(newPwd);
		int line = q.update(u, new String[] { "pwd" });
		logger.debug("修改密码影响行数：" + line);
		return line;
	}

	/**
	 * 获取用户信息
	 */
	@Override
	public List<User> userShowService() {
		Query q = QueryFactory.createQuery();
		List<User> list = q.queryRows("select * from user", User.class, null);
		logger.debug("查看所有用户：" + list);
		return list;
	}

	/**
	 * 用户注册
	 */
	@Override
	public int userRegService(User u) {
		Query q = QueryFactory.createQuery();
		int line = q.insert(u);
		logger.debug("用户注册影响行数：" + line);
		return line;
	}

}
