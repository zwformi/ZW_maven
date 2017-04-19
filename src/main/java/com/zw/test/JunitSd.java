package com.zw.test;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
import org.springframework.transaction.annotation.Transactional;  
 

import com.zw.entity.Student;
import com.zw.mapper.StudentMapper;
/*	使用Junit测试Java代码*/
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(value = "springmvc-servlet.xml")     
public class JunitSd {			 	
		@Autowired
		private StudentMapper studentMapper;
		 
		@Transactional
		public void getStudentTest(){
			Student entity = studentMapper.getStudent("1");
			System.out.println("" + entity.getId() + entity.getName());
			
			List<Student> studentList = studentMapper.getStudentAll();
			for( Student entityTemp : studentList){
				System.out.println(entityTemp.getName());
			}
			
		}
	}

