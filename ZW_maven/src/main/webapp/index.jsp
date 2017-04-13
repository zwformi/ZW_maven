<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    <form method="post" action="/login.do">
    <lable for="username">用户</lable>
    <input id="username" name="username" type="text" placeholder="输入用户名"/>
    <lable for="userpsw">密码</lable>
    <input id="userpsw" name="userpsw" type="password" placeholder="输入用户 密码"/> 
    <input type="submit" value="登陆"> 
    </form>
    <button id="getStudents">获取学生信息</button>
    <!-- 表单提交 -->
    <form action="#" method="post">  
            <table align="center" border='0' cellspacing="10" cellpadding="10">  
                <input type="hidden" name="id" value="id"/>  
                <tr>  
                    <td>用户名：</td>  
                    <td><input type="text" name="userName" value="" /></td>  
                </tr>  
                <tr>  
                    <td>密码：</td>  
                    <td><input type="password" name="password" /><br/></td>  
                </tr>  
                <tr>  
                    <td>爱好：</td>  
                    <td>  
                        <input type="checkbox" name="hobby" value="film" checked="checked" hid="0">电影  
                        <input type="checkbox" name="hobby" value="net" hid="1">上网<br/>  
                    </td>  
                </tr>  
                <tr>  
                    <td>性别：</td>  
                    <td>  
                        <input type="radio" name="sex" checked="checked"/>男  
                         <input type="radio" name="sex"/>女<br/>  
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
                        <select name="list">  
                            <option value="1">律师</option>  
                            <option value="2" selected="selected">学生</option>  
                            <option value="3">医生</option>  
                        </select>  
                    </td>
                </tr>  
                <tr>  
                    <td>个人介绍：</td>  
                    <td><textarea rows="5" cols="20" name="introduce"></textarea><br/></td>  
                </tr>  
                <tr>  
                    <td colspan="2" align="center">  
                        <input type="submit" value="提交"/>  
                        <input type="reset" value="重置"/>  
                    </td>  
                </tr>  
            </table>  
        </form>  
  </body>
  <script type="text/javascript">
  window.onload=function(){
   $("#getStudents").on("click",function(){
      $.post("/test.do",{"a":"zzz","b":1312},function(dat){
          var dat=JSON.stringify(dat);
          var stringBuffer=[];         
          console.log("success");         
      })
   
   })
  
  }
  </script>
</html>
