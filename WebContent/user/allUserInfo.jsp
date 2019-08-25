<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="cn.hanquan.orm.po.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看用户信息</title>
<link href="/09-Manager/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/09-Manager/js/jquery.js"></script>

</head>

<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">数据表</a></li>
    <li><a href="#">基本内容</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <table class="tablelist">
    	<thead>
    	<tr>
	        <th>用户编号</th>
	        <th>用户名</th>
	        <th>密码</th>
	        <th>性别</th>
	        <th>年龄</th>
	        <th>生日</th>
        </tr>
        </thead>
        <tbody>
        
		<%-- 使用JSTL --%>
		<c:forEach items="${allUser}" var="oneUser">
			<tr>
		       <td>${oneUser.uid}</td>
		       <td>${oneUser.uname}</td>
		       <td>${oneUser.pwd}</td>
		       <td>${oneUser.sex}</td>
		       <td>${oneUser.age}</td>
		       <td>${oneUser.birth}</td>
	        </tr>            
		</c:forEach>
        </tbody>
    </table>
    </div>
    
    <%-- JSTL显示时间 --%>
    <div class="rightinfo">
	    <c:set var="now" value="<%=new java.util.Date()%>" />
	    <p>数据更新时间: <fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${now}" /></p>
	    <script type="text/javascript">
			$('.tablelist tbody tr:odd').addClass('odd');
		</script>
	</div>

</body>

</html>
