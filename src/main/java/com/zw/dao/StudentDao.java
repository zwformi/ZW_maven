package com.zw.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.zw.entity.Student;

@Repository("studentdao")
public class StudentDao {
    @Resource
	private JdbcTemplate jdbcTemplate;
    @Resource
	private Student student;
	
    /**
     * 分页返回学生数据
     * @param pageindex
     * @param pagesize
     * @return
     */
    public List<Student> quryStudent(Integer pageindex,Integer pagesize){ 
	String sql = "select * from test_student limit ?,? ";  // 检索记录行 pageindex-pagesize
	Object[] param = {pageindex*pagesize,pagesize};
	List<Student> sl = jdbcTemplate.query(sql,param,new BeanPropertyRowMapper<Student>(Student.class));
	return sl;
    }
    /**
     * 获取学生总记录数 
     * @return
     */  
    public Integer quryStudentCount(){ 
    	String sql = "select count(*) from test_student ";	
    	Integer rs = jdbcTemplate.queryForInt(sql);
    	return rs;
    }
    /**
     * 登陆检测
     * @param name
     * @param password
     * @return
     */
    public Student checkStudent(String name,String password){
    	Student s = new Student();
    	String sql = "select * from test_student where name = ? and password = ? ";
    	Object[] param ={name,password};
    	List<Student> sl = jdbcTemplate.query(sql,param,new BeanPropertyRowMapper<Student>(Student.class));
    	if(sl.size()>0){
    		s=sl.get(0);
    	}
    	else {
			s=null;
		}
    	return s;
    }
    
    /**
     * 添加学生
     * @param student 学生实体
     * @return
     * 返回主键id
     */
    public int addStudent(final Student student){
    	
    	final String sql ="insert into test_student(name,age,sex,password,hobby,photo,occupation,introduce) VALUES(?,?,?,?,?,?,?,?)";
          
    	int idResult = 0;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {   
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql,new String[]{"id"}); 
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
        },keyHolder);
        idResult = keyHolder.getKey().intValue();
        return idResult;
          
    }
    
    
    public List<Student> queryStudentForOne(Integer id){ 
    	String sql = "select * from test_student where id = ?";
    	Object[] param = { id };
    	List<Student> sl = jdbcTemplate.query(sql,param,new BeanPropertyRowMapper<Student>(Student.class));    	
    	return sl;
        }
	
} 


