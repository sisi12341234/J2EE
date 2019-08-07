<%@page pageEncoding="gbk" %>
<%@page import="java.sql.*" %>
<h1>我的好友录</h1>
<br>
<br>
<hr>
<br/>
<a href="allfriend.jsp">查询所有好友信息</a>
<br/>
<br/>
<a href="querybyname.jsp">按姓名查询好友信息</a>
<br>
<hr>
<% 
	out.println("当前时间："+new java.util.Date().toLocaleString());
%>