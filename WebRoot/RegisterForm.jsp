<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
    <title>注册</title>
  </head>
  <body>
	<script type="text/javascript">
	function register(){
		if (document.RegisterForm.UserName.value == ""){
			alert("用户名不能为空！");
			return false;
		}
		if (document.RegisterForm.PassWord.value.length < 6){
			alert("密码不能少于6位！");
			return false;
		}
		if (document.RegisterForm.PassWord.value != document.RegisterForm.PassWordAgain.value){
			alert("两次密码必须一致！");
			return false;
		}
		return true;
	}
	</script>
  	<h1>我的好友录</h1>
  	<hr/>
  	用户注册
  	<form name=RegisterForm action="servlet/AccountServlet?type=Register" method="post">
  		<table border="0" width="100%">
  			<tr>
  				<td width="45%" align="right">登录名称</td>
  				<td><input type=text name=UserName></td>
  			</tr>
  			<tr>
  				<td align="right">登录密码</td>
  				<td><input type=password name=PassWord></td>
  			</tr>
  			<tr>
  				<td align="right">密码确认</td>
  				<td><input type=password name=PassWordAgain></td>
  			</tr>
  			<tr>
  				<td colspan="2" align="center">
  					<input type="submit" onclick="return register();" value="注册">
  					<input type="reset" value="重置">
  				</td>
			</tr>
  		</table>
  	</form>
  	<hr/>
  	当前时间：<%=(new java.util.Date().toLocaleString())%>
  </body>
</html>
