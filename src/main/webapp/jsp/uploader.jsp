<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'uploader.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
            <form action="/simpleFileupload.do" method="post" enctype="multipart/form-data">  
             <input type="hidden" type="text" name="id"/>
			    文件上传：<input type="file" name="fileupload"/>  
			    描述：<input type="text" name="desc"/>  
             <input type="submit" value="上传"/>  
            </form> 
  </body>
  <script type="text/javascript">
   var _id = '${id}';
   if(_id != ''){
   $("input[name='id']").val(_id);
   }
   else{
    alert("用户尚未登录，不能上传文件！！");
    window.location.href="/login.jsp";
   }
  </script>
</html>
