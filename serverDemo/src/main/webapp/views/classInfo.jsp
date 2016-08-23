<%@ page language="java" contentType="text/html; charset=UTF-8" 
	import="java.io.*,java.util.*,java.lang.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="classInfo.jsp"  method="GET" >
	<input type="text" name="clazzName"  value=""/>
</form>
<%
	String clazzName = request.getParameter("clazzName");
	String msg = "没有找到对应的类"; 
	String input_msg = "请输入完整类路径.回车"; 
	%>
	<%=clazzName + "<br/><b/>" %>
<%
	if((clazzName == null || clazzName.equals(""))) {
		%>
			<%=input_msg %>
		<%
	} else {
		try {
			Class<?> onwClass = Class.forName(clazzName);
			%>
			<%=onwClass.getProtectionDomain().getCodeSource().getLocation().getPath()%>
			 <%
		} catch (ClassNotFoundException e) {
			%>
			<%=msg %>
			<%
		}
	}	
%>
</body>
</html>