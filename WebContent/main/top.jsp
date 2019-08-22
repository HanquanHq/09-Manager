<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="cn.hanquan.orm.po.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/09-Manager/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/09-Manager/js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		//退出功能
		$("#out").click(function() {
			var flag = window.confirm("你真的要退出吗?");
			if (flag) {
				window.top.location.href = "/09-Manager/UserServlet?oper=out";
			}
		})
	})
</script>

</head>

<body style="background:url(/09-Manager/images/topbg.gif) repeat-x;">

    <div class="topleft">
    	<a href="/09-Manager/main/main.jsp" target="_parent"><img src="/09-Manager/images/logo.png" title="系统首页" /></a>
    </div>

	<div class="topright">
    	<!-- 退出 -->    
	    <ul>
	    <li><a href="javascript:void(0)" id="out">退出</a></li>
	    </ul>
	    <!-- 用户名 -->
	    <div class="user">
	    <span><%=((User)session.getAttribute("user")).getUname()%></span>
	    </div>    
    </div>

</body>
</html>
    