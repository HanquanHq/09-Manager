<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="cn.hanquan.orm.po.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/09-Manager/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/09-Manager/js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>


</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
	    <span style="font-size: 17px; color: blue; font-weight: bold;">
	   		 欢迎&nbsp;<%=((User)session.getAttribute("user")).getUname()%>&nbsp;登陆信息管理系统！请在左侧选择您的操作
	    </span>
	
	    <div class="tip">
	    	<div class="tiptop"><span>提示信息</span><a></a></div>
	      	<div class="tipinfo">
		        <span><img src="/09-Manager/images/ticon.png" /></span>
		        <div class="tipright">
		        <p>是否确认对信息的修改 ？</p>
		        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
		        </div>
	        </div>
	        
	        <div class="tipbtn">
	        <input name="" type="button"  class="sure" value="确定" />&nbsp;
	        <input name="" type="button"  class="cancel" value="取消" />
	        </div>
	    </div>
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
    