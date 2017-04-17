<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Integer isLogin = (Integer)request.getSession().getAttribute("isLogin");
String userinfo = (String)request.getSession().getAttribute("userInfo");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/Validform/css/Validform_v5.3.2_min.css">
	<script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/Validform/Validform_v5.3.2_min.js"></script>

  </head>
  
  <body>
    <div id="top">   
      <c:if test="${not empty isLogin && isLogin == 1}"> 
                                    欢迎:${name}&nbsp;&nbsp;<a href='/loginout.do'>注销</a>
      </c:if> 
      <c:if test="${empty isLogin || isLogin == 0}"> 
         <a href='/login.jsp'>请登录</a>
      </c:if>    
    </div>
<!--     <button id="getStudents">获取学生信息</button> -->
    <!-- 表单提交 -->
    <form action="/add.do" method="post" id="information">  
            <table align="center" border='0' cellspacing="10" cellpadding="10">  
                <input type="hidden" name="id" value="id"/>  
                <tr>  
                    <td>用户名：</td>  
                    <td><input type="text" name="userName" value="" datatype="s2-8" nullmsg="请输入用户名！" sucmsg="用户验证通过！" errormsg="用户名必须2-8位长度"/></td>  
                </tr>  
                <tr id="psw">  
                    <td>密码：</td>  
                    <td><input type="password" name="password" datatype="n6-6" nullmsg="请输入密码！" sucmsg="密码验证通过！" errormsg="密码必须6位整数"/></td>  
                </tr>  
                <tr>  
                    <td>年龄：</td>  
                    <td><input type="text" name="age" datatype="n2-2" nullmsg="请输入年龄！" sucmsg="年龄验证通过！" errormsg="年龄必须2位整数"/></td>   
                </tr> 
                <tr>  
                    <td>爱好：</td>  
                    <td id="hobby">  
                        <input type="checkbox" name="hobby0" value="0" hid="0">电影  
                        <input type="checkbox" name="hobby1" value="1" hid="1">上网 
                    </td>  
                </tr>  
                <tr>  
                    <td>性别：</td>  
                    <td>  
                        <input type="radio" name="sex" value="0" checked="checked"/>男  
                         <input type="radio" name="sex" value="1"/>女<br/>  
                    </td>  
                </tr>  
                <tr>  
                    <td>头像：</td>  
                    <td>  
                        <input type="file" name="img"/><br/>  
                    </td>  
                </tr>
                <tr>
                    <td>职业：</td>
                    <td>
                        <select name="occupation"> 
                            <option value="0">==请选择==</option> 
                            <option value="1">律师</option>  
                            <option value="2">学生</option>  
                            <option value="3">医生</option>  
                        </select>  
                    </td>
                </tr>  
                <tr>  
                    <td>个人介绍：</td>  
                    <td><textarea rows="5" cols="20" name="introduce"></textarea><br/></td>  
                </tr>  
                <tr id="add_s">  
                    <td colspan="2" align="center">  
                        <input type="submit" value="添加"/>  
                        <input type="reset" value="重置"/>  
                    </td>                  
                </tr>
            </table>  
        </form>          
   
  </body>
  <script type="text/javascript"> 
  var info = $("#information").Validform({
    tiptype:3
  });
  window.onload=function(){
  

  }

  </script>
</html>
