<<<<<<< HEAD
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
=======
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
>>>>>>> 2271f8da801506f2f16b1bcc50f12daced477271
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<<<<<<< HEAD
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
=======
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
>>>>>>> 2271f8da801506f2f16b1bcc50f12daced477271
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
<<<<<<< HEAD
            <form action="/login.do" method="post">
                <input type="text" name="username" class="username" placeholder="Username">
                <input type="password" name="password" class="password" placeholder="Password">
                <button type="submit">Sign me in</button>  
=======
            <form action="" method="post">
                <input type="text" name="username" class="username" placeholder="Username">
                <input type="password" name="password" class="password" placeholder="Password">
                <button type="submit">Sign me in</button>
>>>>>>> 2271f8da801506f2f16b1bcc50f12daced477271
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
<<<<<<< HEAD
=======
        <div align="center">Collect from <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a></div>
>>>>>>> 2271f8da801506f2f16b1bcc50f12daced477271

        <!-- Javascript -->
        <script src="<%=basePath%>js/jquery.min.js"></script>
        <script src="<%=basePath%>js/assets/supersized.3.2.7.min.js"></script>
        <script src="<%=basePath%>js/assets/supersized-init.js"></script>
        <script src="<%=basePath%>js/assets/scripts.js"></script>
<<<<<<< HEAD
        <script>
            if(!!getUrlParam("message")){
			     alert(getUrlParam("message"));
			  } 
          function getUrlParam(name) {
		      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		      var r = window.location.search.substr(1).match(reg);  //匹配目标参数
		      if (r != null) return decodeURI(r[2]); return null; //返回参数值
		  }
        </script>
=======
>>>>>>> 2271f8da801506f2f16b1bcc50f12daced477271
  </body>
</html>
