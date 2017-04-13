<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'hello.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my hello page. <br>    
    <p><c:out value="model['result']"></c:out></p>   
    <!-- 输出普通字符 -->  
    ${msg } <br/>  
    <!-- 输出List -->  
    <p>书籍列表</p>  
    <c:forEach items="${bookList}" var="node">  
         <c:out value="${node}"></c:out>  
    </c:forEach>  
    <br/>  
    <br/>  
      
    <!-- 输出Map -->  
   <c:forEach items="${map}" var="node">  
         姓名：<c:out value="${node.key}"></c:out>  
         住址：<c:out value="${node.value}"></c:out>  
   <br/>  
   </c:forEach>  
    
    
  </body>
</html>
