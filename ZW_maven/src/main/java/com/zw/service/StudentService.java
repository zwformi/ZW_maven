package com.zw.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zw.dao.StudentDao;
import com.zw.entity.Student;

@Service
public class StudentService {
    @Resource
	private StudentDao studentdao;
    
    public List<Student> getStudent(){
    	
    	List<Student> lStudents= new ArrayList<Student>();
    	lStudents=studentdao.quryStudent();
    	
    	return lStudents;
    }
    
    public boolean checkStudent(String name,String password){
    	
    	return studentdao.checkStudent(name,password);
    }
	
}
