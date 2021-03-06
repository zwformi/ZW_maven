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

import com.zw.entity.File;
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
    //"UPDATE `test_student` ts SET ts.age = 23 ,ts.hobby = '0,1' ,ts.`password` = '96e79218965eb72c92a549dd5a330112' ,ts.sex = 1 ,ts.occupation = 1,ts.introduce = 'hello' where ts.id = 32 ";
    public Integer updateStudent(Student st){ 
		String sql = "UPDATE `test_student` ts SET ts.age = ? ,ts.hobby = ? ,ts.`password` = ? ,ts.sex = ? ,ts.occupation = ? ,ts.introduce = ? where ts.id = ? "; 
		Object[] param = {st.getAge(),st.getHobby(),st.getPassword(),st.getSex(),st.getOccupation(),st.getIntroduce(),st.getId()};
		Integer count = jdbcTemplate.update(sql,param);
		return count;
    }
    /**
     * 查询单个学生信息
     * @param id
     * @return
     */
    
    public List<Student> queryStudentForOne(Integer id){ 
    	String sql = "select * from test_student where id = ?";
    	Object[] param = { id };
    	List<Student> sl = jdbcTemplate.query(sql,param,new BeanPropertyRowMapper<Student>(Student.class));    	
    	return sl;
        }
   /**
    * 更换头像 
    * @param path
    * @param id
    * @return
    */
    public int upDateImg(String path,Integer id){ 
    	String sql = "UPDATE `test_student` ts SET ts.photo = ? where id = ?";
    	Object[] param = { path, id };
    	int count = jdbcTemplate.update(sql,param);    	
    	return count;
        }
	
    /**
     * 上传文件记录
     * @param path
     * 文件路径
     * @param id
     * 记录id
     * @param type
     * 文件类型
     * @return
     */
    public int insertFile(Integer id, String path, Integer type,String name){  
    	String sql = "insert into test_file (recordid,filepath,type,name) values(?,?,?,?)";
    	Object[] param = {id, path, type, name};
    	int count= jdbcTemplate.update(sql, param);    	
    	return count;
        }
    /**
     * 获取用户上传的文件
     * @param recordid
     * 记录id
     * @return 
     */
    public List<File> queryFileByRecordId(Integer id){  
    	String sql = "select * from test_file where recordid = ?";
    	Object[] param = { id };
    	List<File> sl = jdbcTemplate.query(sql,param,new BeanPropertyRowMapper<>(File.class));    	
    	return sl;
        }
    
    public List<File> queryFileById(Integer id){  
    	String sql = "select * from test_file where id = ?";
    	Object[] param = { id };
    	List<File> sl = jdbcTemplate.query(sql,param,new BeanPropertyRowMapper<>(File.class));    	
    	return sl;
        }
    
} 


