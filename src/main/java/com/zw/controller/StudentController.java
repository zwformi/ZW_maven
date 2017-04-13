package com.zw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zw.entity.Student;
import com.zw.service.StudentService;
import com.zw.util.Md5Util;

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
  public ModelAndView login(HttpServletRequest request) {

          String username = request.getParameter("username").trim(); 
          String userpsw = request.getParameter("userpsw").trim();
          System.out.println("用户名："+username+",密码："+userpsw);
          String psw = Md5Util.getMd5(userpsw).toLowerCase();
          
	      Map<String,Object> model = new HashMap<String,Object>();
          if(studentService.checkStudent(username,psw)){ 
        	  model.put("result", "登陆成功！！");
        	  model.put("result_code", 1);
        	  System.out.println("登陆成功！！");
          }
          else{
        	  model.put("result", "登陆失败！！");
        	  model.put("result_code", 0);
        	  System.out.println("登陆失败！！");
          }
          
           System.out.println("MAVTest.java login()....");
	       ModelAndView mav = new ModelAndView();
	       mav.setViewName("hello");
	       mav.addObject("msg", "hello kitty");
	   
           // List
	       List<String> list = new ArrayList<String>();
	       list.add("java");
	       list.add("c++");
	       list.add("oracle");
	       mav.addObject("bookList", list);
	   
	           // Map
	       Map<String, String> map = new HashMap<String, String>();
	       map.put("zhangsan", "北京");
	       map.put("lisi", "上海");
	       map.put("wangwu", "深圳");
	       mav.addObject("map", map);

           mav.addObject("result",model);
      return mav;
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

  @RequestMapping(value = "/query", method = RequestMethod.POST)
  @ResponseBody
  public List<Student> queryStudent(HttpServletRequest request) { 
	 return studentService.queryStudent(request);	  
  }
	
}
