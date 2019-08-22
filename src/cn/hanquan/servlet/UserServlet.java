package cn.hanquan.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.hanquan.orm.po.User;
import cn.hanquan.service.UserServiceImpl;

/**
 * 接收提交的表单，并根据登陆结果重定向
 * 
 * @author Buuug
 *
 */
public class UserServlet extends HttpServlet {
	Logger logger = Logger.getLogger(UserServlet.class);
	UserServiceImpl us = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 设置请求、响应编码格式
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		// 获取操作
		String oper = req.getParameter("oper");
		logger.debug("操作：oper=" + oper);
		if ("login".equals(oper)) {
			// 登录
			checkUserLogin(req, resp);
		} else if ("out".equals(oper)) {
			// 退出
			userOut(req, resp);
		} else if ("pwd".equals(oper)) {
			// 密码修改
			userChangePwd(req, resp);
		} else if ("show".equals(oper)) {
			// 显示所有用户
			userShow(req, resp);
		} else if ("reg".equals(oper)) {
			// 注册
			userReg(req, resp);
		} else {
			logger.debug("没有找到对应的操作符：" + oper);
		}

	}

	/**
	 * 登录
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 获取表单请求信息
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		logger.debug("输入的uname=" + uname + "pwd=" + pwd);

		User u = us.checkUserLoginService(uname, pwd);

		// 登录结果
		if (u != null) {// right
			// 登录数据存到session
			HttpSession hs = req.getSession();
			hs.setAttribute("user", u);

			// 重定向
			resp.sendRedirect("/09-Manager/main/main.jsp");
			logger.debug("使用" + uname + " " + pwd + "尝试登录成功");
			return;
		} else {// wrong
			// 请求转发
			logger.debug("使用" + uname + " " + pwd + "尝试登录失败");
			req.setAttribute("str", "用户名或密码错误，请重新输入！");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			return;
		}
	}

	/**
	 * 退出
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession hs = req.getSession();
		hs.invalidate();
		resp.sendRedirect("/09-Manager/login.jsp");
		logger.debug("用户退出登录");
	}

	/**
	 * 注册
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void userReg(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		User u = new User();
		
		// 获取请求
		String _uname = req.getParameter("uname");
		String _pwd = req.getParameter("pwd");
		String _sex = req.getParameter("sex");
		String _age = req.getParameter("age");
		String _birth = req.getParameter("birth");
		logger.debug("_birth：" + _birth);

		// 日期转换
		if((!_birth.equals(null))&&_birth!="") {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date utilBirth = null;
			try {
				utilBirth = df.parse(_birth);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date sqlBirth = new java.sql.Date(utilBirth.getTime());// 毫秒作为过渡
			logger.debug(sqlBirth);
			u.setBirth(sqlBirth);

		}

		// 存入JavaBean
		u.setUname(_uname);
		u.setPwd(_pwd);
		if (_sex != "")
			u.setSex(_sex);
		if (_age != "")
			u.setAge(Integer.parseInt(_age));

		// 提交数据
		int line = us.userRegService(u);
		if (line > 0) {
			req.setAttribute("str", "注册成功！请登录");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			logger.debug("用户注册：" + u);
			return;
		} else {
			logger.debug("严重错误：注册失败");
		}
	}

	// 显示用户信息
	private void userShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> list = new ArrayList<User>();
		list = us.userShowService();
		if (list != null) {
			req.setAttribute("allUser", list);
			req.getRequestDispatcher("/user/allUserInfo.jsp").forward(req, resp);
			return;
		}
		logger.debug("显示所有用户信息");
	}

	// 修改密码
	private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String newPwd = req.getParameter("newPwd");
		User u = (User) req.getSession().getAttribute("user");
		logger.debug(u + "修改密码");
		int uid = u.getUid();
		int line = us.userChangePwdService(newPwd, uid);
		if (line > 0) {
			req.setAttribute("str", "密码修改成功！请重新登录");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			return;
		} else {
			logger.debug("严重错误：密码修改失败");
		}
	}
}