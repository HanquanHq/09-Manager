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

<script type="text/javascript">
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
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="用户注册" style="width:400px;padding:10px 60px 20px 60px;">
	<form action="/09-Manager/UserServlet" method="post" onsubmit="return check()">
		<input type="hidden" name="oper" value="reg" />
		<table cellpadding="5">
			<tr>
				<td>用户名:</td>
				<td><input id="uname" name="uname" class="easyui-validatebox textbox" data-options="required:true"  missingMessage="用户名必填"></td>
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

</body>
</html>