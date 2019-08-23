package cn.hanquan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import cn.hanquan.orm.po.Pcd;
import cn.hanquan.service.UserServiceImpl;

/**
 * 选择地区
 * 
 * @author Buuug
 *
 */
public class LocateServlet extends HttpServlet {
	Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置编码及相应字符集
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		// 获取请求
		String pid = req.getParameter("pid");
		logger.debug("pid" + pid);

		// 获取子地区list
		UserServiceImpl us = new UserServiceImpl();
		List<Pcd> list = us.getAreaInfo(pid);

		// 设置响应
		resp.getWriter().write(new Gson().toJson(list));
	}
}
