package cn.hanquan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 过滤器的使用：
 *   	作用：
 *   		对服务器接收的请求资源和相应给浏览器的资源进行管理。
 *   		保护servlet
 *   	使用：
 *   		创建一个实现了Filter接口的普通Java类(Eclipse中可以直接创建Filter)
 *   		覆写接口方法
 *   			init方法
 *   			doFilter方法
 *   			destroy方法
 *   		在web.xml中配置过滤器
 *   	 	      <filter>
 *				    <display-name>MyFilter</display-name>
 *				    <filter-name>MyFilter</filter-name>
 *				    <filter-class>cn.hanquan.filter.MyFilter</filter-class>
 *				  </filter>
 *				  <filter-mapping>
 *				    <filter-name>MyFilter</filter-name>
 *				    <url-pattern>/*</url-pattern>
 *				  </filter-mapping>
 *			注意：
 *				<url-pattern>/*</url-pattern>
 *					表示拦截所有请求
 *				<url-pattern>*.do</url-pattern>
 *					表示拦截所有以.do结尾的请求。一般是用来进行模块拦截处理。
 *				<url-pattern>/LoginServlet</url-pattern>
 *					表示拦截指定url的请求。针对某个servlet的请求进行拦截，保护servlet。
 *			过滤器的生命周期：
 *				从服务器启动到啊服务器关闭。
 *			总结：
 *				过滤器由程序员声明和配置，服务器收到请求后，根据URI信息在web.xml中找到对应
 *				的过滤器执行doFilter方法，该方法对此请求进行处理后，如果符合要求，则放行，
 *				放行后，如果还有符合要求的过滤器则继续进行过滤，找到执行对应的servlet进行请
 *				求处理。servlet对请求处理完毕后，也就service方法结束了。还需要返回相应的
 *				doFilter方法继续执行。
 *			案例：
 *				统一编码格式设置
 *				session管理
 *				权限管理
 *				资源管理(统一水印、和谐词汇等等)
 *
 *
 */
public class MyFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// 设置编码格式
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		// 判断session是否失效
//		HttpSession hs = ((HttpServletRequest) req).getSession();
//		if (hs.getAttribute("user") == null) {
//			((HttpServletResponse) resp).sendRedirect("/09-Manager/login.jsp");
//		} else {
//			// 放行
//			chain.doFilter(req, resp);
//		}

		// 放行
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
