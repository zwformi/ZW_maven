package com.zw.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.zw.dao.StudentDao;
import com.zw.entity.Student;
import com.zw.util.Md5Util;

@Service
public class StudentService {
    @Resource
	private StudentDao studentdao;
    
    /**
     * 分页返回学生数据
     * @param request
     * @return
     */
    public List<Student> getStudent(HttpServletRequest request){
    	Integer pageindex = Integer.valueOf(request.getParameter("pageIndex"));
    	Integer pagesize = Integer.valueOf(request.getParameter("pageSize"));
    	List<Student> lStudents= new ArrayList<Student>();
    	lStudents=studentdao.quryStudent(pageindex,pagesize); 
    	
    	return lStudents;
    }
    /**
     * 获取学生总记录数 
     * @param request
     * @return
     */
    public Integer getStudentCount(HttpServletRequest request){
    	return studentdao.quryStudentCount();
    }
    /**
     * 登陆
     * @param name
     * @param password
     * @return
     */

    public Student checkStudent(String name,String password){
    	
    	return studentdao.checkStudent(name,password);
    }
    
    /**
     * 添加学生
     * @param request
     * @return
     */
    public int addStudent(HttpServletRequest request){
    	
    Student st = new Student();
    st.setName(request.getParameter("userName"));
    st.setAge(Integer.valueOf(request.getParameter("age")));
    st.setSex(Integer.valueOf(request.getParameter("sex")));
    st.setPassword(Md5Util.getMd5(request.getParameter("password"))); 
    st.setHobby((request.getParameter("hobby0")==null?"":request.getParameter("hobby0"))+","+(request.getParameter("hobby1")==null?"":request.getParameter("hobby1")));
    st.setPhoto(request.getParameter("img")==null?"":request.getParameter("img"));
    st.setOccupation(request.getParameter("occupation").equals("0")?1:Integer.valueOf(request.getParameter("occupation")));
    st.setIntroduce(request.getParameter("introduce"));
    
    int rs = studentdao.addStudent(st);	
    	
    return rs;	
    }
    
    public List<Student> queryStudent(HttpServletRequest request){
    	Integer id = Integer.valueOf(request.getParameter("id"));
        return  studentdao.queryStudentForOne(id);
    }
	
}
