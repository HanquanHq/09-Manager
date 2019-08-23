<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic ValidateBox - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="/09-Manager/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/09-Manager/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/09-Manager/css/demo.css">
	<script type="text/javascript" src="/09-Manager/js/jquery.min.js"></script>
	<script type="text/javascript" src="/09-Manager/js/jquery.easyui.min.js"></script>
</head>
<style type="text/css">
#main{
	position:absolute;
    top:50%;
    left:50%;
    margin:-250px 0 0 -250px;
}
</style>


<script type="text/javascript">
	// 非空验证
	function check() {
		var uname = $("#uname").val();
		var pwd = $("#pwd").val();
		if (uname == null || uname == "" || pwd == null || pwd == "") {
			alert("用户名或密码不能为空");
			return false;
		}
		return true;
	}
</script>

<body>
<div id="main">
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="用户注册" style="width:500px;padding:10px 60px 20px 60px;">
	<form action="/09-Manager/UserServlet" method="post" onsubmit="return check()">
		<input type="hidden" name="oper" value="reg" />
		<table cellpadding="5">
			<tr>
				<td>用户名:</td>
				<td><input id="uname" name="uname" class="easyui-validatebox textbox" data-options="required:true"  missingMessage="用户名必填"></td>
					<td><input type="button" value="测试 " onclick="ajaxReq()"/><div id="showdiv"></div></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input id="pwd" name="pwd" class="easyui-validatebox textbox" data-options="required:true" missingMessage="密码必填"></td>
			</tr>
			<tr>
				<td>性别:</td>
				<td>
					男: <input type="radio" name="sex" value="1" checked="checked"/>
					女: <input type="radio" name="sex" value="0"/>
				</td>
			</tr>
			<tr>
				<td>年龄:</td>
				<td><input name="age" class="easyui-validatebox textbox" value=""></td>
			</tr>
			<tr>
				<td>出生年月:</td>
				<td><input name="birth" class="easyui-datebox textbox" value=""></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="注册" />
				</td>
			</tr>
		</table>
	</form>
	</div>
	<style scoped="scoped">
		.textbox{
			height:20px;
			margin:0;
			padding:0 2px;
			box-sizing:content-box;
		}
	</style>
</div>



	
	
<script type="text/javascript">
	// Ajax
	function ajaxReq(){
		//获取用户请求数据
		var uname=document.getElementById("uname").value;
		var data="name="+uname;
		//创建ajax引擎对象
		var ajax;
		if(window.XMLHttpRequest){
			ajax=new XMLHttpRequest();
		}else if(window.ActiveXObject){
			ajax=new ActiveXObject("Msxml2.XMLHTTP");
		}
		//复写onreadystatechange函数
		ajax.onreadystatechange=function(){
			//判断ajax状态码
			if(ajax.readyState==4){
				//判断响应状态码
				if(ajax.status==200){
					//获取响应内容
					var result=ajax.responseText;
					//处理响应内容
					var showdiv=document.getElementById("showdiv");
					showdiv.innerHTML=result;
				}	
			}	
		}
		//发送请求
			//post方式：请求实体需要单独的发送
				ajax.open("post", "/09-Manager/CheckUnique");
				ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
				ajax.send(data);
	}
</script>

</body>
</html>