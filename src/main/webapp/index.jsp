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
	<script src="<%=basePath%>js/jquery.min.js"></script>
    <style>
      .info_line{
       vertical-align: top;
       display:inline-block;
       margin-top:80px;
      }
    </style>
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
    <div class="info_line">
           <div> 用户信息</div>
    <!-- <button id="getStudents">获取学生信息</button> -->
    <!-- 表单提交 -->
	    <form action="/edit.do" method="post" id="information">  
	            <table align="center" border='0' cellspacing="10" cellpadding="10" style="margin:inherit">  
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
	                        <input type="submit" value="修改"/>  
	                        <input type="reset" value="重置"/>  
	                    </td>                  
	                </tr>
	            </table>  
	        </form>
        </div>
        <div class="info_line" id="file">
                            <div> 文件列表 </div>
          
        </div>
        <button id="redirect_s" style="display:none">跳转学生列表...</button> 
        <button id="uploadjsp" style="display:block">跳转文件上传...</button>  
        <button id="addstudent" style="display:block">添加</button>
  </body>
  <script type="text/javascript"> 
  if('${message}'!=''){
     alert('${message}');
   }
  var _id = '${id}';
  if(getUrlParam("id")!=null){
    _id = getUrlParam("id");
  }
  if(_id !=null && _id != ''){
   $("input[name='id']").val(_id);
   $("#psw").css("display","none");
   $("#redirect_s").css("display","block");
   $.post("/query.do",{"id":_id },function(dat){
      console.log("success");
      console.log(JSON.stringify(dat));
     // alert(dat.length);
    var datainfo = dat["userinfo"]; 
    var datafile = dat["userfile"];
       for(var i=0;i<datainfo.length;i++){
         $("input[name='id']").val(datainfo[0]["id"]);
         $("input[name='userName']").val(datainfo[0]["name"]);
         $("input[name='age']").val(datainfo[0]["age"]);
         var hobbyarr=datainfo[0]["hobby"].split(",");
         for(var i=0;i<hobbyarr.length;i++){
            if(hobbyarr[i]!=""){
                   $("#hobby").find("input[value='"+hobbyarr[i]+"']").prop("checked",true);        
            }        
         }        
         $("input[name='sex'][value='"+datainfo[0]["sex"]+"']").prop("checked",true);
         $("select[name='occupation']").val(datainfo[0]["occupation"]);
         $("textarea[name='introduce']").val(datainfo[0]["introduce"]);
      
      } 
      var Htmlarr = [];
      var typetable = ["图片","文本","音频","视频","压缩文件"];
      for(var i=0;i<datafile.length;i++){
         Htmlarr.push("<div><span>"+datafile[i]["name"]+"</span>&nbsp;&nbsp;<span>"+typetable[datafile[i]["type"]]+"</span>&nbsp;&nbsp;<a href='/dowmLoad/"+datafile[i]["id"]+".do'>下载</a></div>");
      }
      if(datafile.length==0){
         Htmlarr.push("<div><span>暂无上传文件数据！</span></div>");
      }
      $("#file").append(Htmlarr.join(""));
   })
  }
  window.onload=function(){
  
   $("#getStudents").on("click",function(){
      $.post("/test.do",{"a":"zzz","b":1312},function(dat){
          var dat=JSON.stringify(dat);
          var stringBuffer=[];         
          console.log("success,data:"+dat);         
      })  
   })
    $("#redirect_s").on("click",function(){
        window.location.href="/jsp/student-list.jsp";
    })
    $("#uploadjsp").on("click",function(){
        window.location.href="/jsp/uploader.jsp";
    })
    $("#addstudent").on("click",function(){
        window.location.href="/jsp/add.jsp";
    })
  }
  function getUrlParam(name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
      var r = window.location.search.substr(1).match(reg);  //匹配目标参数 
      if (r != null) return decodeURI(r[2]); return null; //返回参数值
  }
  </script>
</html>
