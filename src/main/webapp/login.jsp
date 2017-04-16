<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
boolean isCodeRight = false; 
boolean isSuccess = false;
Integer Codeflag = (Integer)request.getSession().getAttribute("Codeflag");
Integer isLogin = (Integer)request.getSession().getAttribute("isLogin");
    if(Codeflag == null || (Codeflag != null && Codeflag != 0)){ 
      isCodeRight = true; 
    } 
     if(isLogin == null || (isLogin != null && isLogin != 0)){ 
      isSuccess = true; 
    } 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>js/assets/css/reset.css">
    <link rel="stylesheet" href="<%=basePath%>js/assets/css/supersized.css">
    <link rel="stylesheet" href="<%=basePath%>js/assets/css/style.css">

  </head>
  
  <body>
         <div class="page-container">
            <h1>Login</h1>

            <form action="/login.do" method="post">
            <div class="loginform_row">
                <label for="username">用户</label>
                <input type="text" name="username" id="username" class="username" placeholder="Username">
            </div>  
            <div class="loginform_row">      
                <label for="password">密码</label>
                <input type="password" name="password" id="password" class="password" placeholder="Password">
            </div> 
            <div class="loginform_row">       
                <label for="validationCode" style="padding: 0 5;">验证码</label>
                <input type = "text" name = "validationCode" id="validationCode" class="loginform_input_validationCode" style="width: 150px;"/>
                <img class = "validationCode_img" src="/getValidationCode.do" style="width: 115px;height:41px;vertical-align: middle;">  
            </div> 
            <div class="loginform_row">         
            <button type="submit" style="margin-left: 60">Sign me in</button>
            </div>  
            <div class="error"><span>+</span></div>
            </form>
            <div class="connect">
                <p>Or connect with:</p>
                <p>
                    <a class="facebook" href=""></a>
                    <a class="twitter" href=""></a>
                </p>
            </div>
        </div>

        <!-- Javascript -->
        <script src="<%=basePath%>js/jquery.min.js"></script>
        <script src="<%=basePath%>js/assets/supersized.3.2.7.min.js"></script>
        <script src="<%=basePath%>js/assets/supersized-init.js"></script>
        <script src="<%=basePath%>js/assets/scripts.js"></script>

        <script>
          if(!<%=isCodeRight%>){
             alert("验证码错误");
          }
          else{
              if(!<%=isSuccess%>){
                alert("用户名或者密码错误");
              }
          }
          window.onload=function(){
                $(".validationCode_img").click(function(){
                            $(".validationCode_img").attr("src","/getValidationCode.do?"+Math.random()*Math.random());
                         });                      
          };         
          function getUrlParam(name) {
		      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		      var r = window.location.search.substr(1).match(reg);  //匹配目标参数
		      if (r != null) return decodeURI(r[2]); return null; //返回参数值
		  }
        </script>

  </body>
</html>
