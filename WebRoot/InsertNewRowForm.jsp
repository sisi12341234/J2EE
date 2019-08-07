<%@ page language="java" import="java.util.*,DAO.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>添加新纪录</title>
  </head>
  <body>
	 <script>
	 function insert(){
	 	if (document.NewRowForm.NewName.value == ""){
	 		alert("姓名不能为空！");
	 		return false;
	 	}
	 	else if (document.NewRowForm.NewAge.value == ""){
	 		alert("年龄不能为空！");
	 		return false;
	 	}
	 	return true;
	 }
	 </script>
	 <h1>我的好友录</h1><br/>
	 <a href="index.jsp">主页</a>
	 <hr/>
	 <form name='NewRowForm' action="servlet/AllFriendControler?type=DataInsert" method="post">
		<hr/>
		<table width='100%' border='1'>
			<tr>
				<td><strong>姓名</strong></td>
				<td><strong>性别</strong></td>
				<td><strong>年龄</strong></td>
				<td><strong>QQ</strong></td>
				<td><strong>电话</strong></td>
				<td><strong>E-Mail</strong></td>
				<td><strong>地址</strong></td>
			</tr>
			<tr>
				<td><input type="text" name="NewName"/></td>
				<td><input type="text" name="NewSex"/></td>
				<td><input type="text" name="NewAge"/></td>
				<td><input type="text" name="NewQQ"/></td>
				<td><input type="text" name="NewTel"/></td>
				<td><input type="text" name="NewMail"/></td>
				<td><input type="text" name="NewAddr"/></td>
			</tr>
			<tr>
				<td colspan="7" align="center"><input type="submit" value="添加新纪录" onclick="return insert();"/></td>
		 	</tr>
		</table>
		</form>
	 	<hr/>
	 	当前时间：<%=new java.util.Date().toLocaleString()%>
  </body>
</html>
