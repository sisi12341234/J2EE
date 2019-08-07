<%@ page language="java" import="java.util.*,DAO.*" pageEncoding="utf-8"%>
<html>
  <head>
  	<title>查询所有好友信息</title>
  </head>
  <body>
	 <h1>我的好友录</h1><br/>
	 <a href="index.jsp">主页</a>
	 <a href="LoginForm.jsp">注销</a>
	 <hr/>
	 <form name='TableForm' action="servlet/AllFriendControler?type=DeleteOrModify" method="post">
		 <input type="hidden" name="SubmitOperate" value="Nothing">
		 <a href="servlet/AllFriendControler?type=insert">添加新纪录</a>
		 <a href="javascript:TableForm.submit()" onclick="document.TableForm.SubmitOperate.value='Modify'";>修改选中记录</a>
		 <a href="javascript:TableForm.submit()" onclick="document.TableForm.SubmitOperate.value='Delete'">删除选中记录</a>
		 <hr/>
		 	<%
		 	request.setCharacterEncoding("utf-8");
		 	String SubStr = request.getParameter("FriendName");
		 	if (SubStr == null){
		 		SubStr = "";
		 	}
		 	String[][] FriendList = DataBaseOperate.GetAllFriends((String)session.getAttribute("LoginedUserName"),SubStr);
		 	%>
		 	<table width='100%' border='1'>
			 	<tr>
			 		<td><strong>修改</strong></td>
			 		<td><strong>删除</strong></td>
			 		<td><strong>姓名</strong></td>
			 		<td><strong>性别</strong></td>
			 		<td><strong>年龄</strong></td>
			 		<td><strong>QQ</strong></td>
			 		<td><strong>电话</strong></td>
			 		<td><strong>E-Mail</strong></td>
			 		<td><strong>地址</strong></td>
			 	</tr>
		 	<%
		 	if (FriendList != null){
				for (int i = 0;i < FriendList.length;i ++){
					out.print("<tr>");
					out.print("<td><input type='radio' name='Modify' value='" + FriendList[i][0] + "'/></td>");
					out.print("<td><input type='checkbox' name='Delete' value='" + FriendList[i][0] + "'/></td>");
					for (int j = 2; j < FriendList[i].length;j ++){
						out.print("<td>" + FriendList[i][j] + "</td>");
					}
					out.print("</tr>");
				}
			}
			else{
				%>
				<tr>
			 		<td colspan='9'>没有记录</td>
			 	</tr>
			 	<%
			}
			%>
			</table>
		</form>
	 	<hr/>
	 	当前时间：<%=(new java.util.Date().toLocaleString())%>
  </body>
</html>
