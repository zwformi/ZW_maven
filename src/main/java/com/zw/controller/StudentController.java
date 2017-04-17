package com.zw.controller;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zw.entity.Student;
import com.zw.service.StudentService;
import com.zw.util.Md5Util;
import com.zw.util.UploadFlieUtil;

@Controller
public class StudentController {
  @Resource
  private StudentService studentService;
  
  /**
   * 分页获取记录数
   * @param request
   * @return
   */
  @RequestMapping(value="/querylist", method = RequestMethod.POST)
  @ResponseBody
  public List<Student> getSlist(HttpServletRequest request){
	  
	  List<Student> sl = studentService.getStudent(request); 
	  System.out.println("你已经进入了Controller层！！");
	  
	  return sl;
	  
  }
  /**
   *  获取学生总记录数
   * @param request
   * @return
   */
  @RequestMapping(value="/querylistcount", method = RequestMethod.POST)
  @ResponseBody
  public Integer getSlistCount(HttpServletRequest request){
	  
	  Integer rs = studentService.getStudentCount(request); 	  
	  return rs;
	  
  }
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException {

          String username = request.getParameter("username").trim(); 
          String userpsw = request.getParameter("password").trim();
          System.out.println("用户名："+username+",密码："+userpsw);
          String psw = Md5Util.getMd5(userpsw).toLowerCase(); 
          String returnView ="redirect:/login.jsp";
          //使用request对象的getSession()获取session，如果session不存在则创建一个
           HttpSession session = request.getSession();
         //获取session的Id
           String sessionId = session.getId();
          //判断session是不是新创建的
           if (session.isNew()) {
              response.getWriter().print("session创建成功，session的id是："+sessionId);
            }else {
              response.getWriter().print("服务器已经存在session，session的id是："+sessionId);
            }
           
           Integer flag = 0;
           Integer isLogin = 0 ;
           String validationCode = request.getParameter("validationCode");

           String validation_code = (String)session.getAttribute("validation_code");

           if(validationCode.equalsIgnoreCase(validation_code)){

               System.out.println("验证码正确");
               flag = 1;

           }else{

               System.out.println("验证码错误");
               
           } 
          session.setAttribute("Codeflag", flag);
          if(flag==1){
        	  Student student =studentService.checkStudent(username,psw);
              if(student != null){  
            	  //登陆成功
            	  String userinfo = "{\"name\":\""+student.getName()+"\",\"password\":\""+student.getPassword()+"\",\"id\":\""+student.getId()+"\"}";
            	  session.setAttribute("userInfo", userinfo);
            	  session.setAttribute("name", student.getName());
            	  session.setAttribute("password", student.getPassword());
            	  session.setAttribute("id", student.getId());
            	  isLogin = 1;
            	  returnView ="redirect:/index.jsp";
              }
              else{
            	  returnView ="redirect:/login.jsp";
              }
          }
          session.setAttribute("isLogin", isLogin);
      return returnView;
  }
  
  @RequestMapping(value = "/loginout", method = RequestMethod.GET)
  public String loginout(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
	  request.getSession().invalidate();
	  return "redirect:/login.jsp";
  }
  @RequestMapping(value = "/test", method = RequestMethod.POST)
  @ResponseBody
  public List<Student> test(HttpServletRequest request) {
	  
	  String a = request.getParameter("a");
	  String b = request.getParameter("b");
	  System.out.println("a:"+a+"b:"+b);
	  List<Student> sl = studentService.getStudent(request);
	  System.out.println("你已经进入了Controller层！！");
	  
	  return sl;
  
  }
  
  /**
   * 添加学生
   * @param request
   * @param redirectAttributes
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public String addStudent(HttpServletRequest request, RedirectAttributes redirectAttributes) {
	  
	  int id = studentService.addStudent(request);
	  redirectAttributes.addFlashAttribute("message", "添加学生成功");
      return "redirect:/index.jsp?id="+id; 
  }
  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public String editStudent(HttpServletRequest request, RedirectAttributes redirectAttributes) {
	  
	  int count = studentService.editStudent(request);
	  if(count>0)
	      redirectAttributes.addFlashAttribute("message", "修改学生成功");
	  else
		  redirectAttributes.addFlashAttribute("message", "修改学生失败");  
      return "redirect:/index.jsp?id="+request.getParameter("id"); 
  }
  
  
  @RequestMapping(value = "/query", method = RequestMethod.POST)
  @ResponseBody
  public List<Student> queryStudent(HttpServletRequest request) {  
	 return studentService.queryStudent(request);	  
  }
  
	 
@SuppressWarnings("unchecked")
@RequestMapping(value="/simpleFileupload",method=RequestMethod.POST)
public String uploadFlie(HttpServletRequest request){
	String rString = "";
	Map<String, Object> pathOrerr = studentService.simpleFileupload(request);
	if((Integer)pathOrerr.get("resultcode")!=0){
		    if((Integer)((Map<String, Object>)pathOrerr.get("resultmessage")).get("type")==0){
				String path =(String)((Map<String, Object>)pathOrerr.get("resultmessage")).get("path");
				Integer id =(Integer)((Map<String, Object>)pathOrerr.get("resultmessage")).get("id");
				studentService.updateImg(path, id);
		    }
		rString = "上传文件成功";
	}
	else{
		rString = "上传文件失败";
	}
	
	return "redirect:/jsp/uploader.jsp?message="+rString; 
}
	
}
