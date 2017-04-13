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

import com.zw.entity.Student;
import com.zw.service.StudentService;
import com.zw.util.Md5Util;

@Controller
public class StudentController {
  @Resource
  private StudentService studentService;
  
  @RequestMapping("/hello")
  public String  getSlist(){
	  
	  List<Student> sl = studentService.getStudent();
	  System.out.println("你已经进入了Controller层！！");
	  
	  return "hello";
	  
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
	  List<Student> sl = studentService.getStudent();
	  System.out.println("你已经进入了Controller层！！");
	  
	  return sl;
  
  }
  
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public ModelAndView addStudent(HttpServletRequest request) {
	  
	  
	  
	  
	  return new ModelAndView();
  }
	
	
}
