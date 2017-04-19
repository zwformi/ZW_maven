package com.zw.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zw.entity.Student;

@Repository  
@Transactional
public interface StudentMapper {
	
	public Student getStudent(String studentID);
	
	public Student getStudentAndClass(String studentID);
	
	public List<Student> getStudentAll();
	
	public void insertStudent(Student entity);
	
	public void deleteStudent(Student entity);
	
	public void updateStudent(Student entity);
}

