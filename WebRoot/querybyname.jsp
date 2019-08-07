<%@ page language="java" import="java.util.*,DAO.*" pageEncoding="utf-8"%>
<html>
  <head>
  	<title>按姓名模糊查询好友信息</title>
  </head>
  <body>
	<script>
		function FindByName(){
			if (document.QueryNameForm.FriendName.value == ""){
				alert("查询名字不能为空！");
				return false;
			}
			return true;
		}
	</script>
	<h1>我的好友录</h1><br/>
	<a href="index.jsp">主页</a>
	<hr/>
	<form name='QueryNameForm' action='allfriend.jsp' method='post'>
		输入要查询的名字：<input type='text' name='FriendName'/><input type='submit' onclick='return FindByName();'/>
	</form>
	<hr/>
	当前时间：<%=(new java.util.Date().toLocaleString())%>
  </body>
</html>
