package com.zw.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zw.entity.Student;


public class test_main {
   public static void main(String[] arg){
/*	   UserServiceImpl userService = new UserServiceImpl();
	   userService.setUserdao(new UserDaoMySqlImpl());
	   userService.getUser();
	   
	   System.out.println("<====华丽丽的分割线====>");
	   
	   userService.setUserdao(new UserDaoOracleImpl()); 
	   userService.getUser();*/
	   
	   //解析beans.xml文件生成管理相应的bean对象 
/*       ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
       Student p = (Student)context.getBean("student");  
       p.show();   
*/       
   }
}
