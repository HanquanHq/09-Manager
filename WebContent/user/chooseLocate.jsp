<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title> 	
<style type="text/css">
	select{
		width:300px;
		height:30px;
	
	}
	#showdiv{
		width:1000px;
		margin:auto;
		margin-top:150px;
	}
	body{
	
		background-color: gray;
	}
</style>

<!-- 
	三级联动思路:
		1、因为需要在下拉框中实时的变更其中数据，而页面的其他数据不变，使用Ajax
		2、页面加载成功后省下拉框中要填充好所有的省信息
 -->
<script type="text/javascript" src="/09-Manager/js/ajaxUtil.js"></script>
<script type="text/javascript">
	//创建函数获取所有省信息
		function getProvince(){
		//使用ajax请求所有的省信息
			myAjax("get","/09-Manager/LocateServlet","pid=0",function(ajax){
				//获取响应数据
				var result=ajax.responseText;
				eval("var areas="+result);
				//处理响应数据
					//获取下拉框对象
					var sel=document.getElementById("province");
					//遍历数据将数据填充到下拉框中
					for(var i=0;i<areas.length;i++){
						sel.innerHTML=sel.innerHTML+"<option value="+areas[i].id+">"+areas[i].name+"</option>"
					}
					getCity();
			});
			
		}
	
	//获取所有市信息
		function getCity(){
		 	var pid=document.getElementById("province").value;
		 	myAjax("get","/09-Manager/LocateServlet","pid="+pid, function(ajax){
		 		//获取响应数据
				var result=ajax.responseText;
				eval("var areas="+result);
				//获取下拉框对象
				var sel=document.getElementById("city");
				sel.innerHTML="";
				//遍历数据将数据填充到下拉框中
				for(var i=0;i<areas.length;i++){
					sel.innerHTML=sel.innerHTML+"<option value="+areas[i].id+">"+areas[i].name+"</option>"
				}
				geTown();
		 	})
		}
	
		//获取所有区县信息
		function geTown(){
		 	var pid=document.getElementById("city").value;
		 	myAjax("get","/09-Manager/LocateServlet","pid="+pid, function(ajax){
		 		//获取响应数据
				var result=ajax.responseText;
				eval("var areas="+result);
				//获取下拉框对象
				var sel=document.getElementById("town");
				sel.innerHTML="";
				//遍历数据将数据填充到下拉框中
				for(var i=0;i<areas.length;i++){
					sel.innerHTML=sel.innerHTML+"<option value="+areas[i].id+">"+areas[i].name+"</option>"
				}
		 	})
		}
</script>

</head>
<body onload="getProvince()">
	<div id="showdiv">
		省:<select id="province" onchange="getCity()"></select>
		市：<select id="city" onchange="geTown()"></select>
		区县:<select id="town"></select>
	</div>
	
</body>
</html>