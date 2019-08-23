package cn.hanquan.service;

import java.awt.geom.Area;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.hanquan.orm.core.Query;
import cn.hanquan.orm.core.QueryFactory;
import cn.hanquan.orm.po.Pcd;
import cn.hanquan.orm.po.User;

@SuppressWarnings("all")
public class UserServiceImpl implements UserService {
	Logger logger = Logger.getLogger(UserServiceImpl.class);

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
			Object result = q.queryUniqueRow("select * from user where uname=? and pwd=?", User.class,
					new String[] { uname, pwd });
			logger.debug("根据用户名密码查找用户结果：" + result);
			return (User) result;
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

	/**
	 * 查询用户名是否重复
	 * 
	 * @param uname
	 * @return
	 */
	public boolean checkUniqueService(String uname) {
		Query q = QueryFactory.createQuery();
		List<User> list = q.queryRows("select * from user where uname=?", User.class, new String[] { uname });
		if (list == null || list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取指定pid地区信息list
	 * 
	 * @param pid
	 * @return 指定pid地区信息的list
	 */
	public List<Pcd> getAreaInfo(String pid) {
		Query q = QueryFactory.createQuery();
		List<Pcd> list = q.queryRows("select * from pcd where pid=? order by id", Pcd.class,
				new String[] { pid });
		logger.debug("获取pid=" + pid + "的地区信息：" + list);
		return list;
	}
}
