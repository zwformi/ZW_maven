<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'student-list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/pagination/css/pagination.css">
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/pagination/jquery.pagination.js"></script>
    <style type="text/css">
    .table 
	{ 
	width: 100%; 
	padding: 0; 
	margin: 0; 
	} 
	th { 
	font: bold 12px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
	color: #4f6b72; 
	border-right: 1px solid #C1DAD7; 
	border-bottom: 1px solid #C1DAD7; 
	border-top: 1px solid #C1DAD7; 
	letter-spacing: 2px; 
	text-transform: uppercase; 
	text-align: left; 
	padding: 6px 6px 6px 12px; 
	background: #CAE8EA no-repeat; 
	} 
	td { 
	border-right: 1px solid #C1DAD7; 
	border-bottom: 1px solid #C1DAD7; 
	background: #fff; 
	font-size:14px; 
	padding: 6px 6px 6px 12px; 
	color: #4f6b72; 
	} 
	td.alt { 
	background: #F5FAFA; 
	color: #797268; 
	} 
	th.spec,td.spec { 
	border-left: 1px solid #C1DAD7; 
	} 
	/*---------for IE 5.x bug*/ 
	html>body td{ font-size:14px;} 
	tr.select th,tr.select td 
	{ 
	background-color:#CAE8EA; 
	color: #797268; 
	} 
	</style> 
  </head>
  
  <body>
    <table class="table" cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series"> 
		<tr> 
			<th class="spec">id</th> 
			<th>姓名</th> 
			<th>年龄</th> 
			<th>性别</th> 
			<th>爱好</th>
			<th>职业</th> 
			<th>个人介绍</th> 
			<th>头像</th>  
		</tr> 
<!-- 		<tr> 
			<td class="spec">1</td> 
			<td>小明</td> 
			<td class="alt">11</td> 
			<td>男</td> 
			<td class="spec">电影</td> 
			<td>律师</td> 
			<td class="alt">
			<textarea rows="5" cols="8" name="introduce" style=" width: 100%;">很多人纠结纠结的</textarea>
			</td> 
			<td>
			<img src="/456.jpg" alt="头像"/>
			</td> 
		</tr>  -->		
   </table> 
   <div id="Pagination" class="right quotes" style="float: right;"></div>
  </body>
  <script>
  var pageIndex = 0;     //页面索引初始值   
  var pageSize = 5;     //每页显示条数初始化，修改显示条数，修改这里即可   
  var pcount = GetPageSize();
  window.onload=function(){
                 //getStudentList(pageIndex,pageSize);    //Load事件，初始化表格数据，页面索引为0（第一页）
                //分页，PageCount是总条目数，这是必选参数，其它参数都是可选
                $("#Pagination").pagination(pcount, {
                    callback: PageCallback,  //PageCallback() 为翻页调用次函数。
                    prev_text: "上一页",
                    next_text: "下一页 ",
                    items_per_page:pageSize,
                    num_edge_entries: 1,       //两侧首尾分页条目数
                    num_display_entries: 3,    //连续分页主体部分分页条目数
                    current_page: pageIndex,   //当前页索引
                    link_to:"javascript:void(0);" //链接到url */
                });
  }


  //翻页调用   
  function PageCallback(index, jq) {
                    pageIndex=index;             
                    getStudentList(index,pageSize); 
                    } 
  function GetPageSize(){
  ///querylistcount
     var count = 0;
     //需要设置为同步
     $.ajaxSetup({   
            async : false  
        }); 
     $.post("/querylistcount.do",{},function(dat){
       count=dat;
     });
     return count;
  }
  function getStudentList(pageIndex,pageSize){
  
          $.post("/querylist.do",{"pageIndex":pageIndex,"pageSize":pageSize},function(dat){
          
          var data=JSON.stringify(dat);
/*           console.log(data); */
          var stringBuffer=[]; 
          var occupationarr=["律师","学生","医生"];        
	          for(var i=0;i<dat.length;i++){
	             var hobby=dat[i]['hobby'].replace("0", "电影").replace("1", "上网").replace(",", "");
	             stringBuffer.push("<tr>"); 
					stringBuffer.push("<td class='spec'>"+dat[i]['id']+"</td>");
					stringBuffer.push("<td>"+dat[i]['name']+"</td>");
					stringBuffer.push("<td class='alt'>"+dat[i]['age']+"</td>"); 
					stringBuffer.push("<td>"+(dat[i]['sex']==0?"男":"女")+"</td>"); 
					stringBuffer.push("<td class='spec'>"+hobby+"</td>");
					stringBuffer.push("<td>"+ occupationarr[dat[i]['occupation']-1]+"</td>"); 
					stringBuffer.push("<td class='alt'>");
					stringBuffer.push("<textarea rows='5' cols='8' name='introduce' style='width: 100%;'>"+dat[i]['introduce']+"</textarea>");
					stringBuffer.push("</td>"); 
					stringBuffer.push("<td>");
					stringBuffer.push("<img src='"+((dat[i]['photo']==null||dat[i]['photo']=="")?"/456.jpg":("/"+dat[i]['photo']))+"' alt='头像'/>");
					stringBuffer.push("</td>"); 
		       stringBuffer.push("</tr>"); 		   
	          }
	       $(".table tr:gt(0)").remove();
           $(".table").append(stringBuffer.join(""));
          
          })  
  
  }
  </script>
</html>
