<%@ page language="java" import="java.util.*,DAO.*" pageEncoding="utf-8"%>
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
	 <%
	 String FriendId = request.getParameter("id");
	 if (FriendId == null){
	 	response.sendRedirect("index.jsp");
	 	return;
	 }
	 String[] FriendContent = DataBaseOperate.GetFriend(FriendId);
	 if (FriendContent == null){
	 	response.sendRedirect("allfriend.jsp");
	 	return;
	 }
	  %>
	 <h1>我的好友录</h1><br/>
	 <a href="index.jsp">主页</a>
	 <hr/>
	 <form name='NewRowForm' action="servlet/AllFriendControler?type=DataModify" method="post">
		<hr/>
		<input type="hidden" name="FriendId" value="<%=FriendId %>"/>
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
				<td><input type="text" name="NewName" value="<%=FriendContent[2]%>"/></td>
				<td><input type="text" name="NewSex" value="<%=FriendContent[3]%>"/></td>
				<td><input type="text" name="NewAge" value="<%=FriendContent[4]%>"/></td>
				<td><input type="text" name="NewQQ" value="<%=FriendContent[5]%>"/></td>
				<td><input type="text" name="NewTel" value="<%=FriendContent[6]%>"/></td>
				<td><input type="text" name="NewMail" value="<%=FriendContent[7]%>"/></td>
				<td><input type="text" name="NewAddr" value="<%=FriendContent[8]%>"/></td>
			</tr>
			<tr>
				<td colspan="7" align="center"><input type="submit" value="修改纪录" onclick="return insert();"/></td>
		 	</tr>
		</table>
		</form>
	 	<hr/>
	 	当前时间：<%=(new java.util.Date().toLocaleString())%>
  </body>
</html>
