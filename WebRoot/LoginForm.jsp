<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
    <title>登录</title>
  </head>
  <body>
  	<script type="text/javascript">
	function login(){
		if (document.LoginForm.UserName.value == ""){
			alert("用户名不能为空！");
			return false;
		}
		return true;
	}
	</script>
	<h1>我的好友录</h1>
  	<hr/>
  	用户登录
  	<form name="LoginForm" action="servlet/AccountServlet?type=Login" method="post">
  		<table border="0" width="100%">
  			<tr>
  				<td width="47%" align="right">登录名称</td>
  				<td><input type="text" name="UserName"></td>
  			</tr>
  			<tr>
  				<td width="47%" align="right">登录密码</td>
  				<td><input type="password" name="PassWord"></td>
  			</tr>
  			<tr>
  				<td colspan="2" align="center">
  					<input type="submit" onclick="return login();" value="登录">
  					<input type="reset" value="重置">
  					<input type="button" value="注册" onclick="window.location.href='RegisterForm.jsp'">
  				</td>
			</tr>
  		</table>
  	</form>
  	<hr/>
  	当前时间：<%=new java.util.Date().toLocaleString()%>
  </body>
</html>
