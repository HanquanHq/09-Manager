package cn.hanquan.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import cn.hanquan.servlet.UserServlet;

/**
 * 检测在线人数 
 * session被创建，人数自增
 * session被销毁，人数自减
 * 
 * application初始化，onlineNum为0
 * application销毁，无动作
 * 
 * 只要用户打开浏览器中的login页面，就创建了一个session。
 * 导致退出之后虽然session--，但是又回到登录界面，session又加了1
 * 
 * @author Buuug
 *
 */
public class OnlineNum implements HttpSessionListener, ServletContextListener {
	Logger logger = Logger.getLogger(UserServlet.class);

	/**
	 * context(application)创建时，人数为0
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		sc.setAttribute("onlineNum", 0);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/**
	 * session创建时，获取context，自增
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext sc = se.getSession().getServletContext();
		int num = (int) sc.getAttribute("onlineNum");
		sc.setAttribute("onlineNum", ++num);
		logger.debug("session创建时，context自增之后:num="+num);
	}

	/**
	 * session销毁时，获取context，自减
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext sc = se.getSession().getServletContext();
		int num = (int) sc.getAttribute("onlineNum");
		sc.setAttribute("onlineNum", --num);
		logger.debug("session销毁时，context自减之后:num="+num);
	}

}
