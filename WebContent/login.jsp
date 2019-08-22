<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/09-Manager/js/jquery.js"></script>
<script src="/09-Manager/js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

</head>

<body style="background-color:#df7611; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
		<%
   			Object str = (String) request.getAttribute("str");
			String showStr;
			if(str!=null){
				showStr = (String) str;
			}else{
				showStr="";
			}
   			
	    %>


    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>
    
    </div>
	    <div class="loginbody">
	    <span class="systemlogo"></span> 
	    <div class="loginbox loginbox1">
		    <form action='UserServlet' method='post'>
			    <input type="hidden" name="oper" value="login" />
			    <ul>
			    <li><input name="uname" type="text" class="loginuser" placeholder="用户名"/></li>
			    <li><input name="pwd" type="password" class="loginpwd" placeholder="密码" /></li>
			    <li><br /><br />
			    <input name="oper" type="submit" class="loginbtn" value="登录"  onclick="javascript:window.location=''"  />
				<label><a href="/09-Manager/user/reg.jsp">注册</a></label><label><a href="#">忘记密码？</a></label>
				</li>
			    </ul>
		     </form>
	    </div>
	    

			<div style="text-align: center;">
				<span style="font-size: 20px; color: blue; font-weight: bold;"><%=showStr%></span>
			</div>
	</div>
	<div class="loginbm">版权所有  2019  <a href="">hanquan.cn</a> 仅供学习交流，勿用于任何商业用途~~</div>
	
</body>

</html>
