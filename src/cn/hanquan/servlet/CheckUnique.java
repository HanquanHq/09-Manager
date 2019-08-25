package cn.hanquan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hanquan.orm.po.User;
import cn.hanquan.service.UserServiceImpl;

public class CheckUnique extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("utf-8");
//		resp.setCharacterEncoding("utf-8");
//		resp.setContentType("text/html;charset=utf-8");
		
		// 获取请求信息
		String uname = req.getParameter("name");
		if(uname=="") {
			resp.getWriter().write("用户名不能为空！");
			return;
		}
		UserServiceImpl us = new UserServiceImpl();
		boolean isUnique = us.checkUniqueService(uname);
		if(isUnique) {
			resp.getWriter().write("用户名已存在！");			
		}else {
			resp.getWriter().write("用户名可以使用！");
		}
	}
}
