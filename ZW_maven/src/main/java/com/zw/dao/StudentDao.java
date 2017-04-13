package com.zw.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.inject.New;

import org.jboss.weld.exceptions.ForbiddenArgumentException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.zw.entity.Student;

@Repository("studentdao")
public class StudentDao {
    @Resource
	private JdbcTemplate jdbcTemplate;
    @Resource
	private Student student;
	
    
    public List<Student> quryStudent(){ 
	String sql = "select * from test_student where 1 = 1";
	
	List<Student> sl = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Student>(Student.class));
	
	for(Student s:sl){		
		System.out.println("姓名："+s.getName()+"年龄："+s.getAge()+"性别"+s.getSex()+"\n");
	}
	  return sl;
    }
    
    public boolean checkStudent(String name,String password){
    	 
    	String sql = "select count(*) from test_student where name = ? and password = ? ";
    	Object[] param ={name,password};
    	Integer Count = jdbcTemplate.queryForInt(sql,param); 
    	
    	return Count>0;
    }
    
    //String name,Integer age,Integer sex,String password,String hobby,String photo,Integer occupation,String introduce
    public int addStudent(final Student student){
    	
    	final String sql ="insert into test_student(name,age,sex,password,hobby,photo,occupation,introduce) VALUES(?,?,?,?,?,?,?,?)";
    	
    	KeyHolder keyHolder = new GeneratedKeyHolder(); 
    	jdbcTemplate.update(new PreparedStatementCreator() {    
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
                  
                //String sql_sms = "insert into  sms(title,content,date_s,form,sffs,by1,by2,by3) values (?,?,'"+dates+"',?,?,?,?,?)";   
                   java.sql.PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
                   ps.setString(1, student.getName());  
                   ps.setInt(2, student.getAge());  
                   ps.setInt(3, student.getSex());  
                   ps.setString(4, student.getPassword());  

                   ps.setString(5, student.getHobby());
                   ps.setString(6, student.getPhoto()); 
                   ps.setInt(7, student.getOccupation());  
                   ps.setString(8, student.getIntroduce());  
                   return ps;  
            }  
        }, keyHolder);  
          
        int generatedId = keyHolder.getKey().intValue();  
          
        
        
        return generatedId;  
    }
	
} 


